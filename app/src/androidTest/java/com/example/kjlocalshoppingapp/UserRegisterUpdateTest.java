package com.example.kjlocalshoppingapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

public class UserRegisterUpdateTest {


    @Test
    public void testBtnUpdateOnAclick() {

        int position = 0; // setting the position  to update
        String password = "ali"; // setting user password

        ActivityScenario<ViewRegisteredUsers> scenario = ActivityScenario.launch(ViewRegisteredUsers.class);

        // setting current position in the activity
        scenario.onActivity(activity -> activity.currentposition = position);

        //clear text and type new product name
        onView(withId(R.id.edtPassword)).perform(clearText(), typeText(password));

        // click update button
        onView(withId(R.id.btnUpdate)).perform(click());

        // check the user password was updated
        scenario.onActivity(activity->{
            User updatedUser = activity.userList.get(position);
            assertEquals(password, updatedUser.getPassword());
        });
    }
}
