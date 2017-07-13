package com.twitter.demo.ui.settings;

import android.content.Context;

import com.twitter.demo.utilities.SharedPrefUtilis;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/13/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class SettingPresenterImp implements SettingPresenter {

    private WeakReference<Context> weakReference;
    private SettingView settingView;

    public SettingPresenterImp(SettingView settingView, Context context) {
        this.weakReference = new WeakReference<Context>(context);
        this.settingView = settingView;
    }

    @Override
    public void logOutUser() {
        SharedPrefUtilis.clearAllShared(weakReference.get());
        if (this.settingView != null) {
            this.settingView.onLogOut();
        }
    }

    public interface SettingView {
        void onLogOut();

    }
}
