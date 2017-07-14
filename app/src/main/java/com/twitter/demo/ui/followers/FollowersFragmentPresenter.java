package com.twitter.demo.ui.followers;
/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

public interface FollowersFragmentPresenter {

    void getFollowersList(long nextCursor);

    void onDestroy();
}
