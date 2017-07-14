package com.twitter.demo.ui.followers_details;

import android.os.Bundle;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class UserDetailsActivity extends BaseActivity {

    public static String USER_SCREEN_NAME_KEY = "user_screen_name_key";
    public static String USER_NAME_KEY = "user_name_key";

    @BindView(R.id.tv_tool_bar_title)
    TextView tvToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_details);
        ButterKnife.bind(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            setToolBarTextView(tvToolBarTitle);
            setToolBarTitle(getIntent().getExtras().getString(USER_NAME_KEY));
            setUserDetailsFragment();
        }
    }

    private void setUserDetailsFragment() {
        UserDetailsFragment fragment = new UserDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putString(USER_SCREEN_NAME_KEY, getIntent().getExtras().getString(USER_SCREEN_NAME_KEY));
        fragment.setArguments(bundle);

        replaceFragment(getIntent().getExtras().getString(USER_NAME_KEY), fragment);
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        super.onBackPressed();
    }
}
