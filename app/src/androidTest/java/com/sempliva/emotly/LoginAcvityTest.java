package com.sempliva.emotly;

import android.support.test.espresso.assertion.ViewAssertions;
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
 */
@RunWith(AndroidJUnit4.class)
public class LoginAcvityTest {

    @Rule public final ActivityTestRule<LoginActivity> main = new ActivityTestRule<>(LoginActivity.class);

    @Test public void activityStarted(){
        //check the activity is started and the login button is displayed
        onView(withId(R.id.bt_login)).check(matches(isDisplayed()));
    }

    @Test public void loginNoData(){
        //click on login button
        onView(withId(R.id.bt_login)).perform(click());
        //check toast message is displayed
        onView(withText(R.string.TOAST_INSERT_DATA)).inRoot(withDecorView(not(main.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
}
