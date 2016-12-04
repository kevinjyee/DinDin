package com.example.dindin;

/**
 * Created by Stefan on 12/4/2016.
 */

public class dindinProfile {
    private String customLocation;
    private String shortBio;
    private String favoriteDishes;
    private String favoriteCuisines;

    public dindinProfile(){
        this.customLocation = "";
        this.shortBio = "";
        this.favoriteDishes = "";
        this.favoriteCuisines = "";
    }

    public String getCustomLocation(){
        return this.customLocation;
    }

    public String getShortBio(){
        return this.shortBio;
    }

    public String getFavoriteDishes(){
        return this.favoriteDishes;
    }

    public String getFavoriteCuisines(){
        return this.favoriteCuisines;
    }

    public void setCustomLocation(String loc){
        this.customLocation = loc;
    }

    public void setShortBio(String bio){
        this.shortBio = bio;
    }

    public void setFavoriteDishes(String dishes){
        this.favoriteDishes = dishes;
    }

    public void setFavoriteCuisines(String cuisines){
        this.favoriteCuisines = cuisines;
    }
}
