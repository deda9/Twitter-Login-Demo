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
public class LoginUserActivity extends BaseActivity {

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        openLoginFragment();
    }

    private void openLoginFragment() {
        loginFragment = new LoginFragment();
        replaceFragment("",loginFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }
}
