package com.twitter.demo.ui.changeLang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;
import android.widget.TextView;

import com.twitter.demo.R;
import com.twitter.demo.TwitterDemoApp;
import com.twitter.demo.ui.base.BaseActivity;
import com.twitter.demo.utilities.SharedPrefUtilis;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * this is the activity for switch the lang betwee arabic or english
 */
public class ChangeLangActivity extends BaseActivity {

    @BindView(R.id.tv_lang)
    TextView tvLang;
    @BindView(R.id.switch_lan)
    Switch switchLan;
    @BindString(R.string.arabic)
    String arabic;
    @BindString(R.string.english)
    String english;
    @BindView(R.id.tv_tool_bar_title)
    TextView tvToolBarTitle;
    @BindString(R.string.change_lang)
    String changeLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_lang);
        ButterKnife.bind(this);
        setPreviousLang();
        setToolBarTextView(tvToolBarTitle);
        setToolBarTitle(changeLang);
    }

    /**
     * Set the last lang user did choose it
     */
    private void setPreviousLang() {
        String lang = SharedPrefUtilis.getCurrentLang(this);
        if (lang.equals("ar"))
            switchToArabic();
        else
            switchToEnglish();
    }

    /**
     * when user click on the switch to choose lang
     */
    @OnCheckedChanged(R.id.switch_lan)
    public void onSwitchLang() {
        String lang = SharedPrefUtilis.getCurrentLang(this);
        if (lang.equals("ar"))
            switchToEnglish();
        else
            switchToArabic();
        restartApp();
    }

    /**
     * you need to restart the app to set the new lang which the user choose it .
     */
    private void restartApp() {
        showProgressDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }, 1000);
    }

    /**
     * Set the arabic lang
     */
    private void switchToArabic() {
        ((TwitterDemoApp) getApplication()).switchLanguage("ar");
        SharedPrefUtilis.setCurrentLang(this, "ar");
        tvLang.setText(arabic);
    }

    /**
     * set the english lang
     */
    private void switchToEnglish() {
        ((TwitterDemoApp) getApplication()).switchLanguage("en");
        SharedPrefUtilis.setCurrentLang(this, "en");
        tvLang.setText(english);
    }


    @OnClick(R.id.btn_back)
    public void onClick() {
        super.onBackPressed();
    }
}
