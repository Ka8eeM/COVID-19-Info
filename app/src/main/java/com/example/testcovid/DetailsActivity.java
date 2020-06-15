package com.example.testcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity {
    Intent intent;
    ItemModel model;
    TextView txtCountryName;
    TextView txtTotalCases;
    TextView txtLastUpdate;
    TextView txtRecov;
    TextView txtTotalDeath;
    TextView txtNewCases;
    TextView txtNewDeaths;
    TextView txtActive;
    //ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intent = getIntent();
        model = (ItemModel) intent.getSerializableExtra("MyClass");
        txtCountryName = findViewById(R.id.country_name_detail_view);
        txtCountryName.setText(model.getCountryText());
        txtTotalDeath = findViewById(R.id.total_death_detail_view);
        txtTotalDeath.setText(model.getTotalDeathsText());
        txtTotalCases = findViewById(R.id.total_case_detail_view);
        txtTotalCases.setText(model.getTotalCasesText());
        txtLastUpdate = findViewById(R.id.last_update_detail_view);
        txtLastUpdate.setText(model.getLastUpdateText());
        txtNewCases = findViewById(R.id.new_case_detail_view);
        txtNewCases.setText(model.getNewCasesText());
        txtNewDeaths = findViewById(R.id.new_death_detail_view);
        txtNewDeaths.setText(model.getNewDeathsText());
        txtActive = findViewById(R.id.active_case_detail_view);
        txtActive.setText(model.getActiveCasesText());
        txtRecov = findViewById(R.id.total_recov_detail_view);
        txtRecov.setText(model.getTotalRecoveredText());
    }
}