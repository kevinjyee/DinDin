package com.example.dindin;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dindin.utilities.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by Davin on 10/23/2016.
 */

public class ProfileEditFragment extends Fragment implements View.OnClickListener{
    private View view;
    private Button saveButton;
    private Button cancelButton;
    private RadioGroup gender;
    private RadioButton chosengender;
    private EditText short_bio;
    private EditText location;
    private EditText dish;
    private EditText cuisine;
    private ImageView imageview;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profileedit, null);
        view = root;
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = preferences.edit();
        short_bio = (EditText)view.findViewById(R.id.profile_short_bio);
        location = (EditText)view.findViewById(R.id.profile_location);
        dish = (EditText)view.findViewById(R.id.profile_dish);
        cuisine = (EditText) view.findViewById(R.id.profile_cuisine);
        gender = (RadioGroup)view.findViewById(R.id.user_profile_gender);

        preferences = PreferenceManager.getDefaultSharedPreferences(inflater.getContext());
        saveButton = (Button) view.findViewById(R.id.saveProfileButton);
        saveButton.setOnClickListener(this);
        cancelButton = (Button)view.findViewById(R.id.cancelProfileButton);
        cancelButton.setOnClickListener(this);
        imageview = (ImageView) view.findViewById(R.id.user_profile_photo);
        Picasso.with(getActivity())
                .load("https://graph.facebook.com/v2.2/" + preferences.getString(Constants.FACEBOOK_ID,"") + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //

                .into(imageview);
        setCurrentValues(
                preferences.getString(Constants.GENDER,""),
                preferences.getString(Constants.SHORT_BIO,""),
                preferences.getString(Constants.LOCATION,""),
                preferences.getString(Constants.DISH,""),
                preferences.getString(Constants.CUISINE,"")
        );

        return root;
    }
    public void setCurrentValues(String gen, String bio, String location, String dish, String cuisine){
        short_bio.setText(bio);
        this.location.setText(location);
        this.dish.setText(dish);
        this.cuisine.setText(cuisine);

        RadioButton gender_male = (RadioButton)view.findViewById(R.id.male);
        RadioButton gender_female = (RadioButton)view.findViewById(R.id.female);
        if(gen.equals(gender_male.getText().toString())){
            ((RadioButton)gender.getChildAt(0)).setChecked(true);
        }else if(gen.equals(gender_female.getText().toString())){
            ((RadioButton)gender.getChildAt(1)).setChecked(true);
        }else{}
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.saveProfileButton:
                String gender_text = "Gender: N/A";
                if(gender.getCheckedRadioButtonId() == -1){
                }else{
                    chosengender = (RadioButton)view.findViewById(gender.getCheckedRadioButtonId());
                    gender_text = chosengender.getText().toString();
                    editor.putString(Constants.GENDER, gender_text);
                }

                String short_bio_text = short_bio.getText().toString();
                String location_text = location.getText().toString();
                String dish_text = dish.getText().toString();
                String cuisine_text = cuisine.getText().toString();

                editor.putString(Constants.SHORT_BIO, short_bio_text);
                editor.putString(Constants.LOCATION, location_text);
                editor.putString(Constants.DISH, dish_text);
                editor.putString(Constants.CUISINE, cuisine_text);
                editor.commit();
/*
                Constants.currentUser.setProfNickname(name_text);
                Constants.currentUser.setProfBio(short_bio_text);
                Constants.currentUser.setProfAge(age_text);
                Constants.currentUser.setProfLocation(location_text);
                Constants.currentUser.setProfDish(dish_text);
                Constants.currentUser.setProfCuisine(cuisine_text);
                Constants.currentUser.setProfGender(gender_text);
                FirebaseTestActivity.updateUser();
*/
                break;
            case R.id.cancelProfileButton:
                break;
        }
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main_content_fragment, new ProfileFragment());
        ft.commit();
    }
}
