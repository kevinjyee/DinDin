package com.example.dindin.com.example;

/**
 * Created by Stefan on 10/22/2016.
 */

public class Location {
    private double latitude;
    private double longitude;

    public Location(){
    }

    public Location(double lat, double lon){
        this.latitude = lat;
        this.longitude = lon;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public void setLatitude(double lat){
        this.latitude = lat;
    }

    public void setLongitude(double lon){
        this.longitude = lon;
    }

}
