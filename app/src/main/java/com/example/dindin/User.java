package com.example.dindin;

import com.facebook.Profile;

import java.util.Date;
import java.util.List;

/**
 * Created by Davin on 10/20/2016.
 */

public class User {
    String name;
    Date birthday;
    String gender;
    //location (Google maps)
    Profile facebookUser;
    int userRating;
    List<User> matches;
    //List<Recipe> starredRecipes;
    String preferredTask;

    public User(){}

    public User(Profile profile){
        this.facebookUser = profile;
        this.name = profile.getName();
    }
}
