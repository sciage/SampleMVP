package com.example.sampleapp;

import android.content.SharedPreferences;

import com.example.sampleapp.data.DataManager;
import com.example.sampleapp.retrofit.ANError;
import com.example.sampleapp.rx.BasePresenter;
import com.example.sampleapp.rx.PeoplePojo;
import com.example.sampleapp.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainActivityPresenter<V extends MainActivityMvpView> extends BasePresenter<V>
        implements MainActivityMvpPresenter<V> {

    @Inject
    public MainActivityPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getData() {
        getCompositeDisposable().add(getDataManager()
                .getData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<PeoplePojo>>() {
                    @Override
                    public void accept(List<PeoplePojo> response) throws Exception {

                        getMvpView().getAllData(response);

                        // todo add data and loop to get all friends list
                     /*   getDataManager().updateUserInfo(

                                response.info.getId(),
                                response.info.getUser_token(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER
                        );
                        */

                        //getMvpView().hideLoading();
                        // getMvpView().openMainActivity();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }


                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}

