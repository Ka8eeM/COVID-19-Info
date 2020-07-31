package com.ka8eem.testcovid.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ka8eem.testcovid.models.ItemModel;
import com.ka8eem.testcovid.repository.DataClient;
import com.ka8eem.testcovid.repository.DataInterface;
import com.ka8eem.testcovid.ui.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataViewModel extends ViewModel
{

    public MutableLiveData<ArrayList<ItemModel>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> string = new MutableLiveData<>();
    public void getData()
    {
        DataClient.getINSTANCE().getPosts().enqueue(new Callback<ArrayList<ItemModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemModel>> call, Response<ArrayList<ItemModel>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ItemModel>> call, Throwable t) {
                string.setValue("Error!");
            }
        });
    }
}
