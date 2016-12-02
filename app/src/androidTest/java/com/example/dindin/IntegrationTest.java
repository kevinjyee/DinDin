package com.example.dindin;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.core.deps.dagger.Component;
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class IntegrationTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    /*
     * Test 1) Test that all the transitions act smoothly
     */
    @Test
    public void testTransitions() {
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Find"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView.perform(click());


        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("CookBook"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Settings"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("CookBook"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Find"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView.perform(click());





    }


    /*
     * Test 2) Test match list is properly updated
     */
    @Test
    public void testMatchesDB() {
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Find"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button3), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.dislikeButton),
                        withParent(allOf(withId(R.id.dislikelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Davin Siu"),
                        childAtPosition(
                                allOf(withId(R.id.storybookTextItemlayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Davin Siu")));

    }



    /*
     * Test 3) Test Recipe list in CookBook is properly updated
     */
    @Test
    public void recipeMatchesDB()
    {
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
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
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction appCompatCheckedTextView5 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("CookBook"), isDisplayed()));
        appCompatCheckedTextView5.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("Click for Recipe!"),
                        childAtPosition(
                                allOf(withId(R.id.storybookTextItemlayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                1)),
                                1),
                        isDisplayed()));


    }

    /*
    * Test 3) Test Recipe list in CookBook is properly updated
    */
    @Test
    public void testMessaging()
    {
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Find"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton1 = onView(
                allOf(withId(android.R.id.button3), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton1.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.albumlistviewitem),
                        childAtPosition(
                                allOf(withId(R.id.menu_right_ListView),
                                        withParent(withId(R.id.macheslistviewlayout))),
                                0),
                        isDisplayed()));
        relativeLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.messageEditText),
                        withParent(withId(R.id.linearLayout)),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("hi"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.sendButton), withText("SEND"),
                        withParent(withId(R.id.linearLayout)),
                        isDisplayed()));
        appCompatButton2.perform(click());


    }



    //@Test
    public void recipeButtonTest() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true,true);

    }

    //@Test
    public void profileButtonTest() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(true,true);
    }
    //@Test
    public void matchesButtonTest() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(true,true);
    }

    //@Test
    public void cookBookButtonTest() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(true,true);
    }

    //@Test
    public void settingsButtonTest() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(true,true);

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
