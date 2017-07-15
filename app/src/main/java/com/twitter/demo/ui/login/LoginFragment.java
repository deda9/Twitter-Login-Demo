package com.twitter.demo.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.demo.R;
import com.twitter.demo.ui.base.BaseFragment;
import com.twitter.demo.ui.home.HomeActivity;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */

/**
 * This Fragment is responsible for login the user by his twitter account .
 * it open twitter login web page.
 */
public class LoginFragment extends BaseFragment implements LoginFragmentPresenterImp.LoginView {

    private LoginFragmentPresenter loginFragmentPresenter;
    @BindString(R.string.on_fail_twitter_login)
    public String twitterFailLoginMessage;
    @BindString(R.string.on_success_twitter_login)
    public String twitterSuccessLoginMessage;

    public LoginFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        loginFragmentPresenter = new LoginFragmentPresenterImp(this, getActivity());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragmentPresenter.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * this called when the login is success and start the home activity
     */
    @Override
    public void onSuccessTwitterLogin() {
        getBaseActivity().showToast(twitterSuccessLoginMessage);
        hideProgressDialog();
        startActivity(new Intent(getActivity(), HomeActivity.class));
    }

    @Override
    public void onFailTwitterLogin() {
        getBaseActivity().showToast(twitterFailLoginMessage);
    }


    /** its good to use your customize button rather twitter one
     * when click on button we ask the presenter to do the login process and once success
     * it call the onSuccessTwitterLogin() function to go on
     */
    @OnClick(R.id.login_button)
    public void onTwitterLoginClick() {
        if(!isInternetAvailable()){
            showNoNetworkErrorMessage();
            return;
        }
        if(loginFragmentPresenter != null){
            loginFragmentPresenter.loginWithTwitter();
        }
    }
}
