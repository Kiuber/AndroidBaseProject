package me.kiuber.base.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created 2017/4/28 0028 22:47
 * Author Kiuber
 * Description
 */

public class ToastUtil {

    public static void showShort(Context context, Object text) {
        if (text != null) {
            Toast.makeText(context, String.valueOf(text), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "吐司文本错误", Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context, Object text) {
        if (text != null) {
            Toast.makeText(context, String.valueOf(text), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "吐司文本错误", Toast.LENGTH_SHORT).show();
        }
    }
}
