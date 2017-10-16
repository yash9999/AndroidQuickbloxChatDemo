package com.alam.quickbloxchatdemo;

import android.app.Application;

import com.alam.quickbloxchatdemo.quickblox.QuickbloxSession;


/**
 * Created by Braintech on 25-Aug-16.
 */
public class MyApplication extends Application {
    private static MyApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();

        instance=this;

        QuickbloxSession quickbloxSession=new QuickbloxSession();
        quickbloxSession.startSession(getApplicationContext());

    }

    public static synchronized MyApplication getInstance() {
        return instance;
    }



}
