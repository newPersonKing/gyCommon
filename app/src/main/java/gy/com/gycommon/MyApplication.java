package gy.com.gycommon;

import android.app.Application;

import gy.com.gycommon.until.Utils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
