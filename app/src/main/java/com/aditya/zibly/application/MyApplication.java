package com.aditya.zibly.application;//Created by aditya on 6/19/2015

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
