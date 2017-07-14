package com.twitter.demo.ui.followers;

import android.util.Log;

import com.twitter.demo.db.RealmManager;
import com.twitter.demo.db.RealmTransactionListener;
import com.twitter.demo.models.FollowerListResponse;

import io.realm.Realm;

import static com.twitter.sdk.android.core.Twitter.TAG;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

public class FollowersFragmentInteractorImp implements FollowersFragmentInteractor{

    private Realm mRealm;

    public FollowersFragmentInteractorImp(Realm mRealm) {
        this.mRealm = mRealm;
    }

    @Override
    public void saveFollowers(FollowerListResponse response) {
        RealmManager.saveFollowersResponse(mRealm, response, new RealmTransactionListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "FollowerListResponse is Successfully saved" );

            }

            @Override
            public void onError(Throwable error) {
                Log.d(TAG, "FollowerListResponse onError save" );
            }

        });
    }

    @Override
    public void onDestroy() {
        if (mRealm != null && !mRealm.isClosed())
            mRealm.close();
    }
}
