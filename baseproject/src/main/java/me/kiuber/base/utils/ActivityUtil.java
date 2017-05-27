package me.kiuber.base.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 2017/5/22 0022 18:07
 * Author Kiuber
 * Description
 */

public class ActivityUtil {
    private static ActivityUtil instance;
    private List<Activity> mActivities = new ArrayList<>();

    public static ActivityUtil get() {
        if (instance == null) {
            instance = new ActivityUtil();
        }
        return instance;
    }

    public Activity getTopActivity() {
        if (mActivities.isEmpty()) {
            return null;
        } else {
            return mActivities.get(mActivities.size() - 1);
        }
    }

    public void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        mActivities.remove(activity);
    }
}
