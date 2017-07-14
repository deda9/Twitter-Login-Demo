package com.twitter.demo.ui.followers_details;

import android.os.Bundle;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.models.UserHeaderDataModel;
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
    public static String USER_HEADER_MODEL_KEY = "user_header_model_key";
    private UserHeaderDataModel userHeaderDataModel;
    @BindView(R.id.tv_tool_bar_title)
    TextView tvToolBarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_details);
        ButterKnife.bind(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            setToolBarTextView(tvToolBarTitle);
            userHeaderDataModel = getIntent().getExtras().getParcelable(USER_HEADER_MODEL_KEY);

            if (userHeaderDataModel != null) {
                setToolBarTitle(userHeaderDataModel.userName);
            }
            setUserDetailsFragment();
        }
    }

    private void setUserDetailsFragment() {
        UserDetailsFragment fragment = new UserDetailsFragment();
        String userName = userHeaderDataModel.userName == null ? "Profile" : userHeaderDataModel.userName;
        Bundle bundle = new Bundle();
        bundle.putString(USER_SCREEN_NAME_KEY, getIntent().getExtras().getString(USER_SCREEN_NAME_KEY));
        bundle.putParcelable(USER_HEADER_MODEL_KEY, userHeaderDataModel);
        fragment.setArguments(bundle);
        replaceFragment(userName, fragment);
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        super.onBackPressed();
    }
}
