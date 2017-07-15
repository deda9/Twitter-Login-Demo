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

import static com.twitter.sdk.android.core.Twitter.TAG;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * this is for he repsenter all the process in the follower frgamen t
 */
public class FollowersFragmentPresenterImp implements FollowersFragmentPresenter {

    private WeakReference<Context> weakReference;
    private FollowersView followersView;
    private FollowersFragmentInteractor interactor;

    public FollowersFragmentPresenterImp(FollowersView followersView, Context context) {
        this.weakReference = new WeakReference<>(context);
        this.followersView = followersView;
        interactor = new FollowersFragmentInteractorImp();
    }

    /**
     * get the user follower list based ont eh next cursor
     *  next cursor = -1 = the first page (Twitter Doc)
     * @param nextCursor
     */
    @Override
    public void getFollowersList(long nextCursor) {

        String twitterSessionString = SharedPrefUtilis.getTwitterSession(weakReference.get());
        if (twitterSessionString.equals("null")) {
            return;
        }
        TwitterSession twitterSession = new Gson().fromJson(twitterSessionString, TwitterSession.class);

        //Create the cusotm tiwtter api client based on he retrofit library
        final CustomTwitterApiClient twitterApiClient = new CustomTwitterApiClient(twitterSession);
        twitterApiClient
                .getCustomTwitterService()
                .getUserFollowersList(twitterSession.getUserId(), nextCursor)
                .enqueue(new com.twitter.sdk.android.core.Callback<FollowerListResponse>() {
                    @Override
                    public void success(Result<FollowerListResponse> result) {
                        hideProgressDialogForFollowers();
                        if (result == null || result.data == null) {
                            showErrorMessage();
                            return;
                        }

                        saveFollowersResponse(result);
                        handleFollowersListSuccess(result);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        hideProgressDialogForFollowers();
                        if (followersView != null) {
                            followersView.showSomeThingWrong();
                        }
                        Log.d(TAG, "getUserFollowersList failure" );
                    }
                });
    }

    private void hideProgressDialogForFollowers() {
        if (followersView != null) {
            followersView.hideProgressDialogForFollowers();
        }
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

    /**
     * Called when the list fo the follower is came succcesfully and pass the delegate for the
     * follower frgamen to show the list in the recyclerviw
     *
     * @param result
     */
    private void handleFollowersListSuccess(Result<FollowerListResponse> result) {
        if (followersView != null) {
            long nextCursor = result.data.getNextCursor();
            followersView.setNextCursor(nextCursor);
            followersView.setupRecyclerView(result.data);
        }
    }

    /**
     *Talk to the db layer to save thme
     * @param result
     */
    private void saveFollowersResponse(Result<FollowerListResponse> result) {
        if(interactor != null){
            interactor.saveFollowers(result.data);
        }
    }

    public interface FollowersView {

        void setupRecyclerView(FollowerListResponse data);

        void showSomeThingWrong();

        void setNextCursor(long nextCursor);

        void hideProgressDialogForFollowers();
    }
}
