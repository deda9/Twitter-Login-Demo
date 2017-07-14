package com.twitter.demo.ui.followers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.twitter.demo.api.CustomTwitterApiClient;
import com.twitter.demo.models.FollowerListResponse;
import com.twitter.demo.utilities.SharedPrefUtilis;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.lang.ref.WeakReference;

import io.realm.Realm;

import static com.twitter.sdk.android.core.Twitter.TAG;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class FollowersFragmentPresenterImp implements FollowersFragmentPresenter {

    private WeakReference<Context> weakReference;
    private FollowersView followersView;
    private FollowersFragmentInteractor interactor;

    public FollowersFragmentPresenterImp(FollowersView followersView, Context context) {
        this.weakReference = new WeakReference<>(context);
        this.followersView = followersView;
        interactor = new FollowersFragmentInteractorImp(Realm.getDefaultInstance());
    }

    @Override
    public void getFollowersList(long nextCursor) {

        String twitterSessionString = SharedPrefUtilis.getTwitterSession(weakReference.get());
        if (twitterSessionString.equals("null")) {
            return;
        }
        TwitterSession twitterSession = new Gson().fromJson(twitterSessionString, TwitterSession.class);

        final CustomTwitterApiClient twitterApiClient = new CustomTwitterApiClient(twitterSession);
        twitterApiClient
                .getCustomTwitterService()
                .getUserFollowersList(twitterSession.getUserId(), nextCursor)
                .enqueue(new com.twitter.sdk.android.core.Callback<FollowerListResponse>() {
                    @Override
                    public void success(Result<FollowerListResponse> result) {
                        if (result == null || result.data == null) {
                            showErrorMessage();
                            return;
                        }

                        handleFollowersListSuccess(result);
                        saveFollowersResponse(result);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.d(TAG, "getUserFollowersList failure" );
                    }
                });
    }

    @Override
    public void onDestroy() {
        if(interactor != null){
            interactor.onDestroy();
        }
    }

    private void showErrorMessage() {
        if (followersView != null) {
            followersView.showSomeThingWrong();
        }
    }

    private void handleFollowersListSuccess(Result<FollowerListResponse> result) {
        if (followersView != null) {
            followersView.setupRecyclerView(result.data);
            long nextCursor = result.data.getNextCursor();
            followersView.setNextCursor(nextCursor);
        }
    }

    private void saveFollowersResponse(Result<FollowerListResponse> result) {
        if(interactor != null){
            interactor.saveFollowers(result.data);
        }
    }

    public interface FollowersView {

        void setupRecyclerView(FollowerListResponse data);

        void showSomeThingWrong();

        void setNextCursor(long nextCursor);
    }
}
