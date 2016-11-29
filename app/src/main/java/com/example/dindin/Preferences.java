package com.example.dindin;

import com.example.dindin.com.example.AgeRange;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Davin on 10/20/2016.
 */

public class Preferences implements Serializable{
    private String preferredTask;
    private double maxMatchDistance; //radius size (miles) within user location for matching
    private AgeRange ageRange;
    private ArrayList<String> preferredCuisines;

    public Preferences(){

    }

    public Preferences(String prefTask, double matchDistance, AgeRange prefAgeRange, ArrayList<String> cuisines){
        this.preferredTask = prefTask;
        this.maxMatchDistance = matchDistance;
        this.ageRange = prefAgeRange;
        this.preferredCuisines = cuisines;
    }

    public Preferences(String prefTask)
    {
        this.preferredTask = prefTask;
    }

    public String getPreferredTask(){
        return this.preferredTask;
    }

    public double getMaxMatchDistance(){
        return this.maxMatchDistance;
    }

    public AgeRange getAgeRange(){
        return this.ageRange;
    }

    public ArrayList<String> getPreferredCuisines(){
        return this.preferredCuisines;
    }

    public void setPreferredTask(String task){
        this.preferredTask = task;
    }

    public void setMaxMatchDistance(double distance){
        this.maxMatchDistance = distance;
    }

    public void setAgeRange(AgeRange range){
        this.ageRange = range;
    }

    public void setPreferredCuisines(ArrayList<String> cuisinePreferences){
        this.preferredCuisines = cuisinePreferences;
    }

    public boolean areComplementaryRoles(Preferences pref2){
        if((this.getPreferredTask().equalsIgnoreCase("cook") &&
                pref2.getPreferredTask().equalsIgnoreCase("clean")) ||
                (this.getPreferredTask().equalsIgnoreCase("clean") &&
                        pref2.getPreferredTask().equalsIgnoreCase("cook"))){
            return true;
        } else{
            return false;
        }
    }

    public boolean isInAgeRange(Preferences pref2){
        return false; // PLACEHOLDER. FIX THIS
    }

    public boolean shareCuisineInterests(Preferences pref2){
        return !Collections.disjoint(this.getPreferredCuisines(), pref2.getPreferredCuisines());
    }
}
