package com.example.dindin;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.assertion.ViewAssertions;

import com.example.dindin.com.example.NavBarActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Stephen on 11/27/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ProfilePageTest {

    @Rule public final ActivityRule<NavBarActivity> profile = new ActivityRule<>(NavBarActivity.class);

    @Test
    public void shouldHaveThese() {
        onView(withText("HELLO")).check(ViewAssertions.matches(isDisplayed()));
    }

}
