package com.twitter.demo.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.twitter.sdk.android.core.models.User;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class SharedPrefUtilis {


    private static SharedPreferences getSharedPrefInstance(Context context) {
        if (context == null)
            return null;
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void clearAllShared(Context context){
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().clear().apply();
    }

    public static String getCurrentLang(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.CURRENT_LANG, "ar");
    }

    public static void setCurrentLang(Context context, String lang) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.CURRENT_LANG, lang).apply();
    }


    public static String getUserID(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_ID, "none");
    }

    public static void setUserId(Context context, String id) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_ID, id).apply();
    }

    public static String getUserName(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_NAME, "User Name");
    }

    public static void setUserName(Context context, String userName) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_NAME, userName).apply();
    }

    public static String getUserEmail(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_EMAIL, "null");
    }

    public static void setUserScreenName(Context context, String userName) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_SCREEN_NAME, userName).apply();
    }

    public static String getUserScreenName(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_SCREEN_NAME, "null");
    }


    public static void setUserEmail(Context context, String email) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_EMAIL, email).apply();
    }


    public static void saveUserAccountInfo(Context context, User user) {
        setUserEmail(context, user.email);
        setUserName(context, user.name);
        setUserScreenName(context, user.screenName);
        setUserBackgroundUrl(context, user.profileBackgroundImageUrl);
        setUserProfileUrl(context, user.profileImageUrl);
        setUserId(context, String.valueOf(user.id));
    }

    public static void saveTwitterSession(Context context, String twitterSession) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_TWITTER_SESSION, twitterSession).apply();
    }

    public static String getTwitterSession(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_TWITTER_SESSION, "null");
    }

    public static void saveLoggedUserTimeLine(Context context, String userTimelineString) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_TIME_LINE, userTimelineString).apply();
    }

    public static String getLoggedUserTimeLine(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_TIME_LINE, "null");
    }

    public static String getUserProfileUrl(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_PROFILE_URL, "null");
    }

    public static String getUserBackgroundUrl(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        return getSharedPrefInstance(weakReference.get()).getString(Constants.USER_BACKGROUND_URL, "null");
    }

    public static void setUserProfileUrl(Context context, String url) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_PROFILE_URL, url).apply();
    }

    public static void setUserBackgroundUrl(Context context, String url) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        getSharedPrefInstance(weakReference.get()).edit().putString(Constants.USER_BACKGROUND_URL, url).apply();
    }
}
