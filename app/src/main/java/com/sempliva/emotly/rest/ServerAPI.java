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

package com.sempliva.emotly.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sempliva.emotly.rest.data.LoginData;
import com.sempliva.emotly.rest.data.JWT;
import com.sempliva.emotly.rest.data.Moods;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ggc on 07/05/16.
 * Copyright © 2016 com.sempliva. All rights reserved.
 */

// This singleton class manage the communication with the backend.
public class ServerAPI {

    private static final String BASE_URL = "https://emotly.herokuapp.com/api/1.0/";
    private static ServerAPI instance = null;
    private final EmotlyServices emotlyServices;
    private final Retrofit retrofit;

    public static ServerAPI getInstance(){
        if (instance == null)
        {
            instance = new ServerAPI();
        }

        return instance;
    }

    private ServerAPI(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy:MM:dd HH:mm:ss")
                .create();
        retrofit = (new Retrofit.Builder())
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        emotlyServices = retrofit.create(EmotlyServices.class);
    }

  // This class avoid the use of retrofit in all the activity that needs
  // Login server call.
    public void login(String userID, String password, Callback<JWT> callback){
        LoginData loginData = new LoginData();
        loginData.user_id = userID;
        loginData.password = password;
        Call<JWT> call = emotlyServices.login(loginData);
        call.enqueue(callback);
    }

    // Get the list of available moods.
    public void getMoods(Callback<Moods> callback){
        Call<Moods> call = emotlyServices.moods();
        call.enqueue(callback);
    }

}
