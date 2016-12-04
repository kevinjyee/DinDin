package com.example.dindin;

import com.example.dindin.com.example.AgeRange;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by Stefan on 12/1/2016.
 */

public class PreferencesTest {

    Preferences pref1;
    Preferences pref2;
    Preferences pref3;
    Preferences pref4;

    @Before
    public void setUp(){
        ArrayList<String> preferredCuisine1 = new ArrayList<String>();
        preferredCuisine1.add("Mexican");
        preferredCuisine1.add("Chinese");
        preferredCuisine1.add("Indian");
        preferredCuisine1.add("American");
        preferredCuisine1.add("Trash");
        ArrayList<String> preferredCuisine2 = new ArrayList<String>();
        preferredCuisine2.add("Trash");
        ArrayList<String> preferredCuisine3 = new ArrayList<String>();
        preferredCuisine3.add("Chinese");
        preferredCuisine3.add("Korean");
        ArrayList<String> preferredCuisine4 = new ArrayList<String>();
        pref1 = new Preferences("Cook", 20, new AgeRange(18, 23), preferredCuisine1);
        pref2 = new Preferences("Clean", 18, new AgeRange(17, 20), preferredCuisine2);
        pref3 = new Preferences("Clean", 27, new AgeRange(8, 25), preferredCuisine3);
        pref4 = new Preferences("Cook", 13, new AgeRange(60, 77), preferredCuisine4);
    }

    @Test
    public void testComplementaryRoles(){
        assertTrue(pref1.areComplementaryRoles(pref2));
        assertTrue(pref1.areComplementaryRoles(pref3));
        assertTrue(pref2.areComplementaryRoles(pref1));
        assertTrue(pref3.areComplementaryRoles(pref1));
    }

    @Test
    public void testNotComplementaryRoles(){
        assertFalse(pref2.areComplementaryRoles(pref3));
        assertFalse(pref1.areComplementaryRoles(pref1));
        assertFalse(pref2.areComplementaryRoles(pref2));
        assertFalse(pref3.areComplementaryRoles(pref3));
    }

    @Test
    public void testShareCuisineInterests(){
        assertTrue(pref1.shareCuisineInterests(pref2));
        assertTrue(pref2.shareCuisineInterests(pref1));
        assertTrue(pref1.shareCuisineInterests(pref3));
        assertTrue(pref3.shareCuisineInterests(pref1));
        assertTrue(pref1.shareCuisineInterests(pref1));
        assertTrue(pref2.shareCuisineInterests(pref2));
        assertTrue(pref3.shareCuisineInterests(pref3));
    }

    @Test
    public void testDontShareCuisineInterests(){
        assertFalse(pref2.shareCuisineInterests(pref3));
        assertFalse(pref3.shareCuisineInterests(pref2));
        assertFalse(pref1.shareCuisineInterests(pref4));
        assertFalse(pref2.shareCuisineInterests(pref4));
        assertFalse(pref3.shareCuisineInterests(pref4));
        assertFalse(pref4.shareCuisineInterests(pref4));
    }
}
