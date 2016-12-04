package com.example.dindin;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;

import com.example.dindin.com.example.AgeRange;
import com.example.dindin.utilities.Constants;
import com.facebook.login.widget.ProfilePictureView;

import static android.R.attr.checked;
import static com.example.dindin.R.id.radio;

/**
 * Created by Davin on 10/23/2016.
 */

public class PreferencesFragment extends Fragment {

    private User currentUser;
    private Preferences currentPref;
    private AgeRange currentAgeRange;
    private RadioButton cookButton;
    private RadioButton cleanButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private RadioButton bothMandFButton;
    private RadioButton myMaleButton, myFemaleButton;
    private int minAge, maxAge;
    private RangeBar rangebar;
    private TextView maxage, minagevalue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_preferences, null);



        //Instantiate User;
        currentUser = Constants.currentUser;
        if(Constants.currentUser.getPreferences() != null)
        {
         currentPref = Constants.currentUser.getPreferences();
        }
        else
        {
            currentPref = new Preferences();
            currentUser.setPreferences(currentPref);
        }
        if(Constants.currentUser.getPreferences().getAgeRange() != null)
        {
            currentAgeRange = Constants.currentUser.getPreferences().getAgeRange();
        }
        else
        {
            currentAgeRange = new AgeRange();
            currentUser.getPreferences().setAgeRange(currentAgeRange);
        }

        //Init Buttons
        cookButton = (RadioButton) root.findViewById(R.id.cook);
        cleanButton = (RadioButton) root.findViewById(R.id.cleaner);
        maleButton = (RadioButton) root.findViewById(R.id.male);
        femaleButton = (RadioButton) root.findViewById(R.id.female);
        bothMandFButton = (RadioButton) root.findViewById(R.id.both);
        rangebar = (RangeBar) root.findViewById(R.id.rangebar1);
        maxage = (TextView) root.findViewById(R.id.maxage);
        minagevalue = (TextView) root.findViewById(R.id.minagevalue);

        myMaleButton = (RadioButton) root.findViewById(R.id.iamamale);
        myFemaleButton = (RadioButton) root.findViewById(R.id.iamafemale);


        rangebar.setTickCount(38);
        rangebar.setTickHeight(0);

        initView();




        final RadioGroup radioGroupPref = (RadioGroup) root.findViewById(R.id.preference_role);
        radioGroupPref.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = radioGroupPref.findViewById(checkedId);
                boolean checked = ((RadioButton) view).isChecked();
                switch(view.getId()) {
                    case R.id.cook:
                        if (checked)
                            currentPref.setPreferredTask("cook");
                        Log.e("Cook Selected","Cook");

                        break;
                    case R.id.cleaner:
                        if (checked)
                            currentPref.setPreferredTask("clean");
                        Log.e("Clean Selected","Clean");

                        break;
                }
                currentUser.setPreferences(currentPref);

            }
        });

        final RadioGroup radioGroupGender = (RadioGroup) root.findViewById(R.id.preference_gender);
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = radioGroupGender.findViewById(checkedId);
                boolean checked = ((RadioButton) view).isChecked();
                switch(view.getId()) {
                    case R.id.male:
                        if (checked)
                            currentPref.setPreferredGender("male");
                        maleButton.setChecked(true);
                        femaleButton.setChecked(false);
                        bothMandFButton.setChecked(false);
                        break;
                    case R.id.female:
                        if (checked)
                            currentPref.setPreferredGender("female");
                        maleButton.setChecked(false);
                        femaleButton.setChecked(true);
                        bothMandFButton.setChecked(false);
                        break;
                    case R.id.both:
                        if (checked)
                            currentPref.setPreferredGender("both");
                        maleButton.setChecked(false);
                        femaleButton.setChecked(false);
                        bothMandFButton.setChecked(true);
                        break;
                }
                currentUser.setPreferences(currentPref);

            }
        });

        final RadioGroup radioGroupMyGender = (RadioGroup) root.findViewById(R.id.my_gender);
        radioGroupMyGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = radioGroupMyGender.findViewById(checkedId);
                boolean checked = ((RadioButton) view).isChecked();
                switch(view.getId()) {
                    case R.id.iamamale:
                        if (checked)
                            currentUser.setGender("male");


                        break;
                    case R.id.iamafemale:
                        if (checked)
                            currentUser.setGender("female");
                        break;
                }


            }
        });

        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar,
                                              int leftThumbIndex, int rightThumbIndex) {


                minagevalue.setText((leftThumbIndex + 18) + "");
                maxage.setText((rightThumbIndex + 18) + "");
                minAge = leftThumbIndex + 18;
                maxAge = rightThumbIndex + 18;
                currentAgeRange.setMinAge(minAge);
                currentAgeRange.setMaxAge(maxAge);
                currentPref.setAgeRange(currentAgeRange);
                currentUser.setPreferences(currentPref);
            }
        });

        return root;

    }


    public void onCookRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.cook:
                if (checked)
                    currentPref.setPreferredTask("Cook");
                    Log.e("Cook Selected","Cook");

                    break;
            case R.id.cleaner:
                if (checked)
                    currentPref.setPreferredTask("Clean");
                     Log.e("Clean Selected","Clean");

                    break;
        }
        currentUser.setPreferences(currentPref);
    }

    public void onGenderRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    currentPref.setPreferredGender("male");
                         maleButton.setChecked(true);
                        femaleButton.setChecked(false);
                         bothMandFButton.setChecked(false);
                break;
            case R.id.female:
                if (checked)
                    currentPref.setPreferredGender("female");
                maleButton.setChecked(false);
                femaleButton.setChecked(true);
                bothMandFButton.setChecked(false);
                break;
            case R.id.both:
                if (checked)
                    currentPref.setPreferredGender("both");
                maleButton.setChecked(false);
                femaleButton.setChecked(false);
                bothMandFButton.setChecked(true);
                break;
        }
        currentUser.setPreferences(currentPref);
    }

    public void initView()
    {
        if(currentUser.getGender() == null)
        {
            myMaleButton.setChecked(false);
            myFemaleButton.setChecked(false);
        }
        else if(currentUser.getGender() == "male")
        {
            myMaleButton.setChecked(true);
        }
        else if(currentUser.getGender() == "female")
        {
            myFemaleButton.setChecked(true);
        }


        if(currentUser.getPreferences() == null || currentUser.getPreferences().getPreferredGender() == null)
        {
            maleButton.setChecked(false);
            femaleButton.setChecked(false);

        }
        else if(currentUser.getPreferences().getPreferredGender() == "female")
        {
            femaleButton.setChecked(true);
        }
        else if(currentUser.getPreferences().getPreferredGender() == "male")
        {
            maleButton.setChecked(true);
        }
        else if(currentUser.getPreferences().getPreferredGender() == "both")
        {
            bothMandFButton.setChecked(true);
        }

        if(currentUser.getPreferences() == null || currentUser.getPreferences().getPreferredTask() == null)
        {
            cookButton.setChecked(false);
            cleanButton.setChecked(false);
        }
        else if(currentUser.getPreferences().getPreferredTask() == "cook")
        {
            cookButton.setChecked(true);
        }
        else if(currentUser.getPreferences().getPreferredTask() == "clean")
        {
            cleanButton.setChecked(true);
        }

        if(currentUser.getPreferences().getAgeRange() != null) {
            minAge = currentUser.getPreferences().getAgeRange().getMinAge();
            maxAge = currentUser.getPreferences().getAgeRange().getMaxAge();
            if(minAge >= 18 && maxAge <= 55) {
                rangebar.setThumbIndices(minAge - 18, maxAge - 18);
                minagevalue.setText("" + minAge);
                maxage.setText("" + maxAge);
            }

        }


    }


    }


