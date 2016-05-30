/*
 MIT License
 Copyright (c) 2016 Emotly Contributors
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
*/

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
