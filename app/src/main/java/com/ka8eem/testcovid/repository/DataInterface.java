package com.ka8eem.testcovid.repository;

import com.ka8eem.testcovid.models.ItemModel;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;

public interface DataInterface {

    @GET("v1")
    Call<ArrayList<ItemModel>> getData(@HeaderMap Map<String, String> headers);
}
