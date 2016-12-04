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

import com.example.dindin.com.example.Location;
import com.example.dindin.utilities.Constants;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Stefan on 11/26/2016.
 */

public class FirebaseHelper implements Runnable{

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef;
    private static DatabaseReference myRefIndiv;
    private static DatabaseReference myRecipeRef;
    private static DatabaseReference myRecipeRefIndiv;
    private static DataSnapshot userSnapshot;
    boolean userIsInDb;
    private HashSet<User> matches;
    private User currentUser;
    private boolean listProcessed;
    private CountDownLatch latch;


    public FirebaseHelper(String id){
        if(!id.equalsIgnoreCase("test")){
            myRef = database.getReference("usersHashSet");
            myRefIndiv = database.getReference("user");
            myRecipeRef = database.getReference("recipesHashSet");
            myRecipeRefIndiv = database.getReference("recipe");
        } else{
            myRef = database.getReference("usersHashSetTest");
            myRefIndiv = database.getReference("userTest");
            myRecipeRef = database.getReference("recipesHashSetTest");
            myRecipeRefIndiv = database.getReference("recipeTest");
        }
        matches = new HashSet<User>();
    }

    public void addUser(User user){
        //this.currentUser = user;
        this.currentUser = user;
        Query myQuery = myRefIndiv.orderByChild("fbId").equalTo(currentUser.getfbId());
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Object user = snapshot.getValue();
                if(user == null){
                    DatabaseReference childRef = myRefIndiv.push();
                    childRef.setValue(currentUser);
                } else{
                    DataSnapshot data = (DataSnapshot) snapshot.getChildren().iterator().next();
                    User u = data.getValue(User.class);
                    Constants.currentUser = u;
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });
    }

    public void removeUser(User user){
        currentUser = user;
        Query myQuery = myRefIndiv.orderByChild("fbId").equalTo(currentUser.getfbId());
        myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });
    }

    public HashMap<String, User> getUsers(){
        listProcessed = false;
        latch = new CountDownLatch(1);
        Thread t = new Thread();
        t.run();
        HashMap<String, User> userList = new HashMap<>();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hehe");
        return userList;
    }

    public void findMatches(){
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
                    User.filterMatches(users);
                }
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });
        Constants.context.startActivity(Constants.goToMatching);
    }

    public void instantiateDB(){
            DatabaseReference childRef = myRefIndiv.push();
            childRef.setValue(currentUser);
            ObjectMapper mapper = new ObjectMapper();
            try {
                HashMap<String, User> users = new HashMap<String, User>();
                users.put(currentUser.getfbId(), currentUser);
                String jsonUsers = mapper.writeValueAsString(users);
                System.out.println(jsonUsers);
                // Create a new child with a auto-generated ID.
                DatabaseReference childRefDB = myRef.push();

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

    public void run(){
        myRefIndiv.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Iterator it = snapshot.getChildren().iterator();
                while(it.hasNext()){
                    DataSnapshot firstChild = snapshot.getChildren().iterator().next();
                    System.out.println("Yesh");
                }
                latch.countDown();
                listProcessed = true;
            }
            @Override
            public void onCancelled(DatabaseError DatabaseError) {
            }
        });
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
