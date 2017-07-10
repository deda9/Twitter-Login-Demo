package com.twitter.demo.utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.twitter.demo.R;

/**
 * Created by Bassem Qoulta (Deda) on 6/30/2016.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class Utilis {

    static ProgressDialog prDialog;

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        boolean state = info != null && info.isConnected();
        if (!state) {
            showNetworkError(context);
            return false;
        } else
            return true;
    }

    public static void showNetworkError(Context context) {
        showToast(context, "Network is not available");
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void hideProgressDialog() {
        if (prDialog != null)
            prDialog.dismiss();
    }

    public static void showProgressDialog(Context context) {
        prDialog = new ProgressDialog(context);
        prDialog.show();
        prDialog.setCancelable(false);
        prDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        prDialog.setContentView(R.layout.custom_progress_dialog);
    }


    public static void setupWebView(final WebView webView, String data) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadData(data, "text/html; charset=utf-8", "utf-8");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView paramWebView, String url) {
                webView.loadUrl(url);
                return false;
            }

            public void onPageFinished(WebView paramWebView, String paramString) {
                super.onPageFinished(paramWebView, paramString);
            }
        });
        webView.setBackgroundColor(Color.TRANSPARENT);
    }

    public static void startActivity(Activity fromActivity, Class toActivity, boolean animation, boolean removeFromStack) {
        Intent intent = new Intent(fromActivity, toActivity);
        if (removeFromStack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        }
        fromActivity.startActivity(intent);
        if (animation)
            fromActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        fromActivity.finish();
    }
}
