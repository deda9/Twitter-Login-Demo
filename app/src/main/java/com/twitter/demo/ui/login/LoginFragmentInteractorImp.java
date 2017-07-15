package com.twitter.demo.ui.login;

import android.content.Context;

import com.google.gson.Gson;
import com.twitter.demo.utilities.SharedPrefUtilis;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * this class is the interactor for the login fragment to save the data
 */
public class LoginFragmentInteractorImp  implements LoginFragmentInteractor{

    private WeakReference<Context> weakReference;

    public LoginFragmentInteractorImp(Context context) {
        this.weakReference = new WeakReference<>(context);
    }


    /**
     * Save user data into shared pref
     * @param user
     */
    @Override
    public void saveUserAccountInfo(User user) {
        SharedPrefUtilis.saveUserAccountInfo(weakReference.get(), user);
    }

    /**
     * save the user twitter session to use it later to get the followers and the tweets timeline
     * @param twitterSession
     */
    @Override
    public void saveTwitterSession(TwitterSession twitterSession) {
        String twitterSessionSerialized = new Gson().toJson(twitterSession);
        SharedPrefUtilis.saveTwitterSession(weakReference.get(), twitterSessionSerialized);
    }
}
