package me.kiuber.test.ui.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.kiuber.base.utils.LogUtil;
import me.kiuber.base.utils.ToastUtil;
import me.kiuber.test.MyContextHolder;
import me.kiuber.test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
    }

    public void logTest(View view) throws PackageManager.NameNotFoundException {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 0);
            int labelRes = applicationInfo.labelRes;
            String name = getResources().getString(labelRes);
            LogUtil.v(name);
            LogUtil.v("Application_Name", name);
            LogUtil.d(name);
            LogUtil.d("Application_Name", name);
            LogUtil.i(name);
            LogUtil.i("Application_Name", name);
            LogUtil.w(name);
            LogUtil.w("Application_Name", name);
            LogUtil.e(name);
            LogUtil.e("Application_Name", name);
            ToastUtil.showShort(MyContextHolder.getContext(), "已打印到控制台");
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
            ToastUtil.showShort(MyContextHolder.getContext(), e.getMessage());
        }
    }

    public void preferenceTest(View view) {
        startActivity(new Intent(this, PreferenceActivity.class));
    }

    public void toastTest(View view) {
        ToastUtil.showShort(MyContextHolder.getContext(), "短Toast");
        ToastUtil.showLong(MyContextHolder.getContext(), "长Toast");
    }
}
