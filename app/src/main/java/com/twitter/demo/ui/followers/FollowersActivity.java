package com.twitter.demo.ui.followers;

import android.app.ListActivity;
import android.os.Bundle;

import com.twitter.demo.R;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class FollowersActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        //For more details, you can follow this link
        //https://dev.twitter.com/twitterkit/android/show-timelines

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("Deda_9")
                .build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
    }
}
