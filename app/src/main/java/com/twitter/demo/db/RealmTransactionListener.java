package com.twitter.demo.db;

/**
 * Created by m.aboelazm on 10/12/2016.
 * Mohamed Abulazm
 */

public interface RealmTransactionListener {
    void onSuccess();

    void onError(Throwable error);

}
