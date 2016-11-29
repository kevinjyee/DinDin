package com.example.dindin.com.example;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Stefan on 10/22/2016.
 */

public class AgeRange implements Serializable{
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

    public boolean isInAgeRange(Date date){
        int age_in_years = findAge(date);
        return (age_in_years >= this.minAge) && (age_in_years <= this.maxAge);
    }

    public static int findAge(Date birthday){
        Date currentDate = findCurrentDate();
        int age = getDiffYears(birthday, currentDate);
        return age;
    }

    public static Date findCurrentDate(){
        //getting current date and time using Date class
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        return dateobj;
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = findCalendar(first);
        Calendar b = findCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar findCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
