package com.example.dindin;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.dindin.com.example.NavBarActivity;
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
import com.facebook.login.widget.ProfilePictureView;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private TextView info;
    private LoginButton loginButton;
    private LoginResult loginfinal;
    private Profile userProfile;
    private ProfilePictureView profilePictureView;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            facebookSDKInitialize();
            setContentView(R.layout.activity_login);
            LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions("email"); // https://developers.facebook.com/docs/facebook-login/permissions/
            getLoginDetails(loginButton);
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
                // Logs 'install' and 'app activate' App Events.
                AppEventsLogger.activateApp(this);
    }
        @Override
    protected void onPause() {
            super.onPause();
                // Logs 'app deactivate' App Event.
                AppEventsLogger.deactivateApp(this);
    }
}
