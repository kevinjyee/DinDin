package com.example.dindin;

import com.example.dindin.com.example.Location;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Stefan on 12/1/2016.
 */

public class LocationTest {

    Location Austin;
    Location Dallas;
    Location San_Francisco;
    Location Round_Rock;
    Location UT_Campus;
    Location Beijing;

    @Before
    public void setUp(){
        Austin = new Location(30.2672, -97.7431);
        Dallas = new Location(32.7767, -96.7970);
        San_Francisco = new Location(37.7749, -122.4194);
        Round_Rock = new Location(30.5083, -97.6789);
        UT_Campus = new Location(30.2849, -97.7341);
        Beijing = new Location(39.9042, 116.4074);
    }

    @Test
    public void testIsInRange(){
        assertTrue(Austin.isInRange(Dallas, 183));
        assertTrue(Austin.isInRange(Dallas, 184));
        assertTrue(Beijing.isInRange(Austin, 8000));
        assertTrue(Round_Rock.isInRange(Austin, 30));
        assertTrue(Austin.isInRange(UT_Campus, 10));
        assertTrue(Austin.isInRange(Austin, 0));
    }

    @Test
    public void testNotInRange(){
        assertFalse(Austin.isInRange(Dallas, 182));
        assertFalse(Austin.isInRange(Austin, -1));
        assertFalse(Austin.isInRange(Round_Rock, 10));
        assertFalse(Austin.isInRange(Beijing, 10));
        assertFalse(Round_Rock.isInRange(Dallas, 150));
        assertFalse(San_Francisco.isInRange(Beijing, 1000));
    }
}
