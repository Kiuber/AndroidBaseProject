package me.kiuber.test.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import me.kiuber.base.utils.DialogUtil;
import me.kiuber.base.utils.ToastUtil;
import me.kiuber.test.MyContextHolder;
import me.kiuber.test.R;

/**
 * Created 2017/5/18 0018 11:10
 * Author Kiuber
 * Description
 */

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    public void normalToast(View view) {
        ToastUtil.get().showShortToast(MyContextHolder.getContext(), "短Toast");
        ToastUtil.get().showLongToast(MyContextHolder.getContext(), "长Toast");
    }


    public void loadingWindow(View view) {
        DialogUtil.get().showLoadingWindow(this, true);
    }

    public void loadingWindow1(View view) {
        DialogUtil.get().showLoadingWindow(this, true, "正在进行~");
    }

    public void test(View view) {
        View decorView = getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap    drawingCache = decorView.getDrawingCache();
        ImageView imageView    = (ImageView) findViewById(R.id.iv);
        imageView.setImageBitmap(drawingCache);
    }

    public void successToast(View view) {
        ToastUtil.get().showSuccessToast(this);
    }

    public void successToast1(View view) {
        ToastUtil.get().showSuccessToast(this, "上传成功");
    }
}
