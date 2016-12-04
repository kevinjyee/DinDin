package com.example.dindin;


import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.dindin.com.example.AgeRange;
import com.example.dindin.com.example.Location;
import com.example.dindin.utilities.Constants;
import com.facebook.Profile;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import static com.google.android.gms.wearable.DataMap.TAG;

public class User implements Serializable{

    private int id;
    private Date birthday;
    private String name;
    private String gender;
    private com.example.dindin.com.example.Location location;
    private Profile facebookProfile;
    private double userRating;
    private HashMap<String, String> potentialMatches;
    private HashMap<String, String> swipedLeft;
    private HashMap<String, String> swipedRight;
    private HashMap<String, String> finalizedMatches;
    private Preferences preferences;
    private dindinProfile dindinProfile;
    @SerializedName("fbId")

    private String fbId;
    private String age;
    private String phoneNumber = "713-478-3035";

    public User()
    {
    }

    public User(String userId, String name, String birthday){
        this.fbId = userId;
        this.name = name;
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       //this.birthday = date;
        this.age = Integer.toString(AgeRange.findAge(date));
    }

    public User(int userId, String fbID, String userName, Location userLoc, double rating,
                HashMap<String, String> potMatches, HashMap<String, String> finMatches, Preferences prefs)
    {
        this.id = userId;
        this.fbId = fbID;
        this.name = userName;
        this.location = userLoc;
        this.userRating = rating;
        this.potentialMatches = potMatches;
        this.finalizedMatches = finMatches;
        this.preferences = prefs;
    }

    public static User createDefaultUser(){
        int uID = 1;
        String fbID = "1";
        String uName = "Tom";
        Location uLoc = new Location(1, 2);
        double uRating = 3.9;
        AgeRange range = new AgeRange(18, 22);
        ArrayList<String> prefCuisine = new ArrayList<String>();
        prefCuisine.add("Asian");
        prefCuisine.add("Indian");
        prefCuisine.add("American");
        Preferences prefs = new Preferences("Cook", 15, range, prefCuisine);
        HashMap<String, String> potMatches = new HashMap<String, String>();
        potMatches.put("4", "4");
        potMatches.put("2", "2");
        potMatches.put("3", "3");
        HashMap<String, String> finMatches = new HashMap<String, String>();
        finMatches.put("5", "5");
        finMatches.put("6", "6");
        finMatches.put("7", "7");
        return new User(uID, fbID, uName, uLoc, uRating, potMatches, finMatches, prefs);
    }

    public int getId() {
        return id;
    }

    public String getfbId() {
        return fbId;
    }

    public Date getBirthday(){ return birthday; }

    public String getName() {
        return name;
    }

    public String getAge() {return age;}

    public Location getLocation() {
        return location;
    }

    public String getGender(){return gender;}


    public Profile getFacebookProfile() {
        return facebookProfile;
    }

    public HashMap<String, String> getPotentialMatches() {
        return potentialMatches;
    }

    public HashMap<String, String> getSwipedLeft() {
        return swipedLeft;
    }

    public HashMap<String, String> getSwipedRight() {
        return swipedRight;
    }

    public double getUserRating() {
        return userRating;
    }

    public HashMap<String, String> getFinalizedMatches() {
        return finalizedMatches;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public dindinProfile getDindinProfile(){
        return dindinProfile;
    }

    public void setfbId(String id) {
        this.fbId = id;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setBirthday(Date date){ this.birthday = date; }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(com.example.dindin.com.example.Location location) {
        this.location = location;
    }

    public void setGender(String gender) {this.gender = gender;}

    public void setPhoneNumber(String number){this.phoneNumber = number;};


    public String getPhoneNumber(){return this.phoneNumber; }

    public void setFacebookProfile(Profile facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public void setPotentialMatches(HashMap<String, String> potentialMatches) {
        this.potentialMatches = potentialMatches;
    }

    public void setSwipedLeft(HashMap<String, String> swipedLeft) {
        this.swipedLeft = swipedLeft;
    }

    public void setSwipedRight(HashMap<String, String> swipedRight) {
        this.swipedRight = swipedRight;
    }

    public void setFinalizedMatches(HashMap<String, String> finalizedMatches) {
        this.finalizedMatches = finalizedMatches;
    }

    public static User createUserFromProfile(Profile fbProfile){
        User newUser = new User();
        newUser.setfbId(fbProfile.getId());
        newUser.setName(fbProfile.getName());
        newUser.setAge("42");

        return newUser;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public void setDindinProfile(dindinProfile profile){
        this.dindinProfile = profile;
    }

    public boolean matchesPreferences(User user2){
        boolean preferencesMatch = false;
        try{
            preferencesMatch = this.getPreferences().areComplementaryRoles(user2.getPreferences());
            if(!preferencesMatch){
                return false;
            }
        } catch(NullPointerException e){
        }
        try{
            preferencesMatch = this.isInAgeRange(user2);
            if(!preferencesMatch){
                return false;
            }
        } catch(NullPointerException e){
        }
        try{
            preferencesMatch = this.getPreferences().shareCuisineInterests(user2.getPreferences());
            if(!preferencesMatch){
                return false;
            }
        } catch(NullPointerException e){
        }
        try{
            preferencesMatch = this.getPreferences().getPreferredGender().equalsIgnoreCase(user2.getGender()) ||
                this.getPreferences().getPreferredGender().equalsIgnoreCase("both");
            if(!preferencesMatch){
                return false;
            }
        } catch(NullPointerException e){
        }
        return true;

    }

    public boolean isInAgeRange(User user2){
        try {
            return this.getPreferences().getAgeRange().isInAgeRange(Integer.parseInt(user2.getAge()));
        } catch(NullPointerException e){
            return true;
        }
    }

    public boolean isInPreferredRange(User user2){
        try{
            return this.getLocation().isInRange(user2.getLocation(), this.getPreferences().getMaxMatchDistance());
        } catch(NullPointerException e){
            return true;
        }
    }

    public boolean isPotentialMatchFor(User user){
        try {
            return this.matchesPreferences(user) && this.isInAgeRange(user) && isInPreferredRange(user);
        } catch(NullPointerException e){
            return true;
        }
    }

    public static void generateDummyUsers() {
        User Kevin = new User("100001411585746","Kevin","05/24/1995");
        User Stefan = new User("1408027584","Stefan","09/23/1994");
        User Davin = new User("1151947893","Davin","07/17/1995");
        User Stephen = new User("705738627","Stephen","02/06/1995");
        User Yuriy = new User("100000050449525","Yuriy","02/01/1996");
        User Brandon = new User("100000384470712","Brandon","06/06/1996");
        User Rachel = new User("1512173415","Rachel","12/03/1996");
        ArrayList<User> newUsers = new ArrayList<>();
        newUsers.add(Yuriy);
        newUsers.add(Brandon);
        newUsers.add(Rachel);
        HashMap<String, String> dummySwipedRight = new HashMap<>();
        String StefanFbId = "10210794537485351";
        String KevinFbId = "1206579009399140";
        String DavinFbId = "1151947893";
        String StephenFbId = "705738627";
        dummySwipedRight.put(StefanFbId, StefanFbId);
        dummySwipedRight.put(KevinFbId, KevinFbId);
        dummySwipedRight.put(DavinFbId, DavinFbId);
        dummySwipedRight.put(StephenFbId, StephenFbId);
        Yuriy.setSwipedRight(dummySwipedRight);
        Brandon.setSwipedRight(dummySwipedRight);
        Rachel.setSwipedRight(dummySwipedRight);
        Constants.dummyUsers = newUsers;

        Query myQuery = Constants.myRefIndiv.orderByChild("fbId");

        Constants.myRefIndiv.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User userHash = snapshot.getValue(User.class);
                if (userHash != null) {
                    for(User u : Constants.dummyUsers) {
                        DatabaseReference childRef = Constants.myRefIndiv.push();
                        childRef.setValue(u);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });

        Constants.myRef.addChildEventListener(new ChildEventListener() {

            // This function is called once for each child that exists
            // when the listener is added. Then it is called
            // each time a new child is added.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                String userHash = dataSnapshot.getValue(String.class);
                ObjectMapper mapper = new ObjectMapper();
                TypeFactory typeFactory = mapper.getTypeFactory();
                HashMap<String, User> users = null;
                try {
                    // Read out json hash set.
                    users = mapper.readValue(userHash,
                            new TypeReference<HashMap<String, User>>(){});
                    for (User u : Constants.dummyUsers) {
                        users.put(u.getfbId(), u);
                    }
                        try {
                            String jsonUsers = mapper.writeValueAsString(users);
                            System.out.println(jsonUsers);
                            // Create a new child with a auto-generated ID.
                            // Set the child's data to the value passed in from the text box.
                            dataSnapshot.getRef().setValue(jsonUsers);
                        } catch (JsonGenerationException e) {
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // This function is called each time a child item is removed.
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String userJSON = dataSnapshot.getValue(String.class);
                ObjectMapper mapper = new ObjectMapper();
            }

            // The following functions are also required in ChildEventListener implementations.
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG:", "Failed to read value.", error.toException());
            }
        });

    }

    public static User makeMeAUserDamnit(){
        Location loc1 = new Location(30.2672, -97.7431);
        ArrayList<String> preferredCuisine1 = new ArrayList<String>();
        preferredCuisine1.add("Mexican");
        preferredCuisine1.add("Chinese");
        preferredCuisine1.add("Indian");
        preferredCuisine1.add("American");
        preferredCuisine1.add("Trash");
        AgeRange range1 = new AgeRange(18, 24);
        Preferences pref1 = new Preferences("Cook", 20, range1, preferredCuisine1);
        HashMap<String, String> empty = new HashMap<>();
        User testUser = new User(1, "123", "Test User1", loc1, 5.0, empty, empty, pref1);
        testUser.setBirthday(new Date("09/23/1994"));
        return testUser;
    }

    public static void filterMatches(HashMap<String, User> users){
        HashMap<String, User> matches = new HashMap<>();
        User currentUser = Constants.currentUser;
        HashMap<String, String> swipedRight = currentUser.getSwipedRight();
        if(swipedRight == null){
            swipedRight = new HashMap<String, String>();
        }
        HashMap<String, String> swipedLeft = currentUser.getSwipedLeft();
        if(swipedLeft == null){
            swipedLeft = new HashMap<String, String>();
        }
        HashMap<String, String> potentialMatches = currentUser.getPotentialMatches();
        if(potentialMatches == null){
            potentialMatches = new HashMap<String, String>();
        }
        for(String id : users.keySet()){
            User u = users.get(id);
            if(currentUser.getfbId() != u.getfbId() && currentUser.isPotentialMatchFor(u) &&
                    !swipedLeft.containsKey(id) && !swipedRight.containsKey(id) &&
                    !potentialMatches.containsKey(id)){
                matches.put(id, u);
            }
        }
        Constants.feasibleMatches = matches;
    }

}

