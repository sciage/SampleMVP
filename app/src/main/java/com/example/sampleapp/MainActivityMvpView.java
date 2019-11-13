package com.example.sampleapp;

import com.example.sampleapp.rx.MvpView;
import com.example.sampleapp.rx.PeoplePojo;

import java.util.List;

public interface MainActivityMvpView extends MvpView {
    void getAllData(List<PeoplePojo> response);
}
