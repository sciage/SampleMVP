/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.sampleapp.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapp.MainActivityMvpPresenter;
import com.example.sampleapp.MainActivityMvpView;
import com.example.sampleapp.MainActivityPresenter;
import com.example.sampleapp.di.ActivityContext;
import com.example.sampleapp.rx.AppSchedulerProvider;
import com.example.sampleapp.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    MainActivityMvpPresenter<MainActivityMvpView> provideMainActivityMvpPresenter(
            MainActivityPresenter<MainActivityMvpView> presenter) {
        return presenter;
    }

}
