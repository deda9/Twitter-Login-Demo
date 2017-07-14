package com.twitter.demo.db;

import android.content.Context;


import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by Bassem Qoulta(Deda6) on 2/7/17.
 * Bassem.Qoulta@gmail.com
 */

public class BaseRealmManager {

    protected final String TAG = getClass().getSimpleName();
    private final String DB_NAME = "DEMOO";

    public  RealmConfiguration getRealmConfiguration(Context context) {
        Realm.init(context);
        return new RealmConfiguration
                .Builder()
                .name(DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public static void addOrUpdateAsync(Realm realm, final RealmModel realmObject,
                                 final RealmTransactionListener realmTransactionListener) {
        if(realm == null) return ;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmObject);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (realmTransactionListener != null)
                    realmTransactionListener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (realmTransactionListener != null && error != null)
                    realmTransactionListener.onError(error);
            }
        });
    }

   public static void addOrUpdateAsync(final Realm realm, final List<? extends RealmModel> realmObjects,
                          final RealmTransactionListener realmTransactionListener) {
       if(realm == null) return;
       realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmObjects);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (realmTransactionListener != null)
                    realmTransactionListener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (realmTransactionListener != null && error != null)
                    realmTransactionListener.onError(error);
            }
        });
    }

    public static void deleteAllAsync(Realm realm, final Class<? extends RealmModel> object,
                               final RealmTransactionListener realmTransactionListener) {
        if(realm == null) return ;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(object);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (realmTransactionListener != null)
                    realmTransactionListener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (realmTransactionListener != null && error != null)
                    realmTransactionListener.onError(error);
            }
        });
    }

    public static void deleteAsync(Realm realm, final RealmResults results,
                            final int locationOfItemInResultSet,
                            final RealmTransactionListener realmTransactionListener) {
        if(realm == null) return;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteFromRealm(locationOfItemInResultSet);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (realmTransactionListener != null)
                    realmTransactionListener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (realmTransactionListener != null && error != null)
                    realmTransactionListener.onError(error);
            }
        });
    }

    public static RealmResults<? extends RealmModel> findAll(Realm realm, Class<? extends RealmModel> realmClass) {
        if(realm == null) return null;
        return realm.where(realmClass).findAll();
    }
}
