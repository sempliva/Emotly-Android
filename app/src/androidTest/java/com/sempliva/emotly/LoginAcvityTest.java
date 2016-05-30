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

package com.sempliva.emotly;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sempliva.emotly.ui.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by ggc on 07/05/16.
 * Copyright © 2016 com.sempliva. All rights reserved.
 */

// Login Activity test cases.
@RunWith(AndroidJUnit4.class)
public class LoginAcvityTest {

    @Rule
    public final ActivityTestRule<LoginActivity> main = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void activityStarted() {
        //check the activity is started and the login button is displayed
        onView(withId(R.id.bt_login)).check(matches(isDisplayed()));
    }

    @Test
    public void loginNoData() {
        //click on login button
        onView(withId(R.id.bt_login)).perform(click());
        //check toast message is displayed
        onView(withText(R.string.TOAST_INSERT_DATA)).inRoot(withDecorView(not(main.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }


}
