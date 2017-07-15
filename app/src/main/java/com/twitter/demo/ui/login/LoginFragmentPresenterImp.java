package com.twitter.demo.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * this class is the presenter for the login fragment
 */
public class LoginFragmentPresenterImp implements LoginFragmentPresenter {

    private String TAG = getClass().getName();
    private LoginView mLoginView;
    private TwitterAuthClient twitterClient;
    private WeakReference<Context> mWeakReference;
    private LoginFragmentInteractor loginInteractor;


    public LoginFragmentPresenterImp(LoginView loginView, Context context) {
        mLoginView = loginView;
        mWeakReference = new WeakReference<Context>(context);
        loginInteractor = new LoginFragmentInteractorImp(context);
    }

    @Override
    public void onLoginSuccess() {
        if (mLoginView != null)
            mLoginView.onSuccessTwitterLogin();
    }

    @Override
    public void onLoginFail() {
        mLoginView.onFailTwitterLogin();
    }

    /**
     * the operation for the login user by twitter account
     * we initialize the TwitterAuthClient and ask it to login the user
     * it return the Twitter session after the log in
     */
    @Override
    public void loginWithTwitter() {
        twitterClient = new TwitterAuthClient();
        twitterClient.authorize((Activity) mWeakReference.get(), new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                if (mLoginView != null) {
                    mLoginView.showProgressDialog();
                    getUserAccountInfo(result.data);
                } else {
                    Log.e(TAG, "mLoginView is null, so we can't call onSuccessTwitterLogin for it");
                }
            }

            @Override
            public void failure(TwitterException exception) {
                if (mLoginView != null) {
                    mLoginView.onFailTwitterLogin();
                    twitterClient.cancelAuthorize();
                }
            }
        });
    }

    /**
     * after the user logged in , we ask the twitter to get the logged user account data
     * we use from them the username , user screen name, profile url, backgound url
     *
     * @param twitterSession
     */
    private void getUserAccountInfo(final TwitterSession twitterSession) {
        TwitterApiClient twitterApiClient = new TwitterApiClient(twitterSession);
        twitterApiClient
                .getAccountService()
                .verifyCredentials(false, false, true)
                .enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {

                        // we ask the interactor to save the data to use later
                        LoginFragmentPresenterImp.this.loginInteractor.saveUserAccountInfo(result.data);
                        LoginFragmentPresenterImp.this.loginInteractor.saveTwitterSession(twitterSession);

                        // we talk to the fragment delegate to show the message when success.
                        if (LoginFragmentPresenterImp.this.mLoginView != null) {
                            onLoginSuccess();
                        }
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.e(TAG, " we can't call getUserAccountInfo, Failed");
                    }
                });


//
//        if(twitterClient != null){
//            twitterClient.requestEmail(twitterSession, new Callback<String>() {
//                @Override
//                public void success(Result<String> result) {
//                    TwitterAuthToken authToken = twitterSession.getAuthToken();


//
//                    final CustomTwitterApiClient twitterApiClient = new CustomTwitterApiClient(twitterSession);
//                    twitterApiClient
//                            .getCustomTwitterService()
//                            .getUserFollowersList(twitterSession.getUserId(), -1).enqueue(new Callback<FollowerListResponse>() {
//                        @Override
//                        public void success(Result<FollowerListResponse> resdult) {
//
//
//                            Log.d("sdsdsd", resdult.data.getNextCursor() +" n");
//
//                            twitterApiClient
//                                    .getCustomTwitterService()
//                                    .getUserFollowersList(twitterSession.getUserId(), resdult.data.getNextCursor()).enqueue(new Callback<FollowerListResponse>() {
//                                @Override
//                                public void success(Result<FollowerListResponse> resdult) {
//
//                                }
//
//                                @Override
//                                public void failure(TwitterException exception) {
//
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void failure(TwitterException exception) {
//
//                        }
//                    });


//                }

//                @Override
//                public void failure(TwitterException exception) {
//
//                }
//            });
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("hello", "3");
        if (twitterClient != null) {
            twitterClient.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.e(TAG, "twitterClient is null, so we cant set onActivityResult for it");
        }
    }

    public interface LoginView {

        void onSuccessTwitterLogin();

        void onFailTwitterLogin();

        void showProgressDialog();
    }
}
