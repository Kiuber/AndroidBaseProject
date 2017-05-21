package me.kiuber.test.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.kiuber.base.utils.DialogUtil;
import me.kiuber.base.utils.ListUtil;
import me.kiuber.base.utils.PermissionUtil;
import me.kiuber.base.utils.ToastUtil;
import me.kiuber.test.R;

/**
 * Created 2017/5/2 0002 17:19
 * Author Kiuber
 * Description
 */

public class PermissionActivity extends PermissionUtil implements CompoundButton.OnCheckedChangeListener {

    private AppCompatCheckBox mCbCamera;
    private AppCompatCheckBox mCbBluetooth;
    private AppCompatCheckBox mCbWifi;
    private AppCompatCheckBox mCbAccount;
    private AppCompatCheckBox mCbMic;
    private AppCompatCheckBox mCbSms;

    private List<String> mPermissionList = new ArrayList<>();
    private int mRequestCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mPermissionList.add(Manifest.permission.CAMERA);
        mPermissionList.add(Manifest.permission.BLUETOOTH);
        mPermissionList.add(Manifest.permission.ACCESS_WIFI_STATE);
        mPermissionList.add(Manifest.permission.GET_ACCOUNTS);
        mPermissionList.add(Manifest.permission.RECORD_AUDIO);
    }

    private void initView() {
        setContentView(R.layout.activity_permission);

        mCbCamera = (AppCompatCheckBox) findViewById(R.id.cb_camera);
        mCbBluetooth = (AppCompatCheckBox) findViewById(R.id.cb_bluetooth);
        mCbWifi = (AppCompatCheckBox) findViewById(R.id.cb_wifi);
        mCbAccount = (AppCompatCheckBox) findViewById(R.id.cb_account);
        mCbMic = (AppCompatCheckBox) findViewById(R.id.cb_mic);
        mCbSms = (AppCompatCheckBox) findViewById(R.id.cb_mic);
        mCbCamera.setOnCheckedChangeListener(this);
        mCbBluetooth.setOnCheckedChangeListener(this);
        mCbWifi.setOnCheckedChangeListener(this);
        mCbAccount.setOnCheckedChangeListener(this);
        mCbMic.setOnCheckedChangeListener(this);
        mCbSms.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == mCbCamera) {
            if (isChecked) {
                mPermissionList.add(Manifest.permission.CAMERA);
            } else {
                mPermissionList.remove(Manifest.permission.CAMERA);
            }
        }
        if (buttonView == mCbBluetooth) {
            if (isChecked) {
                mPermissionList.add(Manifest.permission.BLUETOOTH);
            } else {
                mPermissionList.remove(Manifest.permission.BLUETOOTH);
            }
        }
        if (buttonView == mCbWifi) {
            if (isChecked) {
                mPermissionList.add(Manifest.permission.ACCESS_WIFI_STATE);
            } else {
                mPermissionList.remove(Manifest.permission.ACCESS_WIFI_STATE);
            }
        }
        if (buttonView == mCbAccount) {
            if (isChecked) {
                mPermissionList.add(Manifest.permission.GET_ACCOUNTS);
            } else {
                mPermissionList.remove(Manifest.permission.GET_ACCOUNTS);
            }
        }
        if (buttonView == mCbMic) {
            if (isChecked) {
                mPermissionList.add(Manifest.permission.RECORD_AUDIO);
            } else {
                mPermissionList.remove(Manifest.permission.RECORD_AUDIO);
            }
        }
        if (buttonView == mCbSms) {
            if (isChecked) {
                mPermissionList.add(Manifest.permission.READ_SMS);
            } else {
                mPermissionList.remove(Manifest.permission.READ_SMS);
            }
        }
    }


    public void requestTest(View view) {
        request(this, ListUtil.get().toArray(mPermissionList), false, new PermissionUtil.MyPermissionListener() {
            @Override
            public void onGranted() {
                ToastUtil.get().showShortToast(PermissionActivity.this, "所有权限授权成功成功");
            }

            @Override
            public void onDenied(String[] deniedPermission) {
                String s = Arrays.toString(deniedPermission);
                DialogUtil.get().showNormalTipDialog(PermissionActivity.this, false, "未授权的权限有：" + s);
            }
        });
    }

    public void requestForceTest(View view) {
        request(this, ListUtil.get().toArray(mPermissionList), true, new PermissionUtil.MyPermissionListener() {
            @Override
            public void onGranted() {
                ToastUtil.get().showShortToast(PermissionActivity.this, "所有权限授权成功成功");
            }

            @Override
            public void onDenied(String[] deniedPermission) {
                String s = Arrays.toString(deniedPermission);
                ToastUtil.get().showShortToast(PermissionActivity.this, s);
            }
        });
    }
}
