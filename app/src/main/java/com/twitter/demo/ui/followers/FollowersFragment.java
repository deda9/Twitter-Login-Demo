package com.twitter.demo.ui.followers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.twitter.demo.R;
import com.twitter.demo.models.FollowerListResponse;
import com.twitter.demo.models.UserHeaderDataModel;
import com.twitter.demo.ui.adapters.FollowersAdapter;
import com.twitter.demo.ui.base.BaseFragment;
import com.twitter.demo.ui.followers_details.UserDetailsActivity;
import com.twitter.demo.utilities.RecyclerViewItemClickListener;
import com.twitter.sdk.android.core.models.User;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twitter.demo.ui.followers_details.UserDetailsActivity.USER_HEADER_MODEL_KEY;
import static com.twitter.demo.ui.followers_details.UserDetailsActivity.USER_SCREEN_NAME_KEY;

/**
 * Created by Bassem Qoulta (Deda) on  7/13/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * this is the frgament which is responsible for get the user followers
 */
public class FollowersFragment extends BaseFragment
        implements FollowersFragmentPresenterImp.FollowersView,
        RecyclerViewItemClickListener<User>, SwipeRefreshLayout.OnRefreshListener {

    private FollowersFragmentPresenter followersPresenter;
    private FollowersAdapter adapter;
    private long nextCursor = -1;
    @BindString(R.string.try_again)
    public String tryAgain;
    @BindView(R.id.rc_followers)
    RecyclerView rcFollowers;
    @BindView(R.id.pr_loading_more)
    ProgressBar prLoadingMore;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    public FollowersFragment() {}

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
        if(!isInternetAvailable()){
            showNoNetworkErrorMessage();
            return;
        }
        getFollowersList();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * Talk to the presenter to get the follower for the logged user
     */
    private void getFollowersList() {
        if (getBaseActivity() != null)
            getBaseActivity().showProgressDialog();
        followersPresenter.getFollowersList(nextCursor);
    }

    /**
     * this is called when the follower list is loaded by the right way.
     * set the data to the adapter
     * cancel the swipe if so
     * cancel load more if so
     * @param data followers list
     */
    @Override
    public void setupRecyclerView(FollowerListResponse data) {
        if (adapter == null) {
            adapter = new FollowersAdapter(data.getUsers(), this);
            rcFollowers.setHasFixedSize(true);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            setupPagination(rcFollowers, manager);
            rcFollowers.setLayoutManager(manager);
            rcFollowers.setAdapter(adapter);
        } else {
            adapter.addAll(data.getUsers());
            prLoadingMore.setVisibility(View.GONE);
        }
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

        if (isLoadingMore)
            isLoadingMore = false;
    }

    /**
     * This the override func whenc come form the setting pagination for the recyclerview
     */
    @Override
    public void loadMoreData() {
        if (this.nextCursor != 0) {
            prLoadingMore.setVisibility(View.VISIBLE);
            followersPresenter.getFollowersList(this.nextCursor);
        }
    }

    @Override
    public void showSomeThingWrong() {
        getBaseActivity().showToast(tryAgain);
    }

    /**
     * Based on the twiiter Doc,
     * you need to send the next cursor to get the next items for the pagaintion
     *
     * @param nextCursor which will sent to indicate tthe next items
     */
    @Override
    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    @Override
    public void hideProgressDialogForFollowers() {
        if (getBaseActivity() != null) {
            getBaseActivity().hideProgressDialog();
        }
    }

    /**
     * Came form the adapter when the user click on the cell of the recyclerview
     * @param position the postion of the cell
     * @param user the item which click ont he cell contair for it
     */
    @Override
    public void onItemClicked(int position, User user) {
        Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
        intent.putExtra(USER_SCREEN_NAME_KEY, user.screenName);
        intent.putExtra(USER_HEADER_MODEL_KEY, getUserHeaderModel(user));
        startActivity(intent);
    }

    private UserHeaderDataModel getUserHeaderModel(User user) {
        UserHeaderDataModel userModel = new UserHeaderDataModel();
        userModel.userName = user.name;
        userModel.backgroundUrl = user.profileBackgroundImageUrl;
        userModel.profileUrl = user.profileImageUrl;
        return userModel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyPresenter();
    }

    private void destroyPresenter() {
        if (followersPresenter != null) {
            followersPresenter.onDestroy();
        }
    }

    /**
     * Handle the swipe and load new data
     */
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        setNextCursor(-1);
        followersPresenter.getFollowersList(nextCursor);
        if (adapter != null)
            adapter.removeAll();
    }
}
