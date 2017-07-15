package com.twitter.demo.ui.tabs;

import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.utilities.Constants;


/**
 * Created by Bassem Qoulta (Deda) on  10/20/16.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class SettingTab extends Tab{
    public SettingTab(TextView textView, ImageView imageView) {
        this.text = textView;
        this.imageView = imageView;
        this.name = Constants.tabsName.SETTINGS;
        this.normalBackgroundId = R.drawable.settings;
        this.pressedBackgroundId = R.drawable.setting_selected;
    }

}
