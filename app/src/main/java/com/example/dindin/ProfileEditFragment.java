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
    private EditText name;
    private RadioGroup gender;
    private RadioButton chosengender;
    private EditText short_bio;
    private EditText age;
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
        name = (EditText)view.findViewById(R.id.user_profile_name);
        short_bio = (EditText)view.findViewById(R.id.profile_short_bio);
        location = (EditText)view.findViewById(R.id.profile_location);
        dish = (EditText)view.findViewById(R.id.profile_dish);
        cuisine = (EditText) view.findViewById(R.id.profile_cuisine);
        gender = (RadioGroup)view.findViewById(R.id.user_profile_gender);
        age = (EditText) view.findViewById(R.id.profile_age);

        imageview = (ImageView) view.findViewById(R.id.user_profile_photo);
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
        setCurrentValues(preferences.getString(Constants.DISPLAY_NAME,""),
                preferences.getString(Constants.GENDER,""),
                preferences.getString(Constants.AGE,""),
                preferences.getString(Constants.SHORT_BIO,""),
                preferences.getString(Constants.LOCATION,""),
                preferences.getString(Constants.DISH,""),
                preferences.getString(Constants.CUISINE,"")
        );

        return root;
    }
    public void setCurrentValues(String name, String gen, String age, String bio, String location, String dish, String cuisine){
        this.name.setText(name);
        this.age.setText(age);
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
                String name_text = name.getText().toString();
                String gender_text;
                if(gender.getCheckedRadioButtonId() == -1){
                }else{
                    chosengender = (RadioButton)view.findViewById(gender.getCheckedRadioButtonId());
                    gender_text = chosengender.getText().toString();
                    editor.putString(Constants.GENDER, gender_text);
                }

                String short_bio_text = short_bio.getText().toString();
                String age_text = age.getText().toString();
                String location_text = location.getText().toString();
                String dish_text = dish.getText().toString();
                String cuisine_text = cuisine.getText().toString();

                editor.putString(Constants.DISPLAY_NAME, name_text);
                editor.putString(Constants.SHORT_BIO, short_bio_text);
                editor.putString(Constants.AGE, age_text);
                editor.putString(Constants.LOCATION, location_text);
                editor.putString(Constants.DISH, dish_text);
                editor.putString(Constants.CUISINE, cuisine_text);
                editor.commit();
                break;
            case R.id.cancelProfileButton:
                break;
        }
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main_content_fragment, new ProfileFragment());
        ft.commit();
    }
}
