package com.example.dindin;

/**
 * Created by Davin on 10/23/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dindin.ProfileEditFragment;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    View view;
    Button editButton;
    TextView nameage, bio, loc, favdish, favcuisine, gender;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profileview, null);
        view = root;
        editButton = (Button) view.findViewById(R.id.editProfileButton);
        editButton.setOnClickListener(this);
        nameage = (TextView)view.findViewById(R.id.user_profile_name);
        bio = (TextView)view.findViewById(R.id.profile_short_bio);
        loc = (TextView)view.findViewById(R.id.user_profile_location);
        favdish = (TextView)view.findViewById(R.id.profile_dish);
        favcuisine = (TextView)view.findViewById(R.id.profile_cuisine);
        gender = (TextView)view.findViewById(R.id.user_profile_gender);
        return root;
    }
    @Override
    public void onClick(View v) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ProfileEditFragment editFragment = new ProfileEditFragment();
        editFragment.setProfileFrag(this);
       //TODO: Replace content frame
        ft.replace(R.id.content_frame, editFragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void setProfileInfo(String name, String gen, String age, String short_bio, String location, String dish, String cuisine){
       //View view = findViewById(R.layout.fragment_profileview);
        //TextView nameage = (TextView)view.findViewById(R.id.user_profile_name);
        nameage.setText(name + ", " + age);
        //TextView bio = (TextView)view.findViewById(R.id.profile_short_bio);
        bio.setText(short_bio);
        //TextView loc = (TextView)view.findViewById(R.id.profile_location);
        loc.setText(location);
        //TextView favdish = (TextView)view.findViewById(R.id.profile_dish);
        favdish.setText(dish);
         //TextView favcuisine = (TextView)view.findViewById(R.id.profile_cuisine);
        favcuisine.setText(cuisine);
        gender.setText(gen);
    }
}