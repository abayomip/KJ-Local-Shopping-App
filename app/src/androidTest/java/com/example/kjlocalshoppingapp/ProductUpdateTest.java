package com.example.kjlocalshoppingapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static junit.framework.TestCase.assertEquals;

import androidx.test.core.app.ActivityScenario;

import org.junit.Test;

public class ProductUpdateTest {


    @Test
    public void testBtnUpdateOnAclick() {

        int position = 0; // setting the position  to update
        String productName = "samsung galaxy 360"; // setting product new name

        ActivityScenario<ViewProductList> scenario = ActivityScenario.launch(ViewProductList.class);

        // setting current position in the activity
        scenario.onActivity(activity -> activity.currentposition = position);

        //clear text and type new product name
        onView(withId(R.id.txtProductName)).perform(clearText(), typeText(productName));

        onView(withId(R.id.btnUpdate)).perform(click());

        // Check that the product was updated with the new product name
        scenario.onActivity(activity->{
            ProductCtlr updatedProduct = activity.productCtlrList.get(position);
            assertEquals(productName, updatedProduct.getProductName());
        });
    }
}


