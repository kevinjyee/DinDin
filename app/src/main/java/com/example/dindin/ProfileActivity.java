package com.example.dindin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dindin.utilities.Constants;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private RelativeLayout layout;
    private User viewedUser;
    private Button editButton;
    private TextView nameage, bio, loc, favdish, favcuisine, gender;
    private ImageView imageview;
    private dindinProfile targetProf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profileview);
        editButton = (Button) findViewById(R.id.editProfileButton);
        editButton.setVisibility(View.GONE);
        nameage = (TextView)findViewById(R.id.user_profile_name);
        bio = (TextView)findViewById(R.id.profile_short_bio);
        loc = (TextView)findViewById(R.id.user_profile_location);
        favdish = (TextView)findViewById(R.id.profile_dish);
        favcuisine = (TextView)findViewById(R.id.profile_cuisine);
        gender = (TextView)findViewById(R.id.user_profile_gender);
        imageview = (ImageView) findViewById(R.id.user_profile_photo);
        viewedUser = (User)getIntent().getExtras().getSerializable("viewedUser");
        layout = (RelativeLayout)findViewById(R.id.profile_layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // action to do
                finish();
                return true;//always return true to consume event
            }
        });
        //Instantiate User;
        if (viewedUser.getDindinProfile() != null) {
            targetProf = viewedUser.getDindinProfile();
        } else {
            targetProf = new dindinProfile();
        }
        setProfileInfo(viewedUser.getName(),
                viewedUser.getGender(),
                viewedUser.getAge(),
                targetProf.getShortBio(),
                targetProf.getCustomLocation(),
                targetProf.getFavoriteDishes(),
                targetProf.getFavoriteCuisines());
    }
    public void setProfileInfo(String name, String gen, String age, String short_bio, String location, String dish, String cuisine){
        nameage.setText(name + ", " + age);
        bio.setText(short_bio);
        loc.setText(location);
        favdish.setText(dish);


        Picasso.with(getApplicationContext())
                .load("https://graph.facebook.com/v2.2/" + viewedUser.getfbId() + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //

                .into(imageview);
        favcuisine.setText(cuisine);
        gender.setText(gen);
    }

}
