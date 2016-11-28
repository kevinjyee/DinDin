package com.example.dindin.utilities;

import com.example.dindin.Recipe;
import com.example.dindin.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static final String DISPLAY_NAME = "display name";
    public static final String SHORT_BIO = "short bio";
    public static final String AGE = "age";
    public static final String LOCATION = "location";
    public static final String DISH = "favorite dish";
    public static final String CUISINE = "favorite cuisine";
    public static final String GENDER = "gender";
    public static final String UNKNOWN = "N/A";



    public static final String methodeName = "POST";
    public static boolean isMatchedFound = false;

    public static User currentUser = new User();
    public static ArrayList<User> dummyUsers = new ArrayList<>();
    public static ArrayList<User> usersMatchedwith = new ArrayList<>();
    public static ArrayList<Recipe> recipsesMatchedwith = new ArrayList<>();
    public static ArrayList<String> fbIDMatchedwith = new ArrayList<>();
    public static ArrayList<String> recipeIDMatchedwith = new ArrayList<>();
    public static HashMap<String, User> feasibleMatches = new HashMap<>();

    public static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final DatabaseReference myRef = database.getReference("usersHashSet");
    public static final DatabaseReference myRefIndiv = database.getReference("user");
    public static final DatabaseReference myRecipeRef = database.getReference("recipesHashSet");
    public static final DatabaseReference myRecipeRefIndiv = database.getReference("recipe");
}
