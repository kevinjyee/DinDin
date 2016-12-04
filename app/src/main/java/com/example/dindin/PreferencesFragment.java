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
import android.widget.Button;
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

public class PreferencesFragment extends Fragment implements OnClickListener {

    private User currentUser;
    private Preferences currentPref;
    private AgeRange currentAgeRange;
    private RadioButton cookButton;
    private RadioButton cleanButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private RadioButton bothMandFButton;
    private RadioButton myMaleButton, myFemaleButton;
    private Button clearMatchHistoryButton;
    private int minAge, maxAge;
    private RangeBar rangebar;
    private SeekBar distancebar;
    private TextView maxage, minagevalue, searchradius;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_preferences, null);


        //Instantiate User;
        currentUser = Constants.currentUser;
        if (Constants.currentUser.getPreferences() != null) {
            currentPref = Constants.currentUser.getPreferences();
        } else {
            currentPref = new Preferences();
            currentUser.setPreferences(currentPref);
        }
        if (Constants.currentUser.getPreferences().getAgeRange() != null) {
            currentAgeRange = Constants.currentUser.getPreferences().getAgeRange();
        } else {
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
        distancebar = (SeekBar) root.findViewById(R.id.seeklitmitosearch);
        maxage = (TextView) root.findViewById(R.id.maxage);
        minagevalue = (TextView) root.findViewById(R.id.minagevalue);
        searchradius = (TextView) root.findViewById(R.id.limitotsearchvalue);
        clearMatchHistoryButton = (Button) root.findViewById(R.id.clearHistoryButton);

        myMaleButton = (RadioButton) root.findViewById(R.id.iamamale);
        myFemaleButton = (RadioButton) root.findViewById(R.id.iamafemale);


        clearMatchHistoryButton.setOnClickListener(this);
        rangebar.setTickCount(38);
        rangebar.setTickHeight(0);
        distancebar.setMax(55);
        initView();


        final RadioGroup radioGroupPref = (RadioGroup) root.findViewById(R.id.preference_role);
        radioGroupPref.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = radioGroupPref.findViewById(checkedId);
                boolean checked = ((RadioButton) view).isChecked();
                switch (view.getId()) {
                    case R.id.cook:
                        if (checked)
                            currentPref.setPreferredTask("cook");
                        Log.e("Cook Selected", "Cook");

                        break;
                    case R.id.cleaner:
                        if (checked)
                            currentPref.setPreferredTask("clean");
                        Log.e("Clean Selected", "Clean");

                        break;
                }
                currentUser.setPreferences(currentPref);
                Constants.fbHelp.updateUser();

            }
        });

        final RadioGroup radioGroupGender = (RadioGroup) root.findViewById(R.id.preference_gender);
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = radioGroupGender.findViewById(checkedId);
                boolean checked = ((RadioButton) view).isChecked();
                switch (view.getId()) {
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
                Constants.fbHelp.updateUser();

            }
        });

        final RadioGroup radioGroupMyGender = (RadioGroup) root.findViewById(R.id.my_gender);
        radioGroupMyGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View view = radioGroupMyGender.findViewById(checkedId);
                boolean checked = ((RadioButton) view).isChecked();
                switch (view.getId()) {
                    case R.id.iamamale:
                        if (checked)
                            currentUser.setGender("male");
                        Constants.fbHelp.updateUser();


                        break;
                    case R.id.iamafemale:
                        if (checked)
                            currentUser.setGender("female");
                        Constants.fbHelp.updateUser();
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
                Constants.fbHelp.updateUser();
            }
        });

        distancebar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentPref.setMaxMatchDistance((double) progress);
                searchradius.setText("" + progress);
                Constants.usersMatchedwith.clear();
                Constants.fbHelp.updateUser();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return root;

    }


    public void onCookRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.cook:
                if (checked)
                    currentPref.setPreferredTask("Cook");
                Log.e("Cook Selected", "Cook");

                break;
            case R.id.cleaner:
                if (checked)
                    currentPref.setPreferredTask("Clean");
                Log.e("Clean Selected", "Clean");

                break;
        }
        currentUser.setPreferences(currentPref);
        Constants.fbHelp.updateUser();
    }

    public void onGenderRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
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
        Constants.fbHelp.updateUser();
    }

    public void initView() {
        if (currentUser.getGender() == null) {
            myMaleButton.setChecked(false);
            myFemaleButton.setChecked(false);
        } else if (currentUser.getGender().equalsIgnoreCase("male")) {
            myMaleButton.setChecked(true);
        } else if (currentUser.getGender().equalsIgnoreCase("female")) {
            myFemaleButton.setChecked(true);
        }


        if (currentUser.getPreferences() == null || currentUser.getPreferences().getPreferredGender() == null) {
            maleButton.setChecked(false);
            femaleButton.setChecked(false);

        } else if (currentUser.getPreferences().getPreferredGender().equalsIgnoreCase("female")) {
            femaleButton.setChecked(true);
        } else if (currentUser.getPreferences().getPreferredGender().equalsIgnoreCase("male")) {
            maleButton.setChecked(true);
        } else if (currentUser.getPreferences().getPreferredGender().equalsIgnoreCase("both")) {
            bothMandFButton.setChecked(true);
        }

        if (currentUser.getPreferences() == null || currentUser.getPreferences().getPreferredTask() == null) {
            cookButton.setChecked(false);
            cleanButton.setChecked(false);
        } else if (currentUser.getPreferences().getPreferredTask().equalsIgnoreCase("cook")) {
            cookButton.setChecked(true);
        } else if (currentUser.getPreferences().getPreferredTask().equalsIgnoreCase("clean")) {
            cleanButton.setChecked(true);
        }

        if (currentUser.getPreferences().getAgeRange() != null) {
            minAge = currentUser.getPreferences().getAgeRange().getMinAge();
            maxAge = currentUser.getPreferences().getAgeRange().getMaxAge();
            if (minAge >= 18 && maxAge <= 55) {
                rangebar.setThumbIndices(minAge - 18, maxAge - 18);
                minagevalue.setText("" + minAge);
                maxage.setText("" + maxAge);
            }

        }

        if (currentUser.getPreferences().getMaxMatchDistance() != 0) {
            distancebar.setProgress((int) currentUser.getPreferences().getMaxMatchDistance());
        }


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.clearHistoryButton) {
            Constants.fbHelp.clearMatchHistory();
        }
    }

}
