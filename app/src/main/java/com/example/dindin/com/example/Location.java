package com.example.dindin.com.example;

import java.io.Serializable;
import java.math.*;

/**
 * Created by Stefan on 10/22/2016.
 */

public class Location implements Serializable{
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

    public boolean isInRange(Location loc, double distance){
        double R = 3959; // R = 3959 miles (or 3960 according to Kevin).
        double deg_to_rad_scalar = Math.PI / 180;
        double phi1 = this.latitude * deg_to_rad_scalar;
        double phi2 = loc.getLatitude() * deg_to_rad_scalar;
        double lambda1 = this.longitude * deg_to_rad_scalar;
        double lambda2 = loc.getLatitude() * deg_to_rad_scalar;
        double a = Math.pow(Math.sin((phi2 - phi1) / 2), 2) + (Math.cos(phi1) * Math.cos(phi2) *
                Math.pow(Math.sin((lambda2 - lambda1) / 2), 2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d <= distance;
    }

}
