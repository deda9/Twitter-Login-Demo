package com.twitter.demo.ui.followers_details;

import android.os.Bundle;

import com.twitter.demo.R;
import com.twitter.demo.ui.base.BaseActivity;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class UserDetailsActivity extends BaseActivity {

    public static String USER_SCREEN_NAME_KEY = "screen_name_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_details);
        if(getIntent() != null && getIntent().getExtras() != null){
            setUserDetailsFragment();
        }
    }

    private void setUserDetailsFragment() {
        UserDetailsFragment fragment = new UserDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(USER_SCREEN_NAME_KEY, getIntent().getExtras().getString(USER_SCREEN_NAME_KEY));
        fragment.setArguments(bundle);

        replaceFragment(getResources().getString(R.string.tab_profile_title), fragment);

    }
}
