package com.twitter.demo.ui.followers_details;

import android.content.Context;
import android.text.TextUtils;

import com.twitter.demo.utilities.SharedPrefUtilis;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.lang.ref.WeakReference;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class UserDetailsFragmentPresenterImp implements UserDetailsFragmentPresenter {

    private UserDetailsViews detailsViews;
    private UserDetailsFragmentInteractor interactor;
    private WeakReference<Context> weakReference;

    public UserDetailsFragmentPresenterImp(Context context, UserDetailsViews detailsViews) {
        weakReference = new WeakReference<>(context);
        this.detailsViews = detailsViews;
        interactor = new UserDetailsFragmentInteractorImp(weakReference.get());
    }

    @Override
    public void getUserTweets(String screenName) {
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(screenName)
                .build();

        if (this.detailsViews != null) {
            this.detailsViews.onSuccessTweets(userTimeline);
        }

        saveLoggedUserTweets(screenName, userTimeline);
    }

    /**
     * In this phase -> we will only save the logged user Tweets
     * <p>
     * TODO:: Save all the tweets for all the followers in the next phase
     *
     * @param screenName -> need to validate this screen name like the same for the loged one
     * @param userTimeline -> data which we will save them
     */
    private void saveLoggedUserTweets(String screenName, UserTimeline userTimeline) {
        if (!TextUtils.isEmpty(screenName)
                && screenName.equals(SharedPrefUtilis.getUserScreenName(weakReference.get()))
                && interactor != null) {
//            interactor.saveLoggedUserTweets(userTimeline);
        }
    }

    public interface UserDetailsViews {
        void onSuccessTweets(UserTimeline userTimeline);
    }
}
