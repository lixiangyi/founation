package com.theaty.otg.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.theaty.otg.R;
import com.theaty.otg.system.UpdateManager;
import com.theaty.otg.ui.circle.CircleFragment;
import com.theaty.otg.ui.home.HomeFragment;
import com.theaty.otg.ui.mine.MineFragment;
import com.theaty.otg.ui.selectpet.SelectPetFragment;
import com.theaty.otg.ui.upgoods.UpGoodsFragment;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import foundation.base.activity.BaseFragmentActivity;
import foundation.base.fragment.BaseFragment;
import foundation.notification.NotificationCenter;
import foundation.notification.NotificationListener;
import foundation.util.DoubleClickExitUtils;

import static com.theaty.otg.notification.NotificationKey.filterMainActivity;

/**
 * 主页面
 */
public class MainActivity extends BaseFragmentActivity implements NotificationListener {

    @BindView(R.id.tabRL0)
    RelativeLayout tabRL0;
    @BindView(R.id.tabRL1)
    RelativeLayout tabRL1;
    @BindView(R.id.tabRL2)
    RelativeLayout tabRL2;
    @BindView(R.id.tabRL3)
    RelativeLayout tabRL3;
    @BindView(R.id.tabRL4)
    RelativeLayout tabRL4;

    @BindView(R.id.iv_0)
    ImageView iv_0;
    @BindView(R.id.iv_1)
    ImageView iv_1;
    @BindView(R.id.iv_2)
    ImageView iv_2;
    @BindView(R.id.iv_3)
    ImageView iv_3;
    @BindView(R.id.iv_4)
    ImageView iv_4;

    @BindView(R.id.tv_0)
    TextView tv_0;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.tv_4)
    TextView tv_4;

    @BindView(R.id.cicle_num_tv)
    TextView cicle_num_tv;
    @BindView(R.id.message_num_tv)
    TextView message_num_tv;

    private DoubleClickExitUtils duClickExitHelper;
    public static int mSrceenWidth;
    public static int mSrceenHeight;

    private ArrayList<Class<? extends BaseFragment>> fragments;
    private boolean isLogin;
    public static int curNotes = 0;
    public static int curMessage = 0;

    @Override
    protected View onCreateContentView() {
        return inflateContentView(R.layout.activity_main);
    }

    @Override
    protected ArrayList<Class<? extends BaseFragment>> fragmentClasses() {
        fragments = new ArrayList<>();
        fragments.add(SelectPetFragment.class);
        fragments.add(UpGoodsFragment.class);
        fragments.add(HomeFragment.class);
        fragments.add(CircleFragment.class);
        fragments.add(MineFragment.class);

        return fragments;
    }

    @Override
    protected int containerViewId() {
        return R.id.contentFL;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        getScreenWidth();
        enableTabItem(2);
        NotificationCenter.defaultCenter.addListener(filterMainActivity, this);
        duClickExitHelper = new DoubleClickExitUtils(this);
        updateApp();
        showLoading();

    }
    public static void into(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();


    }



    @Override
    protected void onResume() {
        super.onResume();



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationCenter.defaultCenter.removeListener(filterMainActivity, this);
    }

    private void updateApp() {
        UpdateManager.checkUpdate(this);
    }

    public void enableTabItem(int position) {
        if (0 > position || 4 < position)
            return;

        iv_0.setEnabled(false);
        iv_1.setEnabled(false);
        iv_2.setEnabled(false);
        iv_3.setEnabled(false);
        iv_4.setEnabled(false);

        tv_0.setEnabled(false);
        tv_1.setEnabled(false);
        tv_3.setEnabled(false);
        tv_4.setEnabled(false);
        switch (position) {
            case 0:
                iv_0.setEnabled(true);
                tv_0.setEnabled(true);
                selectPage(0);
                break;
            case 1:
                iv_1.setEnabled(true);
                tv_1.setEnabled(true);
                selectPage(1);
                break;
            case 2:
                iv_2.setEnabled(true);
                selectPage(2);
                break;
            case 3:
                iv_3.setEnabled(true);
                tv_3.setEnabled(true);
                selectPage(3);
                break;
            case 4:
                iv_4.setEnabled(true);
                tv_4.setEnabled(true);
                selectPage(4);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return duClickExitHelper.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tabRL0, R.id.tabRL1, R.id.tabRL2, R.id.tabRL3, R.id.tabRL4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tabRL0:
                enableTabItem(0);
                break;
            case R.id.tabRL1:
                enableTabItem(1);
                break;
            case R.id.tabRL2:
                enableTabItem(2);
                break;
            case R.id.tabRL3:
                enableTabItem(3);
                break;
            case R.id.tabRL4:
                enableTabItem(4);
                break;
        }
    }

    private void getScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mSrceenWidth = metrics.widthPixels;
        mSrceenHeight = metrics.heightPixels;
    }



    @Override

    public boolean onNotification(Notification notification) {
        if (notification.key.equals(filterMainActivity)) {
            enableTabItem(4);
            return true;
        }

        return false;
    }






}
