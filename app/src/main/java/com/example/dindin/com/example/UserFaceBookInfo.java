package com.example.dindin.com.example;

/**
 * Created by Kevin on 11/23/2016.
 */


/*This class will service the serialized database and to retrieve JSON Strings*/
import com.google.gson.annotations.SerializedName;

public class UserFaceBookInfo {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @SerializedName("id")
    private long faceBookId;
    @SerializedName("gender")
    private String gender;
    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private Location location;


    @SerializedName("age_range")
    private AgeRange ageRange;

    public AgeRange getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(AgeRange ageRange) {
        this.ageRange = ageRange;
    }



    public long getFaceBookId() {
        return faceBookId;
    }

    public void setFaceBookId(long faceBookId) {
        this.faceBookId = faceBookId;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }



}
