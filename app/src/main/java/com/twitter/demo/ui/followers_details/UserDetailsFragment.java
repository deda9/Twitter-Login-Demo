package com.twitter.demo.ui.followers_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.ui.base.BaseFragment;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twitter.demo.ui.followers_details.UserDetailsActivity.USER_SCREEN_NAME_KEY;

/**
 * Created by Bassem Qoulta (Deda) on  7/14/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */


public class UserDetailsFragment extends BaseFragment implements UserDetailsFragmentPresenterImp.UserDetailsViews {

    private UserDetailsFragmentPresenter presenter;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.list_followers)
    ListView listFollowers;

    public UserDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new UserDetailsFragmentPresenterImp(getActivity(), this);
        if(getArguments() != null){
            getUserTweets(getArguments().getString(USER_SCREEN_NAME_KEY));
        }

    }

    private void getUserTweets(String screenName) {
        if (presenter != null) {
            presenter.getUserTweets(screenName);
        }
    }

    @Override
    public void onSuccessTweets(UserTimeline userTimeline) {
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(userTimeline)
                .build();

        tvEmpty.setVisibility(View.GONE);
        listFollowers.setVisibility(View.VISIBLE);
        listFollowers.setAdapter(adapter);
    }
}
