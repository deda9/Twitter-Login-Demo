package com.twitter.demo.ui.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.demo.R;


/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 * <p/>
 * we will use the archetecture MVP it's more powerfull .
 * so Please go in details and see this link
 * https://guides.codepath.com/android/Architecture-of-Android-Apps
 */

/**
 * this activity is the base one for all the fuc which common used over the other activities
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseViews {

    private Toolbar mToolbar;
    private final String TAG = getClass().getSimpleName();
    private ProgressDialog dialog;
    TextView toolBarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //MARK: LifeCycle Calls
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * check the network is available or not
     *
     * @return true or false
     */
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void setToolBarTextView(TextView toolBarTextView) {
        this.toolBarTextView = toolBarTextView;
    }

    public void setToolBarTitle(String title) {
        if (toolBarTextView != null)
            toolBarTextView.setText(title);
        setTitle("");
    }

    //MARK: Views calls
    @Override
    public void showNoNetworkMessage() {
        Toast.makeText(this, getResources().getString(R.string.error_no_internet_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGeneralError() {
        Toast.makeText(this, getResources().getString(R.string.try_again), Toast.LENGTH_SHORT).show();
    }

    /**
     * show custom progress dialog
     */
    @Override
    public void showProgressDialog() {
        if (dialog == null)
            dialog = new ProgressDialog(this);
        dialog.show();
        if (dialog != null && dialog.getWindow() != null)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.custom_progress_dialog);

    }

    @Override
    public void hideProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onConfigurationChanged(Configuration paramConfiguration) {
        super.onConfigurationChanged(paramConfiguration);
    }

    /** used for replace the frgament
     * @param title title to set it in the tool bar(Custom one too)
     * @param fragment to replace it
     */
    protected void replaceFragment(String title, Fragment fragment) {
        if (toolBarTextView != null)
            toolBarTextView.setText(title);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getName())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
