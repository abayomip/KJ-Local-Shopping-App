package com.example.kjlocalshoppingapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.util.Log;




import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;


public class AddProductTest {
    @Rule
    public ActivityScenarioRule<AddProduct> activityRule = new ActivityScenarioRule<>(AddProduct.class);

    private AddProduct getActivity() {
        AtomicReference<AddProduct> activityRef = new AtomicReference<>();
        activityRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }


    @Test
    public void testAddProduct() {
        onView(withId(R.id.txtProductId)).perform(typeText("1"));
        onView(withId(R.id.txtProductName)).perform(typeText("product"));
        onView(withId(R.id.txtProductDis)).perform(typeText("Description"));
        onView(withId(R.id.txtProductPrice)).perform(typeText("11.11"));
        onView(withId(R.id.txtProductListPrice)).perform(typeText("10"));
        onView(withId(R.id.txtProductRetailPrice)).perform(typeText("10"));
        onView(withId(R.id.txtCategory)).perform(typeText("category"));
        onView(withId(R.id.txtDateCreated)).perform(typeText("13/07/2023"));
        //closing the soft keyboard if its open not to obstruct the input
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.txtdateUpdated)).perform(typeText("13/07/2023"));
        //closing the soft keyboard if its open not to obstruct the input
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnAddProduct)).perform(click());
        //creating instance of the db and passing in the result of the getActivity() mtd as an argument to the constructor
        databasecon db = new databasecon(getActivity());
       // this line calls the db.getProdById method on the db object, passing in value 1
        ProductCtlr productTest = db.getProdById(1);
        //using assertNotNull method to check that the value of the productTest is not null
        assertNotNull((productTest));
    }
}
