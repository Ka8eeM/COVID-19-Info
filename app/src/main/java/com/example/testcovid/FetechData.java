package com.example.testcovid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.testcovid.MainActivity.*;
import static com.example.testcovid.MainActivity.adapter;

public class FetechData extends AsyncTask<String, Integer, ArrayList<ItemModel>> implements DataAdapter.OnItemListener {


    @Override
    protected ArrayList<ItemModel> doInBackground(String... urls) {
        ArrayList<ItemModel> retList = new ArrayList<>();
        String result = null;
        Response response = null;
        JSONArray array = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urls[0])
                .get()
                .addHeader("x-rapidapi-host", "covid-19-tracking.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "c93113a008msh2c7f7b7f79968a6p1a61f8jsnd99ac7bbc495")
                .build();
        int i = 0;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new IOException("OkHttp error " + response);
            else {
                result = response.body().string();
                JSONArray jsonObject = new JSONArray(result);
                array = jsonObject;
                //retList = new Gson().fromJson(String.valueOf(array), ArrayList.class);
                for (; i < array.length() - 1; i++) {
                    ItemModel model = new ItemModel();
                    JSONObject o = array.getJSONObject(i);
                    model.setActiveCasesText(o.optString("Active Cases_text", ""));
                    model.setCountryText(o.optString("Country_text", ""));
                    model.setLastUpdateText(o.optString("Last Update", ""));
                    model.setNewCasesText(o.optString("New Cases_text", ""));
                    model.setNewDeathsText(o.optString("New Deaths_text", ""));
                    model.setTotalCasesText(o.optString("Total Cases_text", ""));
                    model.setTotalDeathsText(o.optString("Total Deaths_text", ""));
                    model.setTotalRecoveredText(o.optString("Total Recovered_text", ""));
                    retList.add(model);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return retList;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(ArrayList<ItemModel> itemModels) {
        list = itemModels;
        adapter = new DataAdapter(context , itemModels, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int pos) {
        ItemModel model = list.get(pos);
        Intent intent = new Intent( context,DetailsActivity.class);
        intent.putExtra("MyClass", (Serializable) model);
        context.startActivity(intent);
    }
}
