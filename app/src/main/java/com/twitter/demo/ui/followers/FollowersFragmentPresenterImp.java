package com.twitter.demo.ui.followers;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class FollowersFragmentPresenterImp implements FollowersFragmentPresenter {

    private WeakReference<Context> weakReference;
    private FollowersView followersView;

    public FollowersFragmentPresenterImp(FollowersView followersView, Context context) {
        this.weakReference = new WeakReference<Context>(context);
        this.followersView = followersView;
    }

    public interface FollowersView{

    }
}
