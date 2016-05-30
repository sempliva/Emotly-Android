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
