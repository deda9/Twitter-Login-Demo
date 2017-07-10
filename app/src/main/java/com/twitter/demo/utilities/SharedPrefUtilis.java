package com.twitter.demo.utilities;

import android.content.Context;
import android.content.SharedPreferences;
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

    public static String getUserID() {

        return "f";
    }
}
