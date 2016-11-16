package com.example.dindin;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Davin on 10/23/2016.
 */

public class ProfileEditFragment extends Fragment implements View.OnClickListener{
    View view;
    Button saveButton;
    Button cancelButton;
    ProfileFragment profileFragment;

    public void setProfileFrag(ProfileFragment instance){
        this.profileFragment = instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profileedit, null);
        view = root;
        saveButton = (Button) view.findViewById(R.id.saveProfileButton);
        saveButton.setOnClickListener(this);
        cancelButton = (Button)view.findViewById(R.id.cancelProfileButton);
        return root;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.saveProfileButton:
                EditText name = (EditText)view.findViewById(R.id.user_profile_name);
                String name_text = name.getText().toString();
                /*
                RadioGroup gender = (RadioGroup)view.findViewById(R.id.user_profile_gender);
                RadioButton chosengender;
                String gender_text;
                if(gender.getCheckedRadioButtonId() == -1){
                    gender_text = chosengender
                }
                */
                EditText short_bio = (EditText)view.findViewById(R.id.profile_short_bio);
                String short_bio_text = short_bio.getText().toString();
                EditText age = (EditText)view.findViewById(R.id.profile_age);
                String age_text = age.getText().toString();
                EditText location = (EditText)view.findViewById(R.id.profile_location);
                String location_text = location.getText().toString();
                EditText dish = (EditText)view.findViewById(R.id.profile_dish);
                String dish_text = dish.getText().toString();
                EditText cuisine = (EditText)view.findViewById(R.id.profile_cuisine);
                String cuisine_text = cuisine.getText().toString();

                profileFragment.setProfileInfo(name_text,"Male", age_text, short_bio_text, location_text, dish_text, cuisine_text);
                break;
            case R.id.cancelProfileButton:
                break;
        }
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, profileFragment);
        ft.commit();
    }
}
