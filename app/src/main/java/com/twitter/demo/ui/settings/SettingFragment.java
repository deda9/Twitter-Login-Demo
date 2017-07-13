package com.twitter.demo.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.demo.R;
import com.twitter.demo.ui.base.BaseFragment;
import com.twitter.demo.ui.changeLang.ChangeLangActivity;
import com.twitter.demo.ui.login.LoginUserActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bassem Qoulta (Deda) on  7/13/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class SettingFragment extends BaseFragment implements SettingPresenterImp.SettingView {

    SettingPresenter settingPresenter;

    public SettingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);
        ButterKnife.bind(this, view);
        settingPresenter = new SettingPresenterImp(this, getActivity());
        return view;
    }

    @Override
    public void onLogOut() {
        Intent intent = new Intent(getActivity(), LoginUserActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick({R.id.btn_change_lang, R.id.btn_log_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_lang:
                changeLanguage();
                break;
            case R.id.btn_log_out:
                logOutUser();
                break;
        }
    }

    private void logOutUser() {
        if(settingPresenter != null){
            settingPresenter.logOutUser();
        }
    }

    private void changeLanguage() {
        Intent intent = new Intent(getActivity(), ChangeLangActivity.class);
        startActivity(intent);
    }
}
