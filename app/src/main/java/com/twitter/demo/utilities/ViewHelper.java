package com.twitter.demo.utilities;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by bassem.anwer on 7/18/16.
 * Bassem Qoulta (Deda9).
 * https://eg.linkedin.com/in/bassemqoulta
 */
public class ViewHelper {

    public static void showMessage(Context context, @StringRes int errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }

    public static void showMessage(Context context, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
    }
}
