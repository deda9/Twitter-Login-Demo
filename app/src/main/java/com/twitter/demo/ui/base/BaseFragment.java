package com.twitter.demo.ui.base;

import android.support.v4.app.Fragment;
/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class BaseFragment extends Fragment {

    public final String TAG = getClass().getSimpleName();

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public boolean isInternetAvailable() {
        return getBaseActivity() != null && getBaseActivity().isInternetAvailable();
    }

    public void showProgressDialog() {
        getBaseActivity().showProgressDialog();

    }

    public void hideProgressDialog() {
        getBaseActivity().hideProgressDialog();
    }

    public void showNoNetworkErrorMessage() {
        getBaseActivity().showNoNetworkMessage();
    }

    public void showToast(String message) {
        getBaseActivity().showToast(message);
    }

}
