package com.example.dindin;

import android.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import layout.LoginFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class LoginActivity extends FragmentActivity {

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Profile userProfile;
    private ProfilePictureView profilePictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        // Info is displayed below the login button
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button); // Create login button using layout.
        // The registerCallback method specifies button behavior upon click.
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            private ProfileTracker mProfileTracker;
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();

                if(Profile.getCurrentProfile() == null) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            // profile2 is the new profile
                            Log.v("facebook - profile", profile2.getFirstName());
                            userProfile = profile2;
                            mProfileTracker.stopTracking();
                            profilePictureView = (ProfilePictureView) findViewById(R.id.userProfilePic);
                            profilePictureView.setProfileId(userProfile.getId());
                            info.setText(
                                    "Welcome " + userProfile.getFirstName() + " " + userProfile.getLastName()
                            );
                        }
                    };
                    // no need to call startTracking() on mProfileTracker
                    // because it is called by its constructor, internally.
                }
                else {
                    Profile userProfile = Profile.getCurrentProfile();
                    Log.v("facebook - userprofile", userProfile.getFirstName());
                    profilePictureView = (ProfilePictureView) findViewById(R.id.userProfilePic);
                    profilePictureView.setProfileId(userProfile.getId());
                    info.setText(
                            "Welcome " + userProfile.getFirstName() + " " + userProfile.getLastName()
                    );
                }
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException error) {
                info.setText("Login attempt failed.");
            }
        });
        userProfile = Profile.getCurrentProfile();
        if(userProfile != null){
            profilePictureView = (ProfilePictureView) findViewById(R.id.userProfilePic);
            profilePictureView.setProfileId(userProfile.getId());
            info.setText(
                    "Welcome " + userProfile.getFirstName() + " " + userProfile.getLastName()
            );
        }
        if(userProfile != null) {
            Intent goToNextActivity = new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(goToNextActivity);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if you don't add following block,
        // your registered `FacebookCallback` won't be called
        if (callbackManager.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
    }

    public void simple(View v){
        Intent intent = new Intent(getApplicationContext(), LoginFragment.class);
        startActivity(intent);
    }

}
