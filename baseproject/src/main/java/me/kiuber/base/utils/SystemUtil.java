package me.kiuber.base.utils;

import android.app.Activity;
import android.graphics.Point;

/**
 * Created 2017/5/18 0018 13:30
 * Author Kiuber
 * Description
 */

public class SystemUtil {
    private static SystemUtil instance;

    public static SystemUtil get() {
        if (instance == null) {
            instance = new SystemUtil();
        }
        return instance;
    }

    public int[] getWidthAndHeight(Activity activity) {
        int[] result = new int[2];
        Point point  = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        result[0] = point.x;
        result[1] = point.y;
        return result;
    }
}
