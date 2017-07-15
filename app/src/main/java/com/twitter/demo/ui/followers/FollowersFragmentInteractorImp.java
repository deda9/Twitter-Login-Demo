package com.twitter.demo.ui.followers;

import com.twitter.demo.models.FollowerListResponse;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * This for the db layer
 */
public class FollowersFragmentInteractorImp implements FollowersFragmentInteractor{

    public FollowersFragmentInteractorImp() {
    }

    @Override
    public void saveFollowers(FollowerListResponse response) {
        //TODO:: next phase add cashe layers
    }

    @Override
    public void onDestroy() {
    }
}
