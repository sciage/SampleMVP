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

import android.app.Application;
import android.content.Context;

import com.example.sampleapp.BuildConfig;
import com.example.sampleapp.data.AppDataManager;
import com.example.sampleapp.data.DataManager;
import com.example.sampleapp.data.network.ApiHeader;
import com.example.sampleapp.data.network.ApiHelper;
import com.example.sampleapp.data.network.AppApiHelper;
import com.example.sampleapp.data.prefs.AppPreferencesHelper;
import com.example.sampleapp.data.prefs.PreferencesHelper;
import com.example.sampleapp.di.ApiInfo;
import com.example.sampleapp.di.ApplicationContext;
import com.example.sampleapp.di.PreferenceInfo;
import com.example.sampleapp.rx.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return "BuildConfig.API_KEY";
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getUserId(),
               preferencesHelper.getToken());
    }


}