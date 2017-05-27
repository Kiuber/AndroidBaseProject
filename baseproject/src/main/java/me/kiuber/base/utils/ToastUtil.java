package me.kiuber.base.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

import me.kiuber.base.R;

/**
 * Created 2017/4/28 0028 22:47
 * Author Kiuber
 * Description
 */

public class ToastUtil {

    private static ToastUtil instance;
    private        Timer     mShowTimer;
    private        Timer     mCancelTimer;

    public static ToastUtil get() {
        if (instance == null) {
            instance = new ToastUtil();
        }
        return instance;
    }

    /**
     * 显示短Toast
     *
     * @param text
     */
    public Toast showShortToast(Object text) {
        Context context = ActivityUtil.get().getTopActivity();
        if (text != null) {
            Toast toast = Toast.makeText(context, String.valueOf(text), Toast.LENGTH_SHORT);
            toast.show();
            return toast;
        } else {
            Toast toast = Toast.makeText(context, "Toast文本为null", Toast.LENGTH_SHORT);
            toast.show();
            return toast;
        }
    }

    /**
     * 显示长Toast
     *
     * @param text
     */
    public void showLongToast(Object text) {
        Context context = ActivityUtil.get().getTopActivity();
        if (text != null) {
            Toast.makeText(context, String.valueOf(text), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Toast文本为null", Toast.LENGTH_SHORT).show();
        }
    }

    public void showSuccessToast() {
        Context context = ActivityUtil.get().getTopActivity();
        Toast        toast = new Toast(context);
        LinearLayout view  = (LinearLayout) View.inflate(context, R.layout.view_toast_success, null);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showSuccessToast(String msg) {
        Context      context = ActivityUtil.get().getTopActivity();
        Toast        toast   = new Toast(context);
        LinearLayout view    = (LinearLayout) View.inflate(context, R.layout.view_toast_success, null);
        TextView     tvMsg   = (TextView) view.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
