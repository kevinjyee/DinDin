package com.example.dindin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Stefan on 10/22/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER = "user";
    SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert user into db.
    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ObjectMapper mapper = new ObjectMapper();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, user.getId());
        try {
            String userJSON = mapper.writeValueAsString(user);
            contentValues.put(COLUMN_USER, userJSON);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long result = db.insert(TABLE_NAME, null, contentValues);
        return (result != -1);
    }

    // Retrieve user object from db using id as key.
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        ObjectMapper mapper = new ObjectMapper();
        Cursor cursor = db.query(TABLE_NAME, new String[] {COLUMN_ID, COLUMN_USER}, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String userJSON = cursor.getString(1);
            try {
                User user = mapper.readValue(userJSON, User.class);
                return user;
            }catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else{
            return null;
        }
    }

    // Update user
    public boolean updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ObjectMapper mapper = new ObjectMapper();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, user.getId());
        try {
            String userJSON = mapper.writeValueAsString(user);
            contentValues.put(COLUMN_USER, userJSON);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long result = db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[] {
                String.valueOf(user.getId())});
        return (result != -1);
    }

    // Delete a user
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(user.getId())});
        db.close();
    }

    // Getting shops Count
    public int getUserCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Getting all users
    public ArrayList<User> getAllUsers() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = null;
                String userJSON = cursor.getString(1);
                try {
                    user = mapper.readValue(userJSON, User.class);
                }catch (JsonGenerationException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }
}
