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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;

import com.example.dindin.com.example.AgeRange;
import com.example.dindin.utilities.Constants;

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
    private RadioButton femaleButtton;
    private RadioButton bothMandFButton;
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
        currentPref = new Preferences();
        currentAgeRange = new AgeRange();
        //Init Buttons
        cookButton = (RadioButton) root.findViewById(R.id.cook);
        cleanButton = (RadioButton) root.findViewById(R.id.cleaner);
        maleButton = (RadioButton) root.findViewById(R.id.male);
        femaleButtton = (RadioButton) root.findViewById(R.id.female);
        bothMandFButton = (RadioButton) root.findViewById(R.id.both);
        rangebar = (RangeBar) root.findViewById(R.id.rangebar1);
        maxage = (TextView) root.findViewById(R.id.maxage);
        minagevalue = (TextView) root.findViewById(R.id.minagevalue);

        rangebar.setTickCount(38);
        rangebar.setTickHeight(0);

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
                    cookButton.setChecked(true);
                    cleanButton.setChecked(false);
                    break;
            case R.id.cleaner:
                if (checked)
                    currentPref.setPreferredTask("Clean");
                     Log.e("Clean Selected","Clean");
                cookButton.setChecked(false);
                cleanButton.setChecked(true);
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
                        femaleButtton.setChecked(false);
                         bothMandFButton.setChecked(false);
                break;
            case R.id.female:
                if (checked)
                    currentPref.setPreferredGender("female");
                maleButton.setChecked(false);
                femaleButtton.setChecked(true);
                bothMandFButton.setChecked(false);
                break;
            case R.id.both:
                if (checked)
                    currentPref.setPreferredGender("both");
                maleButton.setChecked(false);
                femaleButtton.setChecked(false);
                bothMandFButton.setChecked(true);
                break;
        }
        currentUser.setPreferences(currentPref);
    }

    }


