package me.kiuber.base.custom;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import me.kiuber.base.R;

/**
 * Created 2017/5/21 0021 15:07
 * Author Kiuber
 * Description
 */

public class LoadingDialog extends Dialog {

    private View mView;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.getContext().setTheme(android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        mView = View.inflate(getContext(), R.layout.view_toast_loading, null);
        setContentView(mView);
        getWindow().getDecorView().getBackground().setAlpha(0);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setMessage(String msg) {
        TextView tvMsg = (TextView) mView.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);
    }
}
