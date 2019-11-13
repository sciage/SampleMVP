package com.example.sampleapp.rx;

import android.app.Application;
import android.content.Context;

import com.example.sampleapp.data.DataManager;
import com.example.sampleapp.di.component.ApplicationComponent;
import com.example.sampleapp.di.component.DaggerApplicationComponent;
import com.example.sampleapp.di.module.ApplicationModule;

import javax.inject.Inject;

public class SampleApplication extends Application {
    private static Context context;

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static SampleApplication get(Context context) {
        return (SampleApplication) context.getApplicationContext();
    }


    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

}