package com.theaty.otg.ui.mine;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.theaty.otg.R;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import foundation.base.fragment.BaseFragment;

/**
 * Created by track on 2017/2/26.
 */

public class MineFragment extends BaseFragment {
    private static final String TAG = "USBTestActivity测试";


    StringBuilder sb;
    UsbDevice usbDevice;
    UsbManager usbManager;
    @BindView(R.id.usbtest_tv_info)
    TextView usbtestTvInfo;
    @BindView(R.id.usbtest_et_content)
    EditText usbtestEtContent;
    @BindView(R.id.usbtest_btn_refresh)
    Button usbtestBtnRefresh;
    Unbinder unbinder;

    @Override
    protected View onCreateContentView() {
//        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_mine, null, false);

        return inflateContentView(R.layout.activity_usbtest);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();

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

    @OnClick(R.id.usbtest_btn_refresh)
    public void onViewClicked() {
        String content = usbtestEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(content))
            usbPrint("打印测试....");
        else
            usbPrint(content);
    }
    private void usbPrint(String content) {
        sb = new StringBuilder();
        usbManager = (UsbManager)getActivity().getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> map = usbManager.getDeviceList();

        if (!map.isEmpty()) {
            for (UsbDevice device : map.values()) {
                int VendorID = device.getVendorId();
                int ProductID = device.getProductId();
                sb.append("VendorID:" + VendorID + ",ProductID:" + ProductID + "\n");
                if (VendorID == 10473 && ProductID == 649) {
                    usbDevice = device;

                    if (!usbManager.hasPermission(usbDevice)) {
                        Toast.makeText(getActivity(), "没有权限操作USB设备！", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    sb.append("设备接口个数：" + usbDevice.getInterfaceCount() + "\n");
                    UsbInterface usbInterface = usbDevice.getInterface(0);

                    sb.append("分配端点个数：" + usbInterface.getEndpointCount() + "\n");
                    UsbEndpoint outEndpoint = usbInterface.getEndpoint(0);

                    UsbDeviceConnection connection = usbManager.openDevice(usbDevice);
                    connection.claimInterface(usbInterface, true);

                    // 打印数据
                    content = content + "\n\n\n\n\n\n";
                    byte[] printData = null;
                    try {
                        printData = content.getBytes("gbk");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    int out = connection.bulkTransfer(outEndpoint, printData, printData.length, 5000);
                    // 关闭连接
                    connection.close();
                }
            }
            usbtestTvInfo.setText(sb.toString());
        } else {
            usbtestTvInfo.setText("设备为空");
        }
    }
}
