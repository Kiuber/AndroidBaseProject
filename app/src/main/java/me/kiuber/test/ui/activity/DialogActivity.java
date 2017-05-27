package me.kiuber.test.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.kiuber.base.utils.ActivityUtil;
import me.kiuber.base.utils.DialogUtil;
import me.kiuber.base.utils.ToastUtil;
import me.kiuber.test.R;

/**
 * Created 2017/5/15 0015 22:36
 * Author Kiuber
 * Description
 */

public class DialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.get().addActivity(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.get().removeActivity(this);
    }

    private void initView() {
        setContentView(R.layout.activity_dialog);
    }

    public void showNormalDialog1(View view) {
        DialogUtil.get().showNormalTipDialog(true, "这是显示一般提示对话框（点击对话框外可以取消）");
    }

    public void showNormalDialog2(View view) {
        DialogUtil.get().showNormalTipDialog(false, "这是显示一般提示对话框（点击对话框外不可以取消）");
    }

    public void showNormalProgressDialog1(View view) {
        DialogUtil.get().showNormalTipProgressDialog(true, "显示一般提示对话框（点击对话框外可以取消，没有自定义标题）");
    }

    public void showNormalProgressDialog2(View view) {
        DialogUtil.get().showNormalTipProgressDialog(true, "自定义标题", "显示一般提示对话框（点击对话框外可以取消，自定义标题）");
    }

    public void showHandleDialog1(View view) {
        DialogUtil.get().showHandleTipDialog(true, DialogUtil.DIALOG_TITLE_TIP, "内容", "确定", new DialogUtil.MyOneButtonDialogOnClickListener() {
            @Override
            public void onPositive() {
                ToastUtil.get().showShortToast("点击了确定");
            }
        });
    }

    public void showHandleDialog2(View view) {
        DialogUtil.get().showHandleTipDialog(true, DialogUtil.DIALOG_TITLE_TIP, "内容", "确定", "取消", new DialogUtil.MyTwoButtonDialogOnClickListener() {
            @Override
            public void onPositive() {
                ToastUtil.get().showShortToast("点击了确定");
            }

            @Override
            public void onNegative() {
                ToastUtil.get().showShortToast("点击了取消");
            }
        });
    }

    public void showHandleDialog3(View view) {
        DialogUtil.get().showHandleTipDialog(true, DialogUtil.DIALOG_TITLE_TIP, "内容", "确定", "取消", "忽略", new DialogUtil.MyThreeButtonDialogOnClickListener() {
            @Override
            public void onPositive() {
                ToastUtil.get().showShortToast("点击了确定");
            }

            @Override
            public void onNeutral() {
                ToastUtil.get().showShortToast("点击了取消");
            }

            @Override
            public void onNegative() {
                ToastUtil.get().showShortToast("点击了忽略");
            }
        });
    }
}
