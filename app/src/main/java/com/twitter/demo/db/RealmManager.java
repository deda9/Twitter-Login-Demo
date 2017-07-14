package com.twitter.demo.db;

import com.twitter.demo.models.FollowerListResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by m.aboelazm on 11/23/2015.
 * mohamed.aboelazm@apptcom.com
 * Mohamed Abulazm: mhabulazm@gmail.com
 * <p/>
 * Get Realm Configuration
 * Write Your Queries Here
 */

public class RealmManager extends BaseRealmManager {

    //Overview
    public static void saveFollowersResponse(Realm realm, final FollowerListResponse followerListResponse,
                                    final RealmTransactionListener realmTransactionListener) {
        addOrUpdateAsync(realm, followerListResponse, realmTransactionListener);
    }

    public static List<FollowerListResponse> getFollowersResponse(Realm realm) {
        RealmQuery<FollowerListResponse> query = realm.where(FollowerListResponse.class);
        return query.findAll();
    }

    public static void deleteFollowerListResponse(Realm realm, RealmTransactionListener realmTransactionListener) {
        deleteAllAsync(realm, FollowerListResponse.class, realmTransactionListener);
    }


}
