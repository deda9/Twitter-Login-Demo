package com.twitter.demo.ui.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.models.UserHeaderDataModel;
import com.twitter.demo.ui.base.BaseActivity;
import com.twitter.demo.ui.followers.FollowersFragment;
import com.twitter.demo.ui.profile.ProfileFragment;
import com.twitter.demo.ui.settings.SettingFragment;
import com.twitter.demo.ui.tabs.FollowersTab;
import com.twitter.demo.ui.tabs.ProfileTab;
import com.twitter.demo.ui.tabs.SettingTab;
import com.twitter.demo.ui.tabs.Tab;
import com.twitter.demo.utilities.Constants;
import com.twitter.demo.utilities.SharedPrefUtilis;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.twitter.demo.ui.followers_details.UserDetailsActivity.USER_HEADER_MODEL_KEY;
import static com.twitter.demo.ui.followers_details.UserDetailsActivity.USER_SCREEN_NAME_KEY;

public class HomeActivity extends BaseActivity {
    private List<Tab> tabsList;
    private ProfileFragment profileFragment;

    @BindView(R.id.tv_tool_bar_title)
    TextView tvToolBarTitle;
    @BindView(R.id.iv_profile)
    ImageView ivProfile;
    @BindView(R.id.tv_profile)
    TextView tvProfile;
    @BindView(R.id.iv_followers)
    ImageView ivFollowers;
    @BindView(R.id.tv_followers)
    TextView tvFollowers;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindString(R.string.tab_profile_title)
    public String profileTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setToolBarTextView(tvToolBarTitle);
        setToolBarTitle(profileTitle);
        setupTabs();
        replaceFragment(getResources().getString(R.string.tab_profile_title), getProfileFragment());
        changeTabBackground(Constants.tabsName.PROFILE);
    }


    private ProfileFragment getProfileFragment() {
        if (profileFragment == null)
            profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USER_SCREEN_NAME_KEY, SharedPrefUtilis.getUserScreenName(this));
        bundle.putParcelable(USER_HEADER_MODEL_KEY, getUserHeaderModel());
        profileFragment.setArguments(bundle);
        return profileFragment;
    }

    private UserHeaderDataModel getUserHeaderModel() {
        UserHeaderDataModel userModel = new UserHeaderDataModel();
        userModel.userName = SharedPrefUtilis.getUserName(this);
        userModel.profileUrl = SharedPrefUtilis.getUserProfileUrl(this);
        userModel.backgroundUrl = SharedPrefUtilis.getUserBackgroundUrl(this);
        return userModel;
    }

    @OnClick(R.id.profile_tab)
    public void openProfile() {
        replaceFragment(getResources().getString(R.string.tab_profile_title), getProfileFragment());
        changeTabBackground(Constants.tabsName.PROFILE);
    }

    @OnClick(R.id.followers_tab)
    public void openFollowers() {
        replaceFragment(getResources().getString(R.string.tab_followers_title), new FollowersFragment());
        changeTabBackground(Constants.tabsName.FOLLOWERS);
    }

    @OnClick(R.id.setting_tab)
    public void openSettings() {
        replaceFragment(getResources().getString(R.string.tab_setting_titles), new SettingFragment());
        changeTabBackground(Constants.tabsName.SETTINGS);
    }

    public void setupTabs() {
        tabsList = new ArrayList<>();
        tabsList.add(new ProfileTab(tvProfile, ivProfile));
        tabsList.add(new FollowersTab(tvFollowers, ivFollowers));
        tabsList.add(new SettingTab(tvSetting, ivSetting));
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof ProfileFragment) {
            super.onBackPressed();
            finish();
        } else {
            replaceFragment(getResources().getString(R.string.tab_profile_title), getProfileFragment());
            changeTabBackground(Constants.tabsName.PROFILE);
        }
    }

    public void changeTabBackground(Constants.tabsName tabName) {
        for (int i = 0; i < tabsList.size(); i++) {
            if (!tabsList.get(i).name.equals(tabName)) {
                tabsList.get(i).imageView.setBackgroundResource(tabsList.get(i).normalBackgroundId);
                tabsList.get(i).text.setTextColor(tabsList.get(i).normalColor);
            } else {
                tabsList.get(i).imageView.setBackgroundResource(tabsList.get(i).pressedBackgroundId);
                tabsList.get(i).text.setTextColor(tabsList.get(i).pressedColor);
            }
        }
    }
}
