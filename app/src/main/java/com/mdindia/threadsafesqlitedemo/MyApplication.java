package com.mdindia.threadsafesqlitedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Faheem.Raza on 15/06/2017.
 */

public class MyApplication extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = MyApplication.this;
        DbHelper.initDB(mContext);
    }
}
