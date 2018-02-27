package com.theaty.otg.ui.home;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.theaty.otg.R;

import java.util.HashMap;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import foundation.base.fragment.BaseFragment;
import foundation.toast.ToastUtil;


/**
 * Created by 李祥义 on 2017/2/26.
 */

public class HomeFragment extends BaseFragment {

    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    UsbManager mUsbManager;
    PendingIntent mPermissionIntent;
    @BindView(R.id.usb)
    Button usb;
    @BindView(R.id.device)
    TextView device;
    @BindView(R.id.content)
    TextView content;
    Unbinder unbinder;
    //mUsbReceiver只是一个普通的广播，根据action，去分别处理对应的事件。
    BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_USB_PERMISSION://接受到自定义广播
                    synchronized (this) {
                        UsbDevice usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {  //允许权限申请
                            if (usbDevice != null) {
                                //Do something

                                setDevice(usbDevice);
                            }else {
                                ToastUtil.showToast("读取失败");
                            }
                        } else {
                            ToastUtil.showToast("用户未授权，读取失败");
                        }
                    }
                    break;
                case UsbManager.ACTION_USB_DEVICE_ATTACHED://接收到存储设备插入广播
                    UsbDevice device_add = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device_add != null) {
                        ToastUtil.showToast("接收到存储设备插入广播");
                    } else {
                        ToastUtil.showToast("未接收到存储设备插入广播");
                    }
                    break;
                case UsbManager.ACTION_USB_DEVICE_DETACHED://接收到存储设备拔出广播
                    UsbDevice device_remove = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device_remove != null) {
                        ToastUtil.showToast("接收到存储设备拔出广播");
//拔出或者碎片 Activity销毁时 释放引用
//device.close();
                    } else {
                        ToastUtil.showToast("未接收到存储设备拔出广播");
                    }
                    break;
            }
        }
    };
    private UsbDevice mDevice;
    private UsbEndpoint mEndpointIntr;
    private UsbDeviceConnection mConnection;

    protected View onCreateContentView() {
        return inflateContentView(R.layout.fragment_home);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("USB OTG");
        setRightTitle("usb信息");

//监听otg插入 拔出
//        IntentFilter usbDeviceStateFilter = new IntentFilter();
//        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);

        //注册监听自定义广播
//        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
//        getActivity().registerReceiver(mUsbReceiver, filter);
        UsbAdmin(getActivity());

    }

    public void UsbAdmin(Context context) {
        mUsbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        context.registerReceiver(mUsbReceiver, filter);
    }

    @Override
    protected void goNext() {
        super.goNext();
        if (mDevice!=null) {
            device.setText("姓名"+mDevice.getDeviceName()+"ID:"+mDevice.getDeviceId()+"getVendorId"+mDevice.getVendorId()+"ProductId"+
            mDevice.getProductId());
        }else {
            ToastUtil.showToast("未连接设备");
        }
    }


    public void openUsb() {
        if (mDevice != null) {
            setDevice(mDevice);
            if (mConnection == null) {
                HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
                Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

                while (deviceIterator.hasNext()) {
                    UsbDevice device = deviceIterator.next();
                    mUsbManager.requestPermission(device, mPermissionIntent);
                }
            }
        } else {
            HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
            Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();

            while (deviceIterator.hasNext()) {
                UsbDevice device = deviceIterator.next();
                mUsbManager.requestPermission(device, mPermissionIntent);
            }
        }
    }

    private void setDevice(UsbDevice device) {
        if (device != null) {
            UsbInterface intf = null;
            UsbEndpoint ep = null;

            int InterfaceCount = device.getInterfaceCount();
            int j;

            mDevice = device;
            for (j = 0; j < InterfaceCount; j++) {
                int i;

                intf = device.getInterface(j);
                Log.i(TAG, "接口是:" + j + "类是:" + intf.getInterfaceClass());
                if (intf.getInterfaceClass() == 7) {
                    int UsbEndpointCount = intf.getEndpointCount();
                    for (i = 0; i < UsbEndpointCount; i++) {
                        ep = intf.getEndpoint(i);
                        Log.i(TAG, "端点是:" + i + "方向是:" + ep.getDirection() + "类型是:" + ep.getType());
                        if (ep.getDirection() == 0 && ep.getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
                            Log.i(TAG, "接口是:" + j + "端点是:" + i);
                            break;
                        }
                    }
                    if (i != UsbEndpointCount) {
                        break;
                    }
                }
            }
            if (j == InterfaceCount) {
                Log.i(TAG, "没有打印机接口");
                return;
            }

            mEndpointIntr = ep;

            UsbDeviceConnection connection = mUsbManager.openDevice(device);

            if (connection != null && connection.claimInterface(intf, true)) {
                Log.i(TAG, "打开成功！ ");
                mConnection = connection;

            } else {
                Log.i(TAG, "打开失败！ ");
                mConnection = null;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.usb)
    public void onViewClicked() {
        if (mDevice!=null) {
            device.setText(mDevice.getDeviceName()+"");
        }else {
            ToastUtil.showToast("未连接设备");
        }

    }
}
