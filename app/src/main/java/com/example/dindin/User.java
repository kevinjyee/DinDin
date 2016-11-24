package com.example.dindin;


import com.example.dindin.com.example.AgeRange;
import com.example.dindin.com.example.Location;
import com.facebook.Profile;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class User {
    private int id;
    private Date birthday;
    private String name;
    private Location location;
    private Profile facebookProfile;
    private double userRating;
    private Queue<Integer> potentialMatches;
    private ArrayList<Integer> finalizedMatches;
    private Preferences preferences;
    @SerializedName("fbId")

    private String fbId;
    public User()
    {
    }

    public User(String userId)
    {
        this.fbId = userId;
    }

    public User(int userId, String userName, Location userLoc, Profile userProfile, double rating,
                Queue<Integer> potMatches, ArrayList<Integer> finMatches, Preferences prefs)
    {

        this.name = userName;
        this.location = userLoc;
        this.facebookProfile = userProfile;
        this.userRating = rating;
        this.potentialMatches = potMatches;
        this.finalizedMatches = finMatches;
        this.preferences = prefs;
    }

    public User createDefaultUser(){
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
        return new User(uID, uName, uLoc, uProfile, uRating, potMatches, finMatches, prefs);
    }

    public String getfbId() {
        return fbId;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Profile getFacebookProfile() {
        return facebookProfile;
    }

    public Queue<Integer> getPotentialMatches() {
        return potentialMatches;
    }

    public double getUserRating() {
        return userRating;
    }

    public ArrayList<Integer> getFinalizedMatches() {
        return finalizedMatches;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setfbId(String id) {
        this.fbId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setFacebookProfile(Profile facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public void setPotentialMatches(Queue<Integer> potentialMatches) {
        this.potentialMatches = potentialMatches;
    }

    public void setFinalizedMatches(ArrayList<Integer> finalizedMatches) {
        this.finalizedMatches = finalizedMatches;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public boolean matchesPreferences(User user2){
        if(this.getPreferences().areComplementaryRoles(user2.getPreferences()) &&
                this.isInAgeRange(user2) &&
                this.getPreferences().shareCuisineInterests(user2.getPreferences())){
            return true;
        } else{
            return false;
        }
    }

    public boolean isInAgeRange(User user2){
        return false;
    }

    public boolean isInPreferredRange(User user2){
        return false;
    }
}

