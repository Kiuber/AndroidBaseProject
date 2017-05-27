package me.kiuber.test.ui.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import me.kiuber.base.utils.ActivityUtil;
import me.kiuber.base.utils.ArrayUtil;
import me.kiuber.base.utils.ListUtil;
import me.kiuber.base.utils.LogUtil;
import me.kiuber.base.utils.ToastUtil;
import me.kiuber.test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.get().addActivity(this);
        initView();
        List<String> strings = ArrayUtil.get().toList(new String[]{"Array1", "Array2", "Array3", "Array4"});
        String       join    = ListUtil.get().join(strings);
        LogUtil.d("join", join);
        String join1 = ListUtil.get().join(strings, "||");
        LogUtil.d("join1", join1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.get().removeActivity(this);
    }

    private void initView() {
        setContentView(R.layout.activity_main);
    }

    public void logTest(View view) throws PackageManager.NameNotFoundException {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 0);
            int             labelRes        = applicationInfo.labelRes;
            String          name            = getResources().getString(labelRes);
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
            ToastUtil.get().showShortToast("已打印到控制台");
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
            ToastUtil.get().showShortToast(e.getMessage());
        }
    }

    public void preferenceTest(View view) {
        startActivity(new Intent(this, PreferenceActivity.class));
    }

    public void toastTest(View view) {
        startActivity(new Intent(this, ToastActivity.class));
    }

    public void permissionTest(View view) {
        startActivity(new Intent(this, PermissionActivity.class));
    }

    public void dialogTest(View view) {
        startActivity(new Intent(this, DialogActivity.class));
    }
}
