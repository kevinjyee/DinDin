package com.example.dindin;

/**
 * Created by Davin on 10/23/2016.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dindin.utilities.Constants;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View view;
    Button editButton;
    TextView nameage, bio, loc, favdish, favcuisine, gender;
    ImageView imageview;
    private SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profileview, null);
        view = root;
        editButton = (Button) view.findViewById(R.id.editProfileButton);
        editButton.setOnClickListener(this);

        nameage = (TextView)view.findViewById(R.id.user_profile_name);
        bio = (TextView)view.findViewById(R.id.profile_short_bio);
        loc = (TextView)view.findViewById(R.id.user_profile_location);
        favdish = (TextView)view.findViewById(R.id.profile_dish);
        favcuisine = (TextView)view.findViewById(R.id.profile_cuisine);
        gender = (TextView)view.findViewById(R.id.user_profile_gender);
        imageview = (ImageView) view.findViewById(R.id.user_profile_photo);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String fbId = preferences.getString(Constants.FACEBOOK_ID, "");
        String firstname = preferences.getString(Constants.FIRST_NAME,"");
        Picasso.with(getActivity())
                .load("https://graph.facebook.com/v2.2/" + fbId + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //
                .into(imageview);

        setProfileInfo(preferences.getString(Constants.DISPLAY_NAME,firstname),
                preferences.getString(Constants.GENDER,"Gender: N/A"),
                preferences.getString(Constants.AGE,"21"),
                preferences.getString(Constants.SHORT_BIO,Constants.UNKNOWN),
                preferences.getString(Constants.LOCATION,"Austin,TX"),
                preferences.getString(Constants.DISH,Constants.UNKNOWN),
                preferences.getString(Constants.CUISINE,Constants.UNKNOWN)
                );

        return root;
    }
    @Override
    public void onClick(View v) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ProfileEditFragment editFragment = new ProfileEditFragment();
        ft.replace(R.id.activity_main_content_fragment, editFragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void setProfileInfo(String name, String gen, String age, String short_bio, String location, String dish, String cuisine){
        nameage.setText(name + ", " + age);
        bio.setText(short_bio);
        loc.setText(location);
        favdish.setText(dish);


        Picasso.with(getActivity())
                .load("https://graph.facebook.com/v2.2/" + preferences.getString(Constants.FACEBOOK_ID,"") + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //

                .into(imageview);
        favcuisine.setText(cuisine);
        gender.setText(gen);
    }
}