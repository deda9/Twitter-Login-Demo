package com.twitter.demo;
/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

import android.app.Application;
import android.util.Log;

import com.twitter.demo.utilities.Constants;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

public class TwitterDemoApp extends Application {

    @Override
    public void onCreate() {
        initializeTwitter();
    }

    private void initializeTwitter() {
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(Constants.TWITTER_CONSUMER_KEY,
                        Constants.TWITTER_CONSUMER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }
}
