package com.ka8eem.testcovid.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

public class ItemModel implements Serializable {

    @SerializedName("Active Cases_text")
    private String activeCasesText;
    @SerializedName("Country_text")
    private String countryText;
    @SerializedName("Last Update")
    private String lastUpdateText;
    @SerializedName("New Cases_text")
    private String newCasesText;
    @SerializedName("New Deaths_text")
    private String newDeathsText;
    @SerializedName("Total Cases_text")
    private String totalCasesText;
    @SerializedName("Total Deaths_text")
    private String totalDeathsText;
    @SerializedName("Total Recovered_text")
    private String totalRecoveredText;

    public ItemModel() {}
    public static ItemModel deserialize(JSONObject object)
    {
        Gson gson = new Gson();
        String res = object.toString();
        ItemModel model = gson.fromJson(res, ItemModel.class);
        return model;
    }

    public String getActiveCasesText() {
        return activeCasesText;
    }

    public String getCountryText() {
        return countryText;
    }

    public String getLastUpdateText() {
        return lastUpdateText;
    }

    public String getNewCasesText() {
        return newCasesText;
    }

    public String getNewDeathsText() {
        return newDeathsText;
    }

    public String getTotalCasesText() {
        return totalCasesText;
    }

    public String getTotalDeathsText() {
        return totalDeathsText;
    }

    public String getTotalRecoveredText() {
        return totalRecoveredText;
    }
}
