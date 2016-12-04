package com.example.dindin;

import com.example.dindin.com.example.AgeRange;
import com.example.dindin.com.example.Location;
import com.facebook.Profile;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Stefan on 12/1/2016.
 */

public class UserTest {

    Location loc1;
    Location loc2;
    Location loc3;
    Location loc4;
    Location loc5;
    Location loc6;
    Location loc7;
    Preferences pref1;
    Preferences pref2;
    Preferences pref3;
    Preferences pref4;
    Preferences pref5;
    Preferences pref6;
    Preferences pref7;
    AgeRange range1;
    AgeRange range2;
    AgeRange range3;
    AgeRange range4;
    AgeRange range5;
    AgeRange range6;
    AgeRange range7;
    User user1;
    User user2;
    User user3;
    User user4;
    User user5;
    User user6;
    User user7;
    FirebaseHelper fbHelp = new FirebaseHelper("test");

    @Before
    public void setUp(){
        loc1 = new Location(30.2672, -97.7431);
        loc2 = new Location(30.5083, -97.6789);
        loc3 = new Location(32.7767, -96.7970);
        loc4 = new Location(37.7749, -122.4194);
        loc7 = new Location(34.8892, -78.2934);
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
        preferredCuisine4.add("Korean");
        ArrayList<String> preferredCuisine5 = new ArrayList<>();
        range1 = new AgeRange(18, 24);
        range2 = new AgeRange(10, 100);
        range3 = new AgeRange(10, 70);
        range4 = new AgeRange(23, 29);
        range5 = new AgeRange(7, 8);
        pref1 = new Preferences("Cook", 20, range1, preferredCuisine1);
        pref2 = new Preferences("Clean", 18, range2, preferredCuisine2);
        pref3 = new Preferences("Clean", 27, range3, preferredCuisine3);
        pref4 = new Preferences("Cook", 13, range4, preferredCuisine4);
        pref5 = new Preferences("Cook", 1, range5, preferredCuisine1);
        pref6 = new Preferences("Clean", 200, range2, preferredCuisine5);
        HashMap<String, String> empty = new HashMap<>();
        user1 = new User(1, "123", "Test User1", loc1, 5.0, empty, empty, pref1);
        user1.setBirthday(new Date("09/23/1994"));
        user2 = new User(2, "234", "Test User2", loc2, 5.0, empty, empty, pref2);
        user2.setBirthday(new Date("09/23/1996"));
        user3 = new User(3, "345", "Test User3", loc3, 5.0, empty, empty, pref3);
        user3.setBirthday(new Date("09/23/1964"));
        user4 = new User(4, "456", "Test User4", loc4, 5.0, empty, empty, pref4);
        user4.setBirthday(new Date("09/23/2000"));
        user5 = new User(5, "567", "Test User5", loc1, 5.0, empty, empty, pref5);
        user5.setBirthday(new Date("09/23/1995"));
        user6 = new User(6, "678", "Test User6", loc3, 5.0, empty, empty, pref6);
        user6.setBirthday(new Date("09/23/1994"));
    }

    @Test
    /*
     * Check cases where user preferences should be compatible. Going for statement coverage.
     * Note* preferences don't need to be reflexive
     */
    public void testMatchesPreferences(){
        assertTrue(user1.matchesPreferences(user2));
        assertTrue(user3.matchesPreferences(user1));
        assertTrue(user2.matchesPreferences(user1));
        assertTrue(user3.matchesPreferences(user4));
        assertTrue(user3.matchesPreferences(user5));
    }

    @Test
    /*
     * Check cases where user preferences should be incompatible. Going for statement coverage.
     */
    public void testDoesntMatchPreferences(){
        assertFalse(user1.matchesPreferences(user3)); // Cuisine interests don't match.
        assertFalse(user4.matchesPreferences(user3)); // Age ranges don't match.
        assertFalse(user1.matchesPreferences(user1)); // Preferred roles don't match.
        assertFalse(user2.matchesPreferences(user6)); // Geographic location isn't a match.
    }

    @Test
    /*
     * Check cases where age/age range should be compatible. Looking for statement coverage.
     * Note* doesn't need to be reflexive.
     */
    public void testIsInAgeRange(){
        assertTrue(user1.isInAgeRange(user2)); // User2's age falls within user1's age range.
        assertTrue(user2.isInAgeRange(user1)); // In this case, user1 falls within user2's age range as well.
    }

    @Test
    /*
     * Check cases where age/age range should be incompatible.
     */
    public void testIsntInAgeRange(){
        assertFalse(user1.isInAgeRange(user3)); // User 3 is too young for user1's age range.
        assertFalse(user1.isInAgeRange(user4)); // Testing another case for good measure. user 3 too young for user 1.
    }

    @Test
    /*
     * Check cases where users' geographic location is compatible. Note* doesn't need to be reflexive.
     */
    public void testIsInPreferredRange(){
        assertTrue(user1.isInPreferredRange(user2));
        assertTrue(user2.isInPreferredRange(user1));
        assertTrue(user1.isInPreferredRange(user1));
    }

    @Test
    /*
     * Check cases where users' geographic location is incompatible.
     */
    public void testIsntInPreferredRange(){
        assertFalse(user1.isInPreferredRange(user3)); // User3 is in Dallas and user1 has a range of ~25 miles.
        assertFalse(user4.isInPreferredRange(user3)); // User4 is in San Francisco and User3 is in Dallas.
        assertFalse(user5.isInPreferredRange(user2)); // User 5 is in the literal middle of nowhere, and user 2's specified
                                                        // range does not extend that far.
    }

    @Test
    /*
     * See if users match providing they pass all the checks tested above.
     */
    public void testIsPotentialMatchFor(){
        assertTrue(user1.isPotentialMatchFor(user2)); // These two should meet all criteria.
        assertTrue(user2.isPotentialMatchFor(user1)); //   ...and they do so reflexively.
    }

    @Test
    /*
     * See if users don't match--looking for statement coverage here.
     */
    public void testIsntPotentialMatchFor(){
        assertFalse(user1.isPotentialMatchFor(user1)); // User1 shouldn't match with user 1 because they fail
                                                    // the preferences check.
        assertFalse(user3.isPotentialMatchFor(user4)); // User3 shouldn't match with 4 because they fail the
                                                    // age range check.
        assertFalse(user5.isPotentialMatchFor(user2)); // User 5 shouldn't match with 2 because they fail the
                                                    // geographic range check.
    }

    @Test
    public void testAddUser(){
        fbHelp.addUser(user1);
    }
}
