package com.ka8eem.testcovid.repository;

import com.ka8eem.testcovid.models.ItemModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataClient {

    private static final String BASE_URL = "https://covid-19-tracking.p.rapidapi.com/";
    private DataInterface dataInterface;
    private static DataClient INSTANCE;

    private DataClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataInterface = retrofit.create(DataInterface.class);
    }

    public static DataClient getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new DataClient();
        return INSTANCE;
    }

    public Call<ArrayList<ItemModel>> getPosts() {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-rapidapi-host", "covid-19-tracking.p.rapidapi.com");
        headers.put("x-rapidapi-key", "c93113a008msh2c7f7b7f79968a6p1a61f8jsnd99ac7bbc495");
        return dataInterface.getData(headers);
    }
}
