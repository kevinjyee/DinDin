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
import java.util.Iterator;
import java.util.Map;

public class FirebaseTestActivity extends AppCompatActivity {

    public HashMap<String, User> matches = new HashMap<String, User>();
    private HashMap<String, User> uList = new HashMap<>();
    User currentUser;
    boolean currentUserIsInDatabase;
    boolean databaseReloaded;
    boolean databaseEmpty;
    Intent goToNextActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_test);
        final ListView listView = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();

        currentUser = (User) intent.getSerializableExtra("currentUser");
        Constants.fbHelp.addUser(currentUser);
        currentUserIsInDatabase = false;
        databaseReloaded = false;
        databaseEmpty = true;
        goToNextActivity = new Intent(getApplicationContext(), NavBarActivity.class);
        Constants.goToMatching = new Intent(getApplicationContext(), NavBarActivity.class);
        Constants.goToMatching.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Constants.context = getApplicationContext();
        Constants.fbHelp.findMatches();
        Constants.fbHelp.clearMatchHistory();

        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

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
        finish();
    }

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
        //User.generateDummyUsers();
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

    public void updateUserHash(){
        Constants.myRefIndiv.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User userHash = snapshot.getValue(User.class);
                HashMap<String, User> users = new HashMap<>();
                if (userHash != null) {
                    Iterator it = snapshot.getChildren().iterator();
                    while(it.hasNext()){
                        DataSnapshot data = (DataSnapshot) it.next();
                        User u = data.getValue(User.class);
                        users.put(u.getfbId(), u);
                    }
                    uList = users;
                    changeUserData();
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });
    }

    public void changeUserData(){
        Constants.myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object userHash = snapshot.getValue();
                if (userHash != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        String jsonUsers = mapper.writeValueAsString(uList);
                        System.out.println(jsonUsers);
                        // Create a new child with a auto-generated ID.
                        if (snapshot.hasChildren()) {
                            DataSnapshot firstChild = snapshot.getChildren().iterator().next();
                            firstChild.getRef().setValue(jsonUsers);
                        }
                    } catch (JsonGenerationException e) {
                        e.printStackTrace();
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    public String parseSnapshot(String json){
        int equalsIndex = json.indexOf("=");
        String parsed = json.substring(equalsIndex + 1, json.length() - 1);
        return parsed;
    }
}
