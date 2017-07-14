package com.twitter.demo;
/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

import android.app.Application;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import com.twitter.demo.db.BaseRealmManager;
import com.twitter.demo.utilities.Constants;
import com.twitter.demo.utilities.SharedPrefUtilis;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

public class TwitterDemoApp extends Application {

    private Locale locale;

    @Override
    public void onCreate() {
        initializeTwitter();
        String lang = SharedPrefUtilis.getCurrentLang(this);
        switchLanguage(lang);
        setupRealm();
    }

    private void setupRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new BaseRealmManager().getRealmConfiguration(this);
        Realm.setDefaultConfiguration(configuration);
    }


    public void switchLanguage(String lang) {
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();

        locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration newConfiguration = new Configuration(configuration);
        newConfiguration.setLocale(locale);

        getBaseContext().getResources().updateConfiguration(newConfiguration, displayMetrics);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (locale != null) {
            Locale.setDefault(locale);
            Configuration config = new Configuration(newConfig);
            config.setLocale(locale);
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
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
