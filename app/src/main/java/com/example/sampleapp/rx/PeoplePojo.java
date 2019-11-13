package com.example.sampleapp.rx;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeoplePojo {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("Details")
    @Expose
    private List<String> details = null;
    @SerializedName("bodyType")
    @Expose
    private String bodyType;
    @SerializedName("userDesire")
    @Expose
    private String userDesire;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getDetails() {
        return details;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getUserDesire() {
        return userDesire;
    }
}
