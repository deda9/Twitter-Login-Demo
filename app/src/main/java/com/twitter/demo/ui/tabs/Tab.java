package com.twitter.demo.ui.tabs;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.demo.utilities.Constants;

/**
 * Created by Bassem Qoulta (Deda) on  10/20/16.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class Tab {

    public Constants.tabsName name;
    public int normalBackgroundId;
    public int pressedBackgroundId;
    public int normalColor = Color.WHITE;
    public int pressedColor = Color.BLACK;
    public TextView text;
    public ImageView imageView;
}
