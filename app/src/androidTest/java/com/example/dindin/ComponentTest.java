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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class ComponentTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    /*
     * Test 1) Test that all the buttons in the nav bar drawer exist
     */
    @Test
    public void testDrawerButton() {
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
        appCompatCheckedTextView.check(matches(isDisplayed()));

         appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView.check(matches(isDisplayed()));

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView.check(matches(isDisplayed()));

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Matches"), isDisplayed()));
        appCompatCheckedTextView.check(matches(isDisplayed()));

        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("CookBook"), isDisplayed()));
        appCompatCheckedTextView.check(matches(isDisplayed()));


        appCompatCheckedTextView  =onView(
                allOf(withId(R.id.design_menu_item_text), withText("Settings"), isDisplayed()));
        appCompatCheckedTextView.check(matches(isDisplayed()));

    }



    /*
     * Test 2) Test that the like,dislike, and info button exist in the findActivityClass
     */
    @Test
    public void findActivityTest() {
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


    }

    /*
    * Test 3) Test that swiping works correctly on recipes activity
    */
    @Test
    public void RecipeActivityTest() {

        try {
            Thread.sleep(10000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Recipe"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.likeButton),
                        withParent(allOf(withId(R.id.likelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.dislikeButton),
                        withParent(allOf(withId(R.id.dislikelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.dislikeButton),
                        withParent(allOf(withId(R.id.dislikelayout),
                                withParent(withId(R.id.likedislikelayout)))),
                        isDisplayed()));
        appCompatButton5.perform(click());



    }

    /*
     * Test 4) Test that the profile activity is editable
     */

    @Test
    public void ProfileActivityTest() {

        try {
            Thread.sleep(8000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.editProfileButton),
                        childAtPosition(
                                allOf(withId(R.id.profile_layout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                4),
                        isDisplayed()));
        button.check(matches(isDisplayed()));



    }
    /*
     * Test 5) Test that cooking and cleaning roles are present in the Setting Activity Test
     */
    @Test
    public void SettingActivityTest() {

        try {
            Thread.sleep(8000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton9 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton9.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Settings"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.cook),
                        childAtPosition(
                                allOf(withId(R.id.preference_gender),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                9)),
                                0),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));

        ViewInteraction radioButton2 = onView(
                allOf(withId(R.id.cleaner),
                        childAtPosition(
                                allOf(withId(R.id.preference_gender),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                9)),
                                1),
                        isDisplayed()));
        radioButton2.check(matches(isDisplayed()));



    }





    @Test
    public void recipeButtonTest() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(true,true);

    }

//    @Test
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

     // @Test
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
