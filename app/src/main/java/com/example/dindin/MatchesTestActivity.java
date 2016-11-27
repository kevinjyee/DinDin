package com.example.dindin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MatchesTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_test);
        Intent intent = getIntent();
        HashMap<String, User> matches = (HashMap<String, User>) intent.getSerializableExtra("matchList");
        System.out.println("MOM'S SPAGHETTI");
    }
}
