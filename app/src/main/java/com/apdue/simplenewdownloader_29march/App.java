package com.apdue.simplenewdownloader_29march;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.analytics.FirebaseAnalytics;

public class App extends Application {

    private static App app;

    @NonNull
    public static App getApp() {
        return app;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(this);
        app = this;
    }
}
