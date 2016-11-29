package com.example.dindin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dindin.com.example.NavBarActivity;
import com.example.dindin.utilities.Constants;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class FirebaseTestActivity extends AppCompatActivity {

    public HashMap<String, User> matches = new HashMap<String, User>();
    User currentUser;
    boolean currentUserIsInDatabase;
    boolean databaseReloaded;
    boolean databaseEmpty;
    Intent goToNextActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);
        // Get ListView object from xml
        final ListView listView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        // Load User passed down by LoginActivity
        currentUser = (User) intent.getSerializableExtra("currentUser");
        currentUserIsInDatabase = false;
        databaseReloaded = false;
        databaseEmpty = true;
        goToNextActivity = new Intent(getApplicationContext(), NavBarActivity.class);

        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        /*
        // Connect to the Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to the todoItems child items it the database
        final DatabaseReference myRef = database.getReference("usersHashSet");
        final DatabaseReference myRefIndiv = database.getReference("user");
        final DatabaseReference myRecipeRef = database.getReference("recipesHashSet");
        final DatabaseReference myRecipeRefIndiv = database.getReference("recipe");
        */

        Constants.myRecipeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object recipeHash = snapshot.getValue();
                if (recipeHash == null) {
                    instantiateRecipeDB();
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });

        Constants.myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object userHash = snapshot.getValue();
                if (userHash == null) {
                    instantiateDB();
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });

        // Assign a listener to detect changes to the child items
        // of the database reference.
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
                    for (String id : users.keySet()) {
                        if (!id.equals(currentUser.getfbId()) && !databaseReloaded) {
                            matches.put(id, users.get(id));
                            adapter.add(users.get(id).getName());
                        } else if(id.equals(currentUser.getfbId())){
                            currentUserIsInDatabase = true;
                            currentUser = users.get(id);
                        }
                    }
                    if (!currentUserIsInDatabase && !databaseReloaded) { // If user not found, add to database and update database.
                        users.put(currentUser.getfbId(), currentUser);
                       // dataSnapshot.getRef().removeValue();
                        try {
                            String jsonUsers = mapper.writeValueAsString(users);
                            System.out.println(jsonUsers);
                            // Create a new child with a auto-generated ID.
                            //DataSnapshot firstChild = dataSnapshot.iterator().next();
                            //firstChild.getRef().setValue(jsonUsers);
                            DatabaseReference childRef = Constants.myRef.push();

                            // Set the child's data to the value passed in from the text box.
                            childRef.setValue(jsonUsers);
                            childRef = Constants.myRefIndiv.push();
                            childRef.setValue(currentUser);
                            databaseReloaded = true;
                        } catch (JsonGenerationException e) {
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //goToNextActivity.putExtra("matchList", matches);
                Constants.feasibleMatches = matches;
                //   editor.putString(Constants.PROFILE_IMAGE_ONE,
                // getStoredImageUrl("1", data.getProfilePicture()));

                startActivity(goToNextActivity);
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

        Query myQuery = Constants.myRefIndiv.orderByChild("fbId").equalTo(currentUser.getfbId());

        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User userHash = snapshot.getValue(User.class);
                if (userHash != null) {
                    //updateUserMatches(snapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });

    }

    /*
    public void createDefaultUsers(DatabaseReference myRef, DatabaseReference myRefIndiv){
        User Kevin = User.createDefaultUser();
        Kevin.setfbId("100001411585746");
        User Tom = User.createDefaultUser();
        User Davin = User.createDefaultUser();
        Davin.setfbId("1151947893");
        User Stephen = User.createDefaultUser();
        Stephen.setfbId("705738627");
        HashSet<User> users = new HashSet<User>();
        users.add(Kevin);
        users.add(Tom);
        users.add(Davin);
        users.add(Stephen);

        for(User u : users){
            DatabaseReference childRef2 = myRefIndiv.push();
            childRef2.setValue(u);
        }
        ObjectMapper mapper = new ObjectMapper();

        try {
            String jsonUsers = mapper.writeValueAsString(users);
            System.out.println(jsonUsers);
            // Create a new child with a auto-generated ID.
            DatabaseReference childRef = myRef.push();

            // Set the child's data to the value passed in from the text box.
            childRef.setValue(jsonUsers);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */

    public void instantiateDB(){
        DatabaseReference childRef = Constants.myRefIndiv.push();
        childRef.setValue(currentUser);
            ObjectMapper mapper = new ObjectMapper();
            try {
                HashMap<String, User> users = new HashMap<String, User>();
                users.put(currentUser.getfbId(), currentUser);
                String jsonUsers = mapper.writeValueAsString(users);
                System.out.println(jsonUsers);
                // Create a new child with a auto-generated ID.
                DatabaseReference childRefDB = Constants.myRef.push();

                // Set the child's data to the value passed in from the text box.
                childRefDB.setValue(jsonUsers);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    // Fill DB with recipes
    public void instantiateRecipeDB(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Recipe> recipes = Recipe.generateRecipes();
            for(Recipe r : recipes){
                DatabaseReference childRef = Constants.myRecipeRefIndiv.push();
                childRef.setValue(r);
            }
            String jsonRecipes = mapper.writeValueAsString(recipes);
            System.out.println(jsonRecipes);
            // Create a new child with a auto-generated ID.
            DatabaseReference childRefDB = Constants.myRecipeRef.push();

            // Set the child's data to the value passed in from the text box.
            childRefDB.setValue(jsonRecipes);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUserMatches(DataSnapshot dataSnapshot){

        if (dataSnapshot.hasChildren()) {
            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
            HashMap<String, String> potMatches = new HashMap<String, String>();
            potMatches.put("2", "2");
            potMatches.put("4", "4");
            potMatches.put("6", "6");
            potMatches.put("8", "8");
            currentUser.setPotentialMatches(potMatches);
            firstChild.getRef().setValue(currentUser);
        }

    }

    public static void updateUser(){
        Query myQuery = Constants.myRefIndiv.orderByChild("fbId").equalTo(Constants.currentUser.getfbId());

        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User userHash = snapshot.getValue(User.class);
                if (userHash != null) {
                    updateUserInfo(snapshot);
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });
    }

    public static void updateUserInfo(DataSnapshot snapshot){
        if (snapshot.hasChildren()) {
            DataSnapshot firstChild = snapshot.getChildren().iterator().next();
            firstChild.getRef().setValue(Constants.currentUser);
        }
    }
}
