package me.kiuber.base.utils;

import android.app.Activity;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created 2017/4/28 0028 16:59
 * Author Kiuber
 * Description
 */

public class LogUtil {

    private static String TAG = "APP_NAME" + "-LOG";
    /**
     * 是否为测试版本
     */
    private static boolean isDebug = true;

    /**
     * 是否弹出吐司日志
     *
     * @param msg
     */
    private static boolean isShowToast = false;

    public static void v(final String variable, final Object msg) {
        if (isDebug) {
            android.util.Log.v(TAG + getCaller(), variable + "--->" + String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


    public static void v(final Object msg) {
        if (isDebug) {
            android.util.Log.v(TAG + getCaller(), String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void d(final String variable, final Object msg) {
        if (isDebug) {
            android.util.Log.d(TAG + getCaller(), variable + "--->" + String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void d(final Object msg) {
        if (isDebug) {
            android.util.Log.d(TAG + getCaller(), String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void i(final String variable, final Object msg) {
        if (isDebug) {
            android.util.Log.i(TAG + getCaller(), variable + "--->" + String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void i(final Object msg) {
        if (isDebug) {
            android.util.Log.i(TAG + getCaller(), String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void w(final String variable, final Object msg) {
        if (isDebug) {
            android.util.Log.w(TAG + getCaller(), variable + "--->" + String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void w(final Object msg) {
        if (isDebug) {
            android.util.Log.w(TAG + getCaller(), String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void e(final String variable, final Object msg) {
        if (isDebug) {
            android.util.Log.e(TAG + getCaller(), variable + "--->" + String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public static void e(final Object msg) {
        if (isDebug) {
            android.util.Log.e(TAG + getCaller(), String.valueOf(msg));
            if (isShowToast) {
                final Activity currentActivity = getCurrentActivity();
                currentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(currentActivity, (CharSequence) msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


    /**
     * 获取当前Activity对象
     *
     * @return
     */
    private static Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    return (Activity) activityField.get(activityRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取调用该类的类名及方法
     *
     * @return
     */
    private static String getCaller() {
        int i;
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        String mClassName = stack[2].getClassName();
        String name = mClassName.substring(mClassName.lastIndexOf(".") + 1, mClassName.length());
        name = "{" + " 【" + "Class--->" + name + ".class" + "】" + "【" + "Method--->" + stack[2].getMethodName() + "】 " + "}";
        return name;
    }
}
