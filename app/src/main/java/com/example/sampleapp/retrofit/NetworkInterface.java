package com.example.sampleapp.retrofit;

import com.example.sampleapp.data.network.ApiEndPoint;
import com.example.sampleapp.rx.PeoplePojo;
import com.google.gson.JsonObject;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetworkInterface {


    @GET(ApiEndPoint.CardData)
    Single<List<PeoplePojo>> getData();

}
