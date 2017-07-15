package com.twitter.demo.api;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Bassem Qoulta (Deda) on  7/12/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * this class is cutomize the twitter TwitterApiClient to get the user followers
 */
public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public CustomTwitterApiService getCustomTwitterService() {

        return getService(CustomTwitterApiService.class);
    }
}
