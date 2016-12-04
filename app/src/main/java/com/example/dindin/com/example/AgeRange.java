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
        this.minAge = 0;
        this.maxAge = 0;
    }

    public AgeRange(int minimumAge, int maximumAge){
        this.setMinAge(minimumAge);
        this.setMaxAge(maximumAge);
    }

    public int getMinAge(){
        return this.minAge;
    }

    public int getMaxAge(){
        return this.maxAge;
    }

    public void setMinAge(int minimumAge){
        this.minAge = minimumAge > 0 ? minimumAge : 0;
    }

    public void setMaxAge(int maximumAge){
        this.maxAge = maximumAge >= this.minAge ? maximumAge : this.minAge;
    }

    public boolean isInAgeRange(int age){
        if(age == 0){
            return true;
        }
        return (age >= this.minAge) && (age <= this.maxAge);
    }

    public static int findAge(Date birthday){
        if(birthday == null){
            return -1;
        }
        Date currentDate = findCurrentDate();
        int age = getDiffYears(birthday, currentDate);
        return age;
    }

    public static Date findCurrentDate(){
        //getting current date and time using Date class
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateobj = new Date();
        return dateobj;
    }

    public static int getDiffYears(Date first, Date last) {
        if(first == null || last == null){
            return -1;
        }
        Calendar a = findCalendar(first);
        Calendar b = findCalendar(last);
        Calendar temp;
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if(diff < 0){
            temp = a;
            a = b;
            b = temp;
        }
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar findCalendar(Date date) {
        if(date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
