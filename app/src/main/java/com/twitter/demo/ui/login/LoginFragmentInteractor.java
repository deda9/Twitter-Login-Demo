package com.twitter.demo.ui.login;

import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

public interface LoginFragmentInteractor {

    void saveUserAccountInfo(User user);

    void saveTwitterSession(TwitterSession twitterSession);
}
