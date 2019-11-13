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

package com.example.sampleapp.data;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.sampleapp.data.network.ApiHeader;
import com.example.sampleapp.data.network.ApiHelper;
import com.example.sampleapp.data.prefs.PreferencesHelper;
import com.example.sampleapp.di.ApplicationContext;
import com.example.sampleapp.rx.PeoplePojo;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Single;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public void updateUserInfo(String userId, String user_token, LoggedInMode loggedInMode) {

    }

    @Override
    public ApiHeader getApiHeader() {
        return null;
    }

    @Override
    public Single<List<PeoplePojo>> getData() {
        return mApiHelper.getData();
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public void registerToken(String token) {

    }

    @Override
    public String getToken() {
        return null;
    }
}
