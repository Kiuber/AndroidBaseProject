package me.kiuber.test;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyContextHolder.initial(this);
    }
}
