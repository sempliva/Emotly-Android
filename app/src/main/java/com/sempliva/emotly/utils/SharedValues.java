package com.sempliva.emotly.utils;

import com.sempliva.emotly.rest.data.Mood;

import java.util.ArrayList;

/**
 * Created by ggc on 02/05/16.
 */
public class SharedValues {

    private static SharedValues mInstance;
    private ArrayList<Mood> mMoods;

    private SharedValues(){

    }

    public static SharedValues getInstance(){
        if(mInstance == null){
            mInstance = new SharedValues();
        }

        return mInstance;
    }

    public void setMoods(ArrayList<Mood> moods){
        if(moods != null)
            mMoods = moods;
    }

    public ArrayList<Mood> getMoods(){
        return mMoods;
    }
}
