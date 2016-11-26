package com.example.dindin.utilities;

import com.example.dindin.Recipe;
import com.example.dindin.User;

import java.util.ArrayList;

/**
 * Created by Kevin on 11/20/2016.
 */

/*Store Global variables and such here*/
public class Constants {



    public static final String PROFILE_IMAGE_ONE = "imageOne";
    public static final String FACEBOOK_ID = "fbid";
    public static final String FIRST_NAME ="firstname";
    public static final String LAST_NAME = "lastname";
    public static final String KEY_FACEBOOK_ID = "current_user_fbid";

    public static final String methodeName = "POST";
    public static boolean isMatchedFound = false;

    public static ArrayList<User> usersMatchedwith = new ArrayList<>();
    public static ArrayList<Recipe> recipsesMatchedwith = new ArrayList<>();
    public static ArrayList<String> fbIDMatchedwith = new ArrayList<>();
    public static ArrayList<String> recipeIDMatchedwith = new ArrayList<>();
}
