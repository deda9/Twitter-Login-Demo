package com.twitter.demo.ui.followers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.demo.R;
import com.twitter.demo.ui.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Bassem Qoulta (Deda) on  7/13/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class FollowersFragment extends BaseFragment implements FollowersFragmentPresenterImp.FollowersView{

    private FollowersFragmentPresenter followersPresenter;

    public FollowersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers, container, false);
        ButterKnife.bind(this, view);
        followersPresenter = new FollowersFragmentPresenterImp(this, getActivity());
        return view;
    }
}
