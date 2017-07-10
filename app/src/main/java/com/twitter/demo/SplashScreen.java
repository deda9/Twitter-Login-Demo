package com.twitter.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.twitter.demo.ui.login.LoginUserActivity;
import com.twitter.demo.utilities.SharedPrefUtilis;
/**
 * Created by Bassem Qoulta (Deda) on  7/10/17.
 * Bassem.Qoulta@gmail.com
 * Basem083926@feng.bu.edu.eg
 * +201225361630
 */
public class SplashScreen extends AppCompatActivity {

    private ImageView logo;
    private int DELAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo = (ImageView) findViewById(R.id.iv_splash_logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!SharedPrefUtilis.getUserID().isEmpty() && !SharedPrefUtilis.getUserID().equals("none")) {
                    startActivity(new Intent(SplashScreen.this, LoginUserActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, LoginUserActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }
        }, DELAY_TIME);
    }

}