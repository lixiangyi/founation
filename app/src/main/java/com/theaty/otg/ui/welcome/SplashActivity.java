package com.theaty.otg.ui.welcome;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.theaty.otg.R;
import com.theaty.otg.system.DatasStore;
import com.theaty.otg.ui.MainActivity;

import butterknife.BindView;
import foundation.base.activity.BaseActivity;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.splashIv)
    ImageView splashIv;

    @Override
    protected View onCreateContentView() {
        return inflateContentView(R.layout.activity_splash);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        startWelcome();
    }

    private void startWelcome() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (DatasStore.isFirstLaunch()) {
                    startActivity(new Intent(mContext, WelcomeActivity.class));

                } else if (DatasStore.isLogin()) {
                    MainActivity.into(SplashActivity.this);
                } else {
                    MainActivity.into(SplashActivity.this);
                }
                finish();
            }
        }).start();
    }


}
