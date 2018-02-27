package com.theaty.otg.ui.circle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theaty.otg.R;
import com.theaty.otg.ui.MainActivity;

import foundation.base.fragment.BaseFragment;
import foundation.notification.NotificationCenter;
import foundation.notification.NotificationListener;

import static com.theaty.otg.notification.NotificationKey.notificAdapterChanges;

/**
 * Created by 李祥义 on 2017/2/26.
 */

public class CircleFragment extends BaseFragment implements NotificationListener {

    @Override
    public void onAttach(Activity activity) {
        this.mActivity = (MainActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        return root;
    }

    @Override
    protected View onCreateContentView() {
        return inflateContentView(R.layout.fragment_home);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        NotificationCenter.defaultCenter.addListener(notificAdapterChanges, this);
    }

    @SuppressLint({"ClickableViewAccessibility", "InlinedApi"})
    private void initView() {


    }

    @Override
    public boolean onNotification(Notification notification) {
        if (notification.key.equals(notificAdapterChanges)) {
            //curpage = 1;

            return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NotificationCenter.defaultCenter.removeListener(notificAdapterChanges, this);
    }




    @Override
    public void onResume() {
        super.onResume();

    }


}
