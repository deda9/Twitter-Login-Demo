package com.twitter.demo.ui.followers;

import android.content.Context;

import com.google.gson.Gson;
import com.twitter.demo.api.CustomTwitterApiClient;
import com.twitter.demo.models.FollowerListResponse;
import com.twitter.demo.utilities.SharedPrefUtilis;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class FollowersFragmentPresenterImp implements FollowersFragmentPresenter {

    private WeakReference<Context> weakReference;
    private FollowersView followersView;

    public FollowersFragmentPresenterImp(FollowersView followersView, Context context) {
        this.weakReference = new WeakReference<Context>(context);
        this.followersView = followersView;
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
                            if (followersView != null) {
                                followersView.showSomeThingWrong();
                            }
                            return;
                        }


                        if (followersView != null) {
                            followersView.setupRecyclerView(result.data);
                            long nextCursor = result.data.getNextCursor();
                            followersView.setNextCursor(nextCursor);
                        }
                    }

                    @Override
                    public void failure(TwitterException exception) {

                    }
                });
    }

    public interface FollowersView {

        void setupRecyclerView(FollowerListResponse data);

        void showSomeThingWrong();

        void setNextCursor(long nextCursor);
    }
}
