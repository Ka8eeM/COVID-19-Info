package com.example.testcovid;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;

public class ItemModel implements Serializable {

    private String activeCasesText;
    private String countryText;
    private String lastUpdateText;
    private String newCasesText;
    private String newDeathsText;
    private String totalCasesText;
    private String totalDeathsText;
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

    public void setActiveCasesText(String activeCasesText) {
        this.activeCasesText = activeCasesText;
    }

    public void setCountryText(String countryText) {
        this.countryText = countryText;
    }

    public void setLastUpdateText(String lastUpdateText) {
        this.lastUpdateText = lastUpdateText;
    }

    public void setNewCasesText(String newCasesText) {
        this.newCasesText = newCasesText;
    }

    public void setNewDeathsText(String newDeathsText) {
        this.newDeathsText = newDeathsText;
    }

    public void setTotalCasesText(String totalCasesText) {
        this.totalCasesText = totalCasesText;
    }

    public void setTotalDeathsText(String totalDeathsText) {
        this.totalDeathsText = totalDeathsText;
    }

    public void setTotalRecoveredText(String totalRecoveredText) {
        this.totalRecoveredText = totalRecoveredText;
    }


}
