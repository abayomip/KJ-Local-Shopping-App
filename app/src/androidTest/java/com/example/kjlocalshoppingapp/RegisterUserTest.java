package com.example.kjlocalshoppingapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class RegisterUserTest {


    @Rule
    public ActivityScenarioRule<Register> activityRule = new ActivityScenarioRule<>(Register.class);

    private Register getActivity() {
        AtomicReference<Register> activityRef = new AtomicReference<>();
        activityRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }

    @Test
    public void testUserRegister() {
        onView(withId(R.id.txtId)).perform(typeText("1"));
        onView(withId(R.id.edtUsername)).perform(typeText("test"));
        onView(withId(R.id.edtPassword)).perform(typeText("test"));
        onView(withId(R.id.edtFullname)).perform(typeText("test test"));
        onView(withId(R.id.edtEmail)).perform(typeText("test@test.com"));
        onView(withId(R.id.edtDateRegistered)).perform(typeText("13/07/2023"));
        onView(withId(R.id.edtDateReviewed)).perform(typeText("13/07/2023"));
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard()); //closing the soft keyboard if its open not to obstruct the input
        onView(withId(R.id.edtHobbies)).perform(typeText("test"));
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edtAddress)).perform(typeText("5 test test"));
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edtPostcode)).perform(typeText("mk1 1tt"));
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edtRank)).perform(typeText("test"));
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnAdd)).perform(click());
        //creating instance of the db and passing in the result of the getActivity() mtd as an argument to the constructor
        databasecon db = new databasecon(getActivity());
        // this line calls the db.getUserById method on the db object, passing in value 1
        User Test = db.getUserById(1);
        //using assertNotNull method to check that the value of the Test is not null
        assertNotNull((Test));
    }

}
