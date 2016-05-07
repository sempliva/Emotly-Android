package com.sempliva.emotly.rest;

import com.sempliva.emotly.rest.data.LoginData;
import com.sempliva.emotly.rest.data.JWT;
import com.sempliva.emotly.rest.data.Moods;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ggc on 28/04/16.
 */
public interface EmotlyServices {

    // LOGIN

    @POST("login")
    Call<JWT> login(@Body LoginData data);

    //EMOTLY

    @GET("moods")
    Call<Moods> moods();

}
