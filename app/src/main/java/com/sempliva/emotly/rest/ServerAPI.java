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
 * Created by ggc on 28/04/16.
 */
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

    public void login(String userID, String password, Callback<JWT> callback){
        LoginData loginData = new LoginData();
        loginData.user_id = userID;
        loginData.password = password;
        Call<JWT> call = emotlyServices.login(loginData);
        call.enqueue(callback);
    }

    public void getMoods(Callback<Moods> callback){
        Call<Moods> call = emotlyServices.moods();
        call.enqueue(callback);
    }

}
