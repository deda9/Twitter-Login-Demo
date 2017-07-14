package com.twitter.demo.ui.followers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.demo.R;
import com.twitter.demo.models.FollowerListResponse;
import com.twitter.demo.ui.adapters.FollowersAdapter;
import com.twitter.demo.ui.base.BaseFragment;
import com.twitter.demo.utilities.RecyclerViewItemClickListener;
import com.twitter.sdk.android.core.models.User;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bassem Qoulta (Deda) on  7/13/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class FollowersFragment extends BaseFragment implements FollowersFragmentPresenterImp.FollowersView, RecyclerViewItemClickListener<User> {

    @BindString(R.string.try_again)
    public String tryAgain;
    @BindView(R.id.rc_followers)
    RecyclerView rcFollowers;

    private FollowersFragmentPresenter followersPresenter;
    private FollowersAdapter adapter;
    private long nextCursor = -1;


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        followersPresenter.getFollowersList(nextCursor);
    }

    @Override
    public void setupRecyclerView(FollowerListResponse data) {
        if(adapter == null){
            adapter = new FollowersAdapter(data.getUsers(),this);
            rcFollowers.setHasFixedSize(true);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            setupPagination(rcFollowers, manager);
            rcFollowers.setLayoutManager(manager);
            rcFollowers.setAdapter(adapter);
        }else{
            adapter.addAll(data.getUsers());
        }
    }

    @Override
    public void loadMoreData() {
        if(this.nextCursor != 0){
            followersPresenter.getFollowersList(this.nextCursor);
        }
    }

    @Override
    public void showSomeThingWrong() {
        getBaseActivity().showToast(tryAgain);
    }

    @Override
    public void setNextCursor(long nextCursor) {
        //for pagination
        this.nextCursor = nextCursor;
    }

    @Override
    public void onItemClicked(int position, User user) {

    }
}
