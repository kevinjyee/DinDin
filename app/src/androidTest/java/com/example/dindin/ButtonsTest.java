package com.example.dindin;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ButtonsTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void buttonsTest() {
        ViewInteraction loginButton = onView(
                allOf(withId(R.id.login_button), withText("Log in with Facebook"), isDisplayed()));
        loginButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.dislikeButton),
                        withParent(allOf(withId(R.id.dislikelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.matchedUserInfoButton),
                        withParent(allOf(withId(R.id.infobuttonlaout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button3), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.dislikeButton),
                        withParent(allOf(withId(R.id.dislikelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.matchedUserInfoButton),
                        withParent(allOf(withId(R.id.infobuttonlaout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(android.R.id.button3), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton8.perform(click());

    }

}
