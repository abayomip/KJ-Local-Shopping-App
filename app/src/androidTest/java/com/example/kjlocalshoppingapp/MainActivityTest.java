package com.example.kjlocalshoppingapp;

import static android.os.Trace.isEnabled;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static org.hamcrest.Matchers.not;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void checkLoginBtnIsDisabledWhenFieldsAreNotEmpty() {
        //finding a view the ID and performs the cleartext action on it,clearing any text if present
        onView(withId(R.id.edtUsername)).perform(clearText());
        onView(withId(R.id.edtUsername)).perform(clearText());
        //find a view with the ID and check that is not enabled by using the matches method with the not and is enabled matcher
        onView(withId(R.id.btnLogin)).check(matches(not(isEnabled())));

        }


    @Test
    public void checkLoginBtnIsEnabledOnlyWhenFieldsAreNotEmpty(){
        //finding a view the ID and performs the typetext action on it,inserting the details into fields
        onView(withId(R.id.edtUsername)).perform(typeText("username"));
        onView(withId(R.id.edtPassword)).perform(typeText("password"));
        //find a view with the ID and check that its enabled by using the matches method with the isEnabled matcher. If the view is not enabled the test will fail
        onView(withId(R.id.btnLogin)).check(matches(ViewMatchers.isEnabled()));

}


    }

