package com.example.sampleapp;

import android.content.SharedPreferences;

import com.example.sampleapp.di.PerActivity;
import com.example.sampleapp.rx.MvpPresenter;

@PerActivity
public interface MainActivityMvpPresenter<V extends MainActivityMvpView> extends MvpPresenter<V> {

    void getData();
}
