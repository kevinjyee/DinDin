package com.example.dindin;

import com.example.dindin.com.example.AgeRange;

import org.junit.*;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Stefan on 12/1/2016.
 */

public class AgeRangeTests {

    AgeRange emptyAgeRange;
    AgeRange typicalAgeRange;
    AgeRange invalidAgeRange;
    AgeRange illegalAgeRange;
    Date StefanBD;
    Date AmyBD;
    Date nullBD;
    Date TravisBD;
    Date oldBD;
    Date babyBD;
    int StefanAge = 22;
    int AmyAge = 26;
    int nullAge = 0;
    int TravisAge = 23;
    int oldAge = 116;
    int babyAge = 0;


    @Before
    // Informs JUnit that this method should be run before each test
    public void setUp() {
        emptyAgeRange = new AgeRange();
        typicalAgeRange = new AgeRange(20, 24);
        illegalAgeRange = new AgeRange(0, 2);
        invalidAgeRange = new AgeRange(-1, -2);
        StefanBD = new Date("09/23/1994");
        AmyBD = new Date("06/14/1990");
        nullBD = null;
        TravisBD = new Date("08/15/1993");
        oldBD = new Date("11/30/1900");
        babyBD = new Date("12/1/2016");
    }



    @Test
    /* testOutOfAgeRange()
     * Test if AgeRange properly detects cases where a birthday puts someone out of AgeRange.
     */
    public void testOutOfAgeRange(){
        assertFalse(typicalAgeRange.isInAgeRange(oldAge));
        assertFalse(typicalAgeRange.isInAgeRange(AmyAge));
        assertFalse(illegalAgeRange.isInAgeRange(StefanAge));
        assertFalse(illegalAgeRange.isInAgeRange(TravisAge));
        assertFalse(illegalAgeRange.isInAgeRange(oldAge));
    }

    /* testInAgeRange()
     * Test if AgeRange properly detects cases where a birthday puts someone inside AgeRange.
     */
    @Test
    public void testInRange(){
        assertTrue(typicalAgeRange.isInAgeRange(StefanAge));
        assertTrue(typicalAgeRange.isInAgeRange(TravisAge));
        assertTrue(illegalAgeRange.isInAgeRange(babyAge));
    }

    @Test
    public void invalidAgeRange(){
        assertFalse(invalidAgeRange.isInAgeRange(StefanAge));
    }

    @Test
    public void emptyAgeRange(){ // AgeRanges are initialized to the widest possible value, so this should return true.
        assertTrue(emptyAgeRange.isInAgeRange(StefanAge));
    }

    @Test
    public void testFindAge(){
        assertEquals(-1, AgeRange.findAge(nullBD));
        assertEquals(22, AgeRange.findAge(StefanBD));
        assertEquals(23, AgeRange.findAge(TravisBD));
        assertEquals(116, AgeRange.findAge(oldBD));
        assertEquals(0, AgeRange.findAge(babyBD));
    }

    @Test
    public void getDiffYears(){
        assertEquals(1, AgeRange.getDiffYears(TravisBD, StefanBD));
        assertEquals(93, AgeRange.getDiffYears(oldBD, StefanBD));
        assertEquals(22, AgeRange.getDiffYears(StefanBD, babyBD));
        assertEquals(-1, AgeRange.getDiffYears(null, StefanBD));
    }

    @After
    public void tearDown() {

    }
}
