package com.example.dindin;

/**
 * Created by Davin on 10/23/2016.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dindin.ProfileEditFragment;
import com.example.dindin.utilities.Constants;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    View view;
    Button editButton;
    TextView nameage, bio, loc, favdish, favcuisine, gender;
    ImageView imageview;
    private SharedPreferences preferences;
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
        imageview = (ImageView) view.findViewById(R.id.user_profile_photo);
        preferences = PreferenceManager.getDefaultSharedPreferences(inflater.getContext());
        String fbId = preferences.getString(Constants.FACEBOOK_ID, "");
        String firstname = preferences.getString(Constants.FIRST_NAME,"");
        Picasso.with(getActivity())
                .load("https://graph.facebook.com/v2.2/" + fbId + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //
                .into(imageview);

        nameage.setText(firstname+ ", " + "21");
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
        nameage.setText(Constants.FIRST_NAME + ", " + age);
        //TextView bio = (TextView)view.findViewById(R.id.profile_short_bio);
        bio.setText(short_bio);
        //TextView loc = (TextView)view.findViewById(R.id.profile_location);
        loc.setText(location);
        //TextView favdish = (TextView)view.findViewById(R.id.profile_dish);
        favdish.setText(dish);
         //TextView favcuisine = (TextView)view.findViewById(R.id.profile_cuisine);


        Picasso.with(getActivity())
                .load("https://graph.facebook.com/v2.2/" + Constants.FACEBOOK_ID + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //

                .into(imageview);
        favcuisine.setText(cuisine);
        gender.setText(gen);
    }
}