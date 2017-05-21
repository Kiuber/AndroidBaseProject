package me.kiuber.base.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.PopupWindow;

import me.kiuber.base.custom.LoadingDialog;

/**
 * Created 2017/5/15 0015 22:19
 * Author Kiuber
 * Description
 */

public class DialogUtil {
    private static DialogUtil  instance;
    private        PopupWindow mPopupWindow;
    public static final String DIALOG_TITLE_TIP     = "提示";
    public static final String DIALOG_TITLE_WARM    = "警告";
    public static final String DIALOG_POSITIVE_KNOW = "知道了";


    public static DialogUtil get() {
        if (instance == null) {
            instance = new DialogUtil();
        }
        return instance;
    }

    /**
     * 显示一般提示对话框
     *
     * @param context
     * @param cancelAble
     * @param message
     * @return
     */
    public AlertDialog.Builder showNormalTipDialog(Context context, boolean cancelAble, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton("知道了", null);
        if (!cancelAble) {
            builder.setCancelable(false);
        }
        builder.show();
        return builder;
    }

    /**
     * 显示可定制的提示对话框
     *
     * @param context
     * @param cancelAble
     * @param title
     * @param message
     * @param positive
     * @return
     */
    public AlertDialog.Builder showNormalTipDialog(Context context, boolean cancelAble, String title, String message, String positive) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positive, null);
        if (!cancelAble) {
            builder.setCancelable(false);
        }
        builder.show();
        return builder;
    }

    /**
     * 显示可以回调的对话框
     *
     * @param context
     * @param cancelAble
     * @param message
     * @param listener
     * @return
     */
    public AlertDialog.Builder showHandleTipDialog(Context context, boolean cancelAble, String title, String message, String positive, final MyOneButtonDialogOnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setTitle(title);
        builder.setMessage(message);
        if (!cancelAble) {
            builder.setCancelable(false);
        }
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositive();
            }
        });
        builder.show();
        return builder;
    }

    /**
     * 显示可定制的回调对话框
     *
     * @param context
     * @param cancelAble
     * @param title
     * @param message
     * @param positive
     * @param negative
     * @param listener
     * @return
     */
    public AlertDialog.Builder showHandleTipDialog(Context context, boolean cancelAble, String title, String message, String positive, String negative, final MyTwoButtonDialogOnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        if (!cancelAble) {
            builder.setCancelable(false);
        }
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositive();
            }
        });
        builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegative();
            }
        });
        builder.show();
        return builder;
    }

    /**
     * 显示可定制的回调对话框
     *
     * @param context
     * @param cancelAble
     * @param title
     * @param message
     * @param positive
     * @param negative
     * @param listener
     * @return
     */
    public AlertDialog.Builder showHandleTipDialog(Context context, boolean cancelAble, String title, String message, String positive, String neutral, String negative, final MyThreeButtonDialogOnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        if (!cancelAble) {
            builder.setCancelable(false);
        }
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositive();
            }
        });
        builder.setNeutralButton(neutral, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNeutral();
            }
        });
        builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegative();
            }
        });
        builder.show();
        return builder;
    }

    /**
     * 显示一般提示进度对话框
     *
     * @param context
     * @param cancelAble
     * @param message
     * @return
     */
    public ProgressDialog showNormalTipProgressDialog(Context context, boolean cancelAble, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        if (!cancelAble) {
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
        return progressDialog;
    }

    /**
     * 显示可定制的进度对话框
     *
     * @param context
     * @param cancelAble
     * @param title
     * @param message
     * @return
     */
    public ProgressDialog showNormalTipProgressDialog(Context context, boolean cancelAble, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        if (!cancelAble) {
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
        return progressDialog;
    }


    public Dialog showLoadingWindow(Activity activity, boolean cancelAble) {
        LoadingDialog loadingDialog = new LoadingDialog(activity);
        loadingDialog.setCancelable(cancelAble);
        loadingDialog.show();
        return loadingDialog;
    }

    public Dialog showLoadingWindow(Activity activity, boolean cancelAble, String msg) {
        LoadingDialog loadingDialog = new LoadingDialog(activity);
        loadingDialog.setCancelable(cancelAble);
        loadingDialog.setMessage(msg);
        loadingDialog.show();
        return loadingDialog;
    }

    public interface MyOneButtonDialogOnClickListener {

        void onPositive();

    }

    public interface MyTwoButtonDialogOnClickListener {

        void onPositive();

        void onNegative();
    }

    public interface MyThreeButtonDialogOnClickListener {

        void onPositive();

        void onNeutral();

        void onNegative();
    }

//    public PopupWindow showLoadingWindow(Activity activity, boolean focusable) {
//        if (mPopupWindow == null) {
//            View         decorView   = activity.getWindow().getDecorView();
//            LinearLayout view        = (LinearLayout) View.inflate(activity, R.layout.view_toast_loading, null);
//            PopupWindow  popupWindow = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            mPopupWindow = popupWindow;
//            popupWindow.setContentView(view);
//            popupWindow.setFocusable(focusable);
//            popupWindow.showAtLocation(decorView, Gravity.CENTER, 0, 0);
//            return popupWindow;
//        } else {
//            ToastUtil.get().showShortToast(activity, "popupWindow already exist");
//            return null;
//        }
//    }
//
//    public PopupWindow showLoadingWindow(Activity activity, boolean focusable, String msg) {
//        if (mPopupWindow == null) {
//            View         decorView = activity.getWindow().getDecorView();
//            LinearLayout view      = (LinearLayout) View.inflate(activity, R.layout.view_toast_loading, null);
//            TextView     tvMsg     = (TextView) view.findViewById(R.id.tv_msg);
//            tvMsg.setText(msg);
//            PopupWindow popupWindow = new PopupWindow(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            mPopupWindow = popupWindow;
//            popupWindow.setContentView(view);
//            popupWindow.setFocusable(focusable);
//            popupWindow.showAtLocation(decorView, Gravity.CENTER, 0, 0);
//            return popupWindow;
//        } else {
//            ToastUtil.get().showShortToast(activity, "popupWindow already exist");
//            return null;
//        }
//    }
}
