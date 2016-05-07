package com.sempliva.emotly.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sempliva.emotly.R;
import com.sempliva.emotly.rest.data.Moods;
import com.sempliva.emotly.rest.ServerAPI;
import com.sempliva.emotly.utils.SharedValues;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///TODO: Fix activity flow
public class SplashScreenActivity extends AppCompatActivity implements Callback<Moods> {

    private SharedPreferences preferences;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ServerAPI.getInstance().getMoods(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        intent = new Intent(this,LoginActivity.class);

    }

    @Override
    public void onResponse(Call<Moods> call, Response<Moods> response) {
        if(response.body() != null){
            preferences.edit().putString(getString(R.string.PREFERENCE_MOODS), response.message());
            SharedValues.getInstance().setMoods(response.body().moods);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Call<Moods> call, Throwable t) {
        startActivity(intent);
    }
}
