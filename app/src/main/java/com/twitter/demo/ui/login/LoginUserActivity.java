package com.twitter.demo.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.twitter.demo.R;
import com.twitter.demo.ui.base.BaseActivity;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * This activity is a container for the login fragment
 */
public class LoginUserActivity extends BaseActivity {

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        openLoginFragment();
    }

    /**
     * Open the login fragment which is responsible for login process
     */
    private void openLoginFragment() {
        loginFragment = new LoginFragment();
        replaceFragment("",loginFragment);
    }


    /**
     * when data came from the twitter login it pass them to ehe fragment ,
     * then fragment pass them for then presenter
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }
}
