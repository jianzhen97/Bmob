package com.lenovo.bmob;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by w on 2017/3/28.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "9b60d9e9bd274fe74b85fd25ff448179");
    }
}
