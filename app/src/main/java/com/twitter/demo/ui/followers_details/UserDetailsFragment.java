package com.twitter.demo.ui.followers_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.models.UserHeaderDataModel;
import com.twitter.demo.ui.base.BaseFragment;
import com.twitter.demo.utilities.PicassoCache;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.twitter.demo.ui.followers_details.UserDetailsActivity.USER_HEADER_MODEL_KEY;
import static com.twitter.demo.ui.followers_details.UserDetailsActivity.USER_SCREEN_NAME_KEY;

/**
 * Created by Bassem Qoulta (Deda) on  7/14/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class UserDetailsFragment extends BaseFragment
        implements UserDetailsFragmentPresenterImp.UserDetailsViews, SwipeRefreshLayout.OnRefreshListener {

    private UserDetailsFragmentPresenter presenter;
    private UserHeaderDataModel userHeaderDataModel;

    @BindView(R.id.list_followers)
    ListView listFollowers;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    CircleImageView headerProfileImage;
    ImageView headerBackground;
    TextView headerUserName;

    public UserDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            userHeaderDataModel = getArguments().getParcelable(USER_HEADER_MODEL_KEY);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new UserDetailsFragmentPresenterImp(getActivity(), this);
        getUserTweets(getArguments().getString(USER_SCREEN_NAME_KEY));
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getUserTweets(String screenName) {
        if (presenter != null) {
            presenter.getUserTweets(screenName);
        }
    }

    @Override
    public void onSuccessTweets(UserTimeline userTimeline) {
        if(swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter
                .Builder(getActivity())
                .setTimeline(userTimeline)
                .build();

        listFollowers.setVisibility(View.VISIBLE);
        listFollowers.setAdapter(adapter);

        if(listFollowers.getHeaderViewsCount() == 0){
            listFollowers.addHeaderView(getHeader());
        }
    }

    private View getHeader() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.follower_header, null);
        headerProfileImage = (CircleImageView) header.findViewById(R.id.iv_profile);
        headerBackground = (ImageView) header.findViewById(R.id.iv_background);
        headerUserName = (TextView) header.findViewById(R.id.tv_user_name);
        setHeaderData();
        return header;
    }

    private void setHeaderData() {
        if (userHeaderDataModel == null) return;
        headerUserName.setVisibility(View.VISIBLE);
        headerUserName.setText(userHeaderDataModel.userName);
        PicassoCache.getPicassoInstance(getActivity())
                .load(userHeaderDataModel.profileUrl)
                .placeholder(R.drawable.avatar)
                .into(headerProfileImage);

        PicassoCache.getPicassoInstance(getActivity())
                .load(userHeaderDataModel.backgroundUrl)
                .placeholder(R.drawable.image_details_placeholder)
                .into(headerBackground);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getUserTweets(getArguments().getString(USER_SCREEN_NAME_KEY));
    }
}
