package com.example.dindin.com.example;

/**
 * Created by Stefan on 10/22/2016.
 */

public class AgeRange {
    private int minAge;
    private int maxAge;

    public AgeRange(){

    }

    public AgeRange(int minimumAge, int maximumAge){
        this.minAge = minimumAge;
        this.maxAge = maximumAge;
    }

    public int getMinAge(){
        return this.minAge;
    }

    public int getMaxAge(){
        return this.maxAge;
    }

    public void setMinAge(int minimumAge){
        this.minAge = minimumAge;
    }

    public void setMaxAge(int maximumAge){
        this.maxAge = maximumAge;
    }

    public boolean isInAgeRange(int age_in_years){
        return (age_in_years >= minAge) && (age_in_years <= maxAge);
    }
}
