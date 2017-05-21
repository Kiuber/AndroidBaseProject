package me.kiuber.base.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.HashSet;

/**
 * Created 2017/5/2 0002 19:50
 * Author Kiuber
 * Description
 */

public class PermissionUtil extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private int mRequestCode = 0x58227942;
    private String[]             mPermission;
    private MyPermissionListener mListener;
    private Activity             mActivity;
    private boolean              mIsForce;


    public interface MyPermissionListener {
        void onGranted();

        void onDenied(String[] deniedPermission);
    }

    /**
     * 发起请求权限
     *
     * @param activity
     * @param manifest
     * @param isForce  是否强制用户开启失败的权限
     * @param listener
     */
    public void request(Activity activity, String[] manifest, boolean isForce, MyPermissionListener listener) {
        mActivity = activity;
        mIsForce = isForce;
        mListener = listener;
        HashSet<String> permissions = new HashSet<>();
        for (String permission : manifest) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(permission);
            }
        }
        if (permissions.isEmpty()) {
            listener.onGranted();
        } else {
            mPermission = SetUtil.get().toArray(permissions);
            ActivityCompat.requestPermissions(activity, mPermission, mRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        HashSet<String> deniedPermission = new HashSet<>();
        if (requestCode == mRequestCode) {
            LogUtil.d("requestCode == mRequestCode");
            for (int i = 0; i < grantResults.length; i++) {
                LogUtil.d("grantResults[i]", grantResults[i]);
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermission.add(permissions[i]);
                }
            }
            if (deniedPermission.size() == 0) {
                mListener.onGranted();
            } else {
                if (mIsForce) {
                    DialogUtil.get().showHandleTipDialog(mActivity, false, "警告", "当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【去设置】按钮前往设置中心进行权限授权，否则将不能使用下面功能。", "去设置", "退出", new DialogUtil.MyTwoButtonDialogOnClickListener() {
                        @Override
                        public void onPositive() {
                            startAppSettings();
                        }

                        @Override
                        public void onNegative() {
                            finish();
                        }
                    });
                } else {
                    mListener.onDenied(SetUtil.get().toArray(deniedPermission));
                }
            }
        }
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
        mActivity.startActivity(intent);
    }

}
