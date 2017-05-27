package me.kiuber.base.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.HashSet;

import static android.os.Build.VERSION;
import static android.os.Build.VERSION_CODES;

/**
 * Created 2017/5/2 0002 19:50
 * Author Kiuber
 * Description 继承Activity的权限工具类，子类是FragmentActivity将无法接收到onRequestPermissionsResult方法。
 */

public class PermissionUtil extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private int mRequestCode = 1127;
    private        MyPermissionListener mListener;
    private static PermissionUtil       instance;

    public static PermissionUtil get() {
        if (instance == null) {
            instance = new PermissionUtil();
        }
        return instance;
    }


    public interface MyPermissionListener {
        void onGranted();

        void onDenied(String[] deniedPermission);
    }

    /**
     * 发起请求权限
     *
     * @param manifest
     * @param listener
     */
    public void request(String[] manifest, MyPermissionListener listener) {
        Activity activity = ActivityUtil.get().getTopActivity();
        mListener = listener;
        HashSet<String> permissions = new HashSet<>();
        // 检测未授权的权限
        for (String permission : manifest) {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, permission)) {
                permissions.add(permission);
            }
        }
        if (permissions.isEmpty()) {
            // 如果未授权权限长度为0 即请求的权限都已授权
            listener.onGranted();
        } else {
            // 对未授权的权限发起申请
            String[] permission = SetUtil.get().toArray(permissions);
            ActivityCompat.requestPermissions(activity, permission, mRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        HashSet<String> deniedPermission = new HashSet<>();
        if (requestCode == mRequestCode) {
            LogUtil.d("requestCode == mRequestCode");
            // 检测申请权限的结果
            for (int i = 0; i < grantResults.length; i++) {
                LogUtil.d("grantResults[i]", grantResults[i]);
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermission.add(permissions[i]);
                }
            }
            if (deniedPermission.size() == 0) {
                // 未拒绝的权限长度为0 即都已授权
                mListener.onGranted();
            } else {
                // 返回未授权的权限
                mListener.onDenied(SetUtil.get().toArray(deniedPermission));
            }
        }
    }

    /**
     * 对于单一权限申请被用户拒绝后的提示
     *
     * @param permissionEN
     */
    public void noGrantedTip(String permissionEN) {
        DialogUtil.get().showHandleTipDialog(false, DialogUtil.DIALOG_TITLE_WARM
                , "缺少【" + switchToCHS(permissionEN) + "】权限将不能使用相应功能，是否立即去授权此权限？如果跳转失败，请根据机型手动进入设置并授权对应权限。"
                , "去设置", "取消", new DialogUtil.MyTwoButtonDialogOnClickListener() {
                    @Override
                    public void onPositive() {
                        startAppSettings();
                    }

                    @Override
                    public void onNegative() {

                    }
                });
    }

    /**
     * 启动当前应用设置页面
     */
    public void startAppSettings() {
        Intent   intent      = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Activity topActivity = ActivityUtil.get().getTopActivity();
        intent.setData(Uri.parse("package:" + topActivity.getPackageName()));
        topActivity.startActivity(intent);
    }

    /**
     * 判断当前系统版本是否大于23
     *
     * @return
     */
    public boolean isThanM() {
        return VERSION.SDK_INT >= VERSION_CODES.M;
    }


    /**
     * 6.0以下及以上的兼容方案
     *
     * @param permission
     * @param listener
     */
    public void compatibleResolver(final String permission, final CompatibleResolverListener listener) {
        try {
            if (isThanM()) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    request(new String[]{permission}, new MyPermissionListener() {
                        @Override
                        public void onGranted() {
                            listener.onGranted();
                        }

                        @Override
                        public void onDenied(String[] deniedPermission) {
                            noGrantedTip(permission);
                        }
                    });
                } else {
                    listener.onGranted();
                }
            } else {
                listener.onGranted();
            }
        } catch (Exception e) {
            DialogUtil.get().showNormalTipDialog(false, e.getMessage());
            e.printStackTrace();
        }
    }


    public interface CompatibleResolverListener {

        void onGranted();
    }

    public String switchToCHS(String permissionEN) {
        String permissionCHS = "";
        switch (permissionEN) {
            case Manifest.permission.CAMERA:
                permissionCHS = "相机";
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
                permissionCHS = "位置";
                break;
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                permissionCHS = "位置";
                break;
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                permissionCHS = "存储空间";
                break;
        }
        return permissionCHS;
    }

}
