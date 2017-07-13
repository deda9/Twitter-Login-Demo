package com.twitter.demo.utilities;

import android.content.Context;
import android.content.SharedPreferences;

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


    public static String getUserID() {

        return "f";
    }
}
