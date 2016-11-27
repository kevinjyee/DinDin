package com.example.dindin.ComponentTest;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.dindin.R;
import com.example.dindin.SplashActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FindMatchesTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void findMatchesTest() {
        ViewInteraction loginButton = onView(
                allOf(ViewMatchers.withId(R.id.login_button), withText("Log in with Facebook"), isDisplayed()));
        loginButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.dislikeButton),
                        childAtPosition(
                                allOf(withId(R.id.dislikelayout),
                                        childAtPosition(
                                                withId(R.id.likedislikelayout),
                                                0)),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.matchedUserInfoButton),
                        childAtPosition(
                                allOf(withId(R.id.infobuttonlaout),
                                        childAtPosition(
                                                withId(R.id.likedislikelayout),
                                                1)),
                                0),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.likeButton),
                        childAtPosition(
                                allOf(withId(R.id.likelayout),
                                        childAtPosition(
                                                withId(R.id.likedislikelayout),
                                                2)),
                                0),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withText("Liked"),
                        withParent(allOf(withId(R.id.match_user_detail_layout),
                                withParent(withId(R.id.swipeviewlayout)))),
                        isDisplayed()));
        button4.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
