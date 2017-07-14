package com.twitter.demo.ui.base;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class BaseFragment extends Fragment {

    public boolean isLoadingMore;
    public boolean isLastPage;
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
    public void setupPagination(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = 0;

                    if (layoutManager instanceof GridLayoutManager)
                        pastVisiblesItems = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();

                    else if (layoutManager instanceof LinearLayoutManager)
                        pastVisiblesItems = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

                    if (!isLoadingMore && !isLastPage) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && pastVisiblesItems >= 0) {
                            isLoadingMore = true;
                            loadMoreData();
                        }
                    }
                }
            }
        });
    }

    public void loadMoreData() {
        // we will override this
    }

}
