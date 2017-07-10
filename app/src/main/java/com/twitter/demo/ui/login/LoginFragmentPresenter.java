package com.twitter.demo.ui.login;

import android.content.Intent;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public interface LoginFragmentPresenter {

    void onLoginSuccess();

    void onLoginFail();

    void loginWithTwitter();

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
