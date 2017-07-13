package com.twitter.demo.ui.tabs;

import android.widget.ImageView;
import android.widget.TextView;

import com.a24.protein.R;
import com.a24.protein.utilities.Constants;

/**
 * Created by Bassem Qoulta (Deda) on  10/20/16.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class ProfileTab extends Tab {
    public ProfileTab(TextView textView, ImageView imageView) {
        this.text = textView;
        this.imageView = imageView;
        this.name = Constants.tabsName.HOME;
        this.normalBackgroundId = R.drawable.ic_action_home2;
        this.pressedBackgroundId = R.drawable.ic_action_home;

    }
}
