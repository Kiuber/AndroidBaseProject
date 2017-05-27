package me.kiuber.test.ui.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yanzhenjie.zbar.Image;
import com.yanzhenjie.zbar.ImageScanner;
import com.yanzhenjie.zbar.Symbol;
import com.yanzhenjie.zbar.SymbolSet;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;

import me.kiuber.base.utils.ArrayUtil;
import me.kiuber.base.utils.DialogUtil;
import me.kiuber.base.utils.ListUtil;
import me.kiuber.base.utils.LogUtil;
import me.kiuber.base.utils.ToastUtil;
import me.kiuber.test.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        List<String> strings = ArrayUtil.get().toList(new String[]{"Array1", "Array2", "Array3", "Array4"});
        String       join    = ListUtil.get().join(strings);
        LogUtil.d("join", join);
        String join1 = ListUtil.get().join(strings, "||");
        LogUtil.d("join1", join1);
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

    public void saoTest(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1127);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1127) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                Bitmap2Bytes(bitmap);

                byte[] bytes = Bitmap2Bytes(bitmap);

                Image barcode = new Image();
                barcode.setData(bytes);

                String qrCodeString = null;

                ImageScanner imageScanner = new ImageScanner();
                int          result       = imageScanner.scanImage(barcode);
                if (result != 0) {
                    SymbolSet symSet = imageScanner.getResults();
                    for (Symbol sym : symSet)
                        qrCodeString = sym.getData();
                }

                if (!TextUtils.isEmpty(qrCodeString)) {
                    DialogUtil.get().showNormalTipDialog(false, qrCodeString);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
