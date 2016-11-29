package com.example.dindin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dindin.com.example.NavBarActivity;
import com.example.dindin.com.example.UserFaceBookInfo;
import com.example.dindin.utilities.Constants;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.ProfileTracker;
import com.facebook.Profile;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextView info;
    private LoginButton loginButton;
    private LoginResult loginfinal;
    private Profile userProfile;
    private ProfilePictureView profilePictureView;
    private UserFaceBookInfo currentUserFaceBookInfo;
    CallbackManager callbackManager;


    private Location mLastLocation;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private SharedPreferences preferences;
    private Editor editor;

    private static final int PERMISSION_REQUEST_ACCESS_FINE_LOCATION=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email"); // https://developers.facebook.com/docs/facebook-login/permissions/
        // First we need to check availability of play services

        // Building the GoogleApi client

            //if (checkPlayServices()) {
                buildGoogleApiClient();
            //}
            mLocationRequest = LocationRequest.create()
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                    .setInterval(10 * 1000)
                    .setFastestInterval(1 * 1000);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        editor = preferences.edit();

        getLoginDetails(loginButton);

    }



    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    private void displayLocation() {
        if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED)) {
            mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
        }

            if (mLastLocation != null) {
                handleNewLocation(mLastLocation);
            } else {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }

    }
/*
    Initialize the facebook sdk and then callback manager will handle the login responses.
 */
    protected void facebookSDKInitialize() {
                FacebookSdk.sdkInitialize(getApplicationContext());
                callbackManager = CallbackManager.Factory.create();
    }
        /*
    Register a callback function with LoginButton to respond to the login result.
    On successful login,login result has new access token and  recently granted permissions.
    */
        protected void getLoginDetails(LoginButton login_button){
                // Callback registration
            info = (TextView)findViewById(R.id.info);
                login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    private ProfileTracker mProfileTracker;
            @Override
            public void onSuccess(LoginResult login_result) {
                                getUserInfo(login_result);


            }
                        @Override
            public void onCancel() {
                // code for cancellation
                            info.setText("Login attempt canceled.");
            }
                        @Override
            public void onError(FacebookException exception) {
                //  code to handle error
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

                editor.putString(Constants.FACEBOOK_ID, userProfile.getId());
                editor.putString(Constants.FIRST_NAME,userProfile.getFirstName());
                editor.putString(Constants.LAST_NAME,userProfile.getLastName());

                editor.commit();
                Intent goToNextActivity = new Intent(getApplicationContext(), FirebaseTestActivity.class);
                User currentUser = User.createUserFromProfile(userProfile);
                Constants.currentUser = currentUser;
                currentUser = Constants.currentUser;
                goToNextActivity.putExtra("currentUser", currentUser);
            //   editor.putString(Constants.PROFILE_IMAGE_ONE,
                        // getStoredImageUrl("1", data.getProfilePicture()));
                Constants.isFirstTime = false;
                startActivity(goToNextActivity);
            }
    }
/*
    To get the facebook user's own profile information via  creating a new request.
    When the request is completed, a callback is called to handle the success condition.
 */
    protected void getUserInfo(LoginResult login_result){
                GraphRequest data_request = GraphRequest.newMeRequest(

                login_result.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {


                                    editor.putString(Constants.FACEBOOK_ID, userProfile.getId());
                                    editor.putString(Constants.FIRST_NAME, userProfile.getFirstName());
                                    editor.putString(Constants.LAST_NAME, userProfile.getLastName());
                                    editor.commit();
                                Constants.isFirstTime = true;


                                Intent intent = new Intent(LoginActivity.this, NavBarActivity.class);
                                intent.putExtra("jsondata",json_object.toString());
                                startActivity(intent);
                    }
                });
                Bundle permission_param = new Bundle();
                permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
                data_request.setParameters(permission_param);
                data_request.executeAsync();
            }
        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
            Log.e("data",data.toString());
    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
                return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    if(!mGoogleApiClient.isConnected()){
                        mGoogleApiClient.connect();
                    }else {
                        displayLocation();
                    }
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
        @Override
    protected void onPause() {
            super.onPause();
            if(mGoogleApiClient.isConnected()){
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
                mGoogleApiClient.disconnect();
            }
                // Logs 'app deactivate' App Event.
                AppEventsLogger.deactivateApp(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Once connected with google api, get the location
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }else {
            displayLocation();
        }
    }

    @Override
    public void onLocationChanged(Location location){
        handleNewLocation(location);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }


    public void handleNewLocation(android.location.Location location){

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        com.example.dindin.com.example.Location ourLocation = new com.example.dindin.com.example.Location(latitude,longitude);
        Constants.currentUser.setLocation(ourLocation);

        Log.d(TAG, location.toString());
    }

/*
    private class BackGroundTaskFetchFBData extends AsyncTask<String, Void, Void>
    {

        private DatabaseHelper databaseHandler = new DatabaseHelper(
                LoginActivity.this);
        @Override
        protetected Void doInBackGround(String... params){
        return null;
    }
        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);


        }

    }
    */
}

