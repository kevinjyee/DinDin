package com.example.dindin;

import android.app.Application;

import com.example.dindin.com.example.AgeRange;
import com.example.dindin.com.example.Location;
import com.facebook.Profile;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Stefan on 10/23/2016.
 */

public class JSONTest extends Application {

    public JSONTest(){

    }

    @Override
    public void onCreate(){
        super.onCreate();
        testJSON();
    }

    public void testJSON(){
        ObjectMapper mapper = new ObjectMapper();
        int uID = 1;
        String uName = "Tom";
        Location uLoc = new Location(1, 2);
        Profile uProfile = null;
        double uRating = 3.9;
        AgeRange range = new AgeRange(18, 22);
        Set<String> prefCuisine = new HashSet<String>();
        prefCuisine.add("Asian");
        prefCuisine.add("Indian");
        prefCuisine.add("American");
        Preferences prefs = new Preferences("Cook", 15, range, prefCuisine);
        Queue<Integer> potMatches = new PriorityQueue<Integer>();
        potMatches.add(4);
        potMatches.add(2);
        potMatches.add(3);
        ArrayList<Integer> finMatches = new ArrayList<Integer>();
        finMatches.add(5);
        finMatches.add(6);
        finMatches.add(7);
        User testUser = new User(uID, uName, uLoc, uProfile, uRating, potMatches, finMatches, prefs);
        try{
            String jsonUser = mapper.writeValueAsString(testUser);
            System.out.println(jsonUser);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
