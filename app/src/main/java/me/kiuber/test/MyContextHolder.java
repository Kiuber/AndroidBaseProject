package me.kiuber.test;

import android.content.Context;

/**
 * Created 2017/4/28 0028 23:04
 * Author Kiuber
 * Description
 */

public class MyContextHolder {
    static Context ApplicationContext;

     static void initial(Context context) {
        ApplicationContext = context;
    }

    public static Context getContext() {
        return ApplicationContext;
    }
}

