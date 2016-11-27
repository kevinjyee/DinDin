package com.example.dindin;


import android.telephony.TelephonyManager;

import com.example.dindin.com.example.AgeRange;
import com.example.dindin.com.example.Location;
import com.facebook.Profile;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class User implements Serializable{

    private int id;
    private Date birthday;
    private String name;
    private Location location;
    private Profile facebookProfile;
    private double userRating;
    private HashMap<String, String> potentialMatches;
    private HashMap<String, String> swipedLeft;
    private HashMap<String, String> swipedRight;
    private HashMap<String, String> finalizedMatches;
    private Preferences preferences;
    @SerializedName("fbId")

    private String fbId;
    private String age;
    private String phoneNumber = "713-478-3035";

    public User()
    {
    }

    public User(String userId, String firstName, String Age)
    {
        this.fbId = userId;
        this.name = firstName;
        this.age = Age;
    }

    public User(int userId, String fbID, String userName, Location userLoc, Profile userProfile, double rating,
                HashMap<String, String> potMatches, HashMap<String, String> finMatches, Preferences prefs)
    {
        this.id = userId;
        this.fbId = fbID;
        this.name = userName;
        this.location = userLoc;
        this.facebookProfile = userProfile;
        this.userRating = rating;
        this.potentialMatches = potMatches;
        this.finalizedMatches = finMatches;
        this.preferences = prefs;
    }

    public static User createDefaultUser(){
        int uID = 1;
        String fbID = "1";
        String uName = "Tom";
        Location uLoc = new Location(1, 2);
        Profile uProfile = null;
        double uRating = 3.9;
        AgeRange range = new AgeRange(18, 22);
        ArrayList<String> prefCuisine = new ArrayList<String>();
        prefCuisine.add("Asian");
        prefCuisine.add("Indian");
        prefCuisine.add("American");
        Preferences prefs = new Preferences("Cook", 15, range, prefCuisine);
        HashMap<String, String> potMatches = new HashMap<String, String>();
        potMatches.put("4", "4");
        potMatches.put("2", "2");
        potMatches.put("3", "3");
        HashMap<String, String> finMatches = new HashMap<String, String>();
        finMatches.put("5", "5");
        finMatches.put("6", "6");
        finMatches.put("7", "7");
        return new User(uID, fbID, uName, uLoc, uProfile, uRating, potMatches, finMatches, prefs);
    }

    public int getId() {
        return id;
    }

    public String getfbId() {
        return fbId;
    }

    public Date getBirthday(){ return birthday; }

    public String getName() {
        return name;
    }

    public String getAge() {return age;}

    public Location getLocation() {
        return location;
    }

    public Profile getFacebookProfile() {
        return facebookProfile;
    }

    public HashMap<String, String> getPotentialMatches() {
        return potentialMatches;
    }

    public HashMap<String, String> getSwipedLeft() {
        return swipedLeft;
    }

    public HashMap<String, String> getSwipedRight() {
        return swipedRight;
    }

    public double getUserRating() {
        return userRating;
    }

    public HashMap<String, String> getFinalizedMatches() {
        return finalizedMatches;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setfbId(String id) {
        this.fbId = id;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBirthday(Date date){ this.birthday = date; }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public void setPhoneNumber(String number){this.phoneNumber = number;};


    public String getPhoneNumber(){return this.phoneNumber; }

    public void setFacebookProfile(Profile facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public void setPotentialMatches(HashMap<String, String> potentialMatches) {
        this.potentialMatches = potentialMatches;
    }

    public void setSwipedLeft(HashMap<String, String> swipedLeft) {
        this.swipedLeft = swipedLeft;
    }

    public void setSwipedRight(HashMap<String, String> swipedRight) {
        this.swipedRight = swipedRight;
    }

    public void setFinalizedMatches(HashMap<String, String> finalizedMatches) {
        this.finalizedMatches = finalizedMatches;
    }

    public static User createUserFromProfile(Profile fbProfile){
        User newUser = new User();
        newUser.setfbId(fbProfile.getId());
        newUser.setName(fbProfile.getName());
        newUser.setAge("42");

        return newUser;
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
        return this.getPreferences().getAgeRange().isInAgeRange(user2.getBirthday());
    }

    // TODO
    public boolean isInPreferredRange(User user2){
        return false;
    }

}

