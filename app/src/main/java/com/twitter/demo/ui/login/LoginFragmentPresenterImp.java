package com.twitter.demo.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.twitter.demo.api.CustomTwitterApiClient;
import com.twitter.demo.models.FollowerListResponse;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class LoginFragmentPresenterImp implements LoginFragmentPresenter {

    private String TAG = getClass().getName();
    private LoginView mLoginView;
    private TwitterAuthClient twitterClient;
    private WeakReference<Context> mWeakReference;


    public LoginFragmentPresenterImp(LoginView loginView, Context context) {
        mLoginView = loginView;
        mWeakReference = new WeakReference<Context>(context);
    }

    @Override
    public void onLoginSuccess() {
        mLoginView.onSuccessTwitterLogin();
    }

    @Override
    public void onLoginFail() {
        mLoginView.onFailTwitterLogin();
    }

    @Override
    public void loginWithTwitter() {
        twitterClient = new TwitterAuthClient();
        twitterClient.authorize((Activity) mWeakReference.get(), new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                if (mLoginView != null) {

                    Log.d("hello", result.data.getUserName());
                    Log.d("hello", "4");
                    getUserEmailSilence(result.data);
                    mLoginView.onSuccessTwitterLogin();
                }else {
                    Log.e(TAG,"mLoginView is null, so we can't call onSuccessTwitterLogin for it");
                }
            }

            @Override
            public void failure(TwitterException exception) {
                if (mLoginView != null) {
                    mLoginView.onFailTwitterLogin();
                    twitterClient.cancelAuthorize();
                    Log.d("hello", exception.getLocalizedMessage());
                    Log.d("hello", "5");
                }
            }
        });
    }
//TODO: Need to refactor this once we did all apis
    private void getUserEmailSilence(final TwitterSession twitterSession) {
        if(twitterClient != null){
            twitterClient.requestEmail(twitterSession, new Callback<String>() {
                @Override
                public void success(Result<String> result) {
                    Log.d("hello", "userEmail" + result.data);

                    TwitterAuthToken authToken = twitterSession.getAuthToken();
                    String token = authToken.token;
                    String secret = authToken.secret;
                    Log.d("hello", "secret" + secret);
                    Log.d("hello", "token" + token);
                    Log.d("hello", "twitterSession.getUserId" + twitterSession.getUserId());


//                    TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
//                    StatusesService statusesService = twitterApiClient.getStatusesService();
//                    Call<Tweet> call = statusesService.show(524971209851543553L, null, null, null);
//                    call.enqueue(new Callback<Tweet>() {
//                        @Override
//                        public void success(Result<Tweet> result) {
//                            //Do something with result
//                        }
//
//                        public void failure(TwitterException exception) {
//                            //Do something on failure
//                        }
//                    });






                    final CustomTwitterApiClient twitterApiClient = new CustomTwitterApiClient(twitterSession);
                    twitterApiClient
                            .getCustomTwitterService()
                            .getUserFollowersList(twitterSession.getUserId(), -1).enqueue(new Callback<FollowerListResponse>() {
                        @Override
                        public void success(Result<FollowerListResponse> resdult) {


                            Log.d("sdsdsd", resdult.data.getNextCursor() +" n");

                            twitterApiClient
                                    .getCustomTwitterService()
                                    .getUserFollowersList(twitterSession.getUserId(), resdult.data.getNextCursor()).enqueue(new Callback<FollowerListResponse>() {
                                @Override
                                public void success(Result<FollowerListResponse> resdult) {

                                }

                                @Override
                                public void failure(TwitterException exception) {

                                }
                            });

                        }

                        @Override
                        public void failure(TwitterException exception) {

                        }
                    });



                }

                @Override
                public void failure(TwitterException exception) {

                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("hello", "3");
        if (twitterClient != null) {
            twitterClient.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.e(TAG,"twitterClient is null, so we cant set onActivityResult for it");
        }
    }

    public interface LoginView {

        void onSuccessTwitterLogin();

        void onFailTwitterLogin();
    }
}
