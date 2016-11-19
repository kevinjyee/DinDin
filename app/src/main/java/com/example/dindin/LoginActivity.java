package com.example.dindin;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dindin.com.example.NavBarActivity;
import com.example.dindin.utilities.AlertDialogManager;
import com.example.dindin.utilities.AppLog;
import com.example.dindin.utilities.ConnectionDetector;
import com.example.dindin.utilities.LocationFinder;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
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
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.io.IOException;


public class LoginActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private LoginResult loginfinal;
    private Profile userProfile;
    private ProfilePictureView profilePictureView;
    private ConnectionDetector cd;
    private String regid;
    private static final String TAG = "LoginActivity";
    Context context;
    GoogleCloudMessaging gcm;
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1972;
    CallbackManager callbackManager;
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private LocationFinder newLocationFinder;
    double mLatitude;
    double mLongitude;
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            facebookSDKInitialize();
            setContentView(R.layout.activity_login);
            LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions("email"); // https://developers.facebook.com/docs/facebook-login/permissions/
            context = getApplicationContext();
            checkLoginDetails();
            getLoginDetails(loginButton);

    }

    protected void checkLoginDetails()
    {
        cd = new ConnectionDetector(getApplicationContext());

        if (cd.isConnectingToInternet()) {
            if (checkPlayServices()) {
                regid =FirebaseInstanceId.getInstance().getToken();
                if (regid.isEmpty()) {
                    registerInBackground();
                } else {
                    AppLog.Log(TAG, "reg id saved : " + regid);
                }
            } else {
                return;
            }
        } else {
            AlertDialogManager
                    .internetConnetionErrorAlertDialog(LoginActivity.this);
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If it
     * doesn't, display a dialog that allows users to download the APK from the
     * Google Play Store or enable it in the device's system settings.
     */
    public boolean checkPlayServices() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();

        int resultCode = api.isGooglePlayServicesAvailable(this);

        if (resultCode == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(resultCode) &&
                api.showErrorDialogFragment(this, resultCode, REQUEST_GOOGLE_PLAY_SERVICES)) {
            // wait for onActivityResult call (see below)
        } else {
            Toast.makeText(this, api.getErrorString(resultCode), Toast.LENGTH_LONG).show();
        }
        return false;
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

                Intent goToNextActivity = new Intent(getApplicationContext(), NavBarActivity.class);

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
                                                Intent intent = new Intent(LoginActivity.this,NavBarActivity.class);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
                //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
                return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
        LocationManager locationManagerresume = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManagerresume
                .isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            newLocationFinder = new LocationFinder();
            newLocationFinder.getLocation(LoginActivity.this,
                    mLocationResult);
        } else {
            showGPSDisabledAlertToUser();

        }
    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        alertDialogBuilder
                .setMessage(
                        "GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton(R.string.okbuttontext,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton(R.string.button_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.setCancelable(false);
        alert.show();
       // Toast.makeText(context,"GPS is Off",Toast.LENGTH_LONG);
    }
    LocationFinder.LocationResult mLocationResult = new LocationFinder.LocationResult() {
        public void gotLocation(final double latitude, final double longitude) {
            // logDebug("gotLocation  latitude "+latitude);
            // logDebug("gotLocation  longitude "+longitude);
            System.out.println("Got Location...Lat:" + String.valueOf(latitude)
                    + "Long:" + String.valueOf(longitude));
            if (latitude == 0.0 || longitude == 0.0) {

                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Current Location not found please Switch on your GPS_PROVIDER or  NETWORK_PROVIDER");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            } else {
                runOnUiThread(new Runnable() {
                    public void run() {
                        mLatitude = latitude;
                        mLongitude = longitude;
                    }
                });
            }
        }
    };
        @Override
    protected void onPause() {
            super.onPause();
                // Logs 'app deactivate' App Event.
                AppEventsLogger.deactivateApp(this);
    }

    @SuppressLint("NewApi")
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            // logDebug("getRegistrationId   Registration not found.");

            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
                Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            // Log.i(TAG, "App version changed.");
            // logDebug("getRegistrationId   App version changed.");
            return "";
        }
        return registrationId;
    }
    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGCMPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences,
        // but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(LoginActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    private void registerInBackground() {
        new GCMRegistration().execute();

    }

    private class GCMRegistration extends AsyncTask<String, Void, Void> {
        private boolean flagforresponse = true;
        private String[] params;

        @Override
        protected Void doInBackground(String... params) {
            String msg = "";

                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }

                String regidfoundseccessfully = "getGoogleRegistrationId";
                msg = "GCMRegistration doInBackground Device registered, registration ID="
                        + regid;
                storeRegistrationId(context, regid);

            return null;
        }

        /**
         * Stores the registration ID and app versionCode in the application's
         * {@code SharedPreferences}.
         *
         * @param context
         *            application's context.
         * @param regId
         *            registration ID
         */
        private void storeRegistrationId(Context context, String regId) {
            final SharedPreferences prefs = getGCMPreferences(context);
            int appVersion = getAppVersion(context);
            // Log.i(TAG, "Saving regId on app version " + appVersion);
            // logDebug("Saving regId on app version " + appVersion);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(PROPERTY_REG_ID, regId);
            editor.putInt(PROPERTY_APP_VERSION, appVersion);
            editor.commit();
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }


    }
}
