package com.example.taxidriver;

import android.app.Application;
import android.content.Context;

public class TaxiDriver extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        TaxiDriver.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return TaxiDriver.context;
    }
}
