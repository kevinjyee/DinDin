package com.example.dindin;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TransitionTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void transitionTest() {
        ViewInteraction loginButton = onView(
                allOf(withId(R.id.login_button), withText("Log in with Facebook"), isDisplayed()));
        loginButton.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Find"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatCheckedTextView4 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView4.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatCheckedTextView5 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("CookBook"), isDisplayed()));
        appCompatCheckedTextView5.perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction appCompatCheckedTextView6 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Settings"), isDisplayed()));
        appCompatCheckedTextView6.perform(click());

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction appCompatCheckedTextView7 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("CookBook"), isDisplayed()));
        appCompatCheckedTextView7.perform(click());

        ViewInteraction appCompatImageButton8 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton8.perform(click());

        ViewInteraction appCompatCheckedTextView8 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView8.perform(click());

        ViewInteraction appCompatImageButton9 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton9.perform(click());

        ViewInteraction appCompatCheckedTextView9 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView9.perform(click());

        ViewInteraction appCompatImageButton10 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton10.perform(click());

        ViewInteraction appCompatCheckedTextView10 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView10.perform(click());

        ViewInteraction appCompatImageButton11 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton11.perform(click());

        ViewInteraction appCompatCheckedTextView11 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Find"), isDisplayed()));
        appCompatCheckedTextView11.perform(click());

        ViewInteraction appCompatImageButton12 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton12.perform(click());

        ViewInteraction appCompatCheckedTextView12 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView12.perform(click());

        ViewInteraction appCompatImageButton13 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton13.perform(click());

        ViewInteraction appCompatCheckedTextView13 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("CookBook"), isDisplayed()));
        appCompatCheckedTextView13.perform(click());

        ViewInteraction appCompatImageButton14 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton14.perform(click());

        ViewInteraction appCompatCheckedTextView14 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView14.perform(click());

        ViewInteraction appCompatImageButton15 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton15.perform(click());

        ViewInteraction appCompatCheckedTextView15 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView15.perform(click());

        ViewInteraction appCompatImageButton16 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton16.perform(click());

        ViewInteraction appCompatCheckedTextView16 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Settings"), isDisplayed()));
        appCompatCheckedTextView16.perform(click());

    }

}
