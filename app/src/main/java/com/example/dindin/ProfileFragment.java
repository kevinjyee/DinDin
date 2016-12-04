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

import static com.example.dindin.utilities.Constants.currentUser;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View view;
    private dindinProfile currentProf;
    private Button editButton;
    private TextView nameage, bio, loc, favdish, favcuisine, gender;
    private ImageView imageview;
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
        Picasso.with(getActivity())
                .load("https://graph.facebook.com/v2.2/" + currentUser.getfbId() + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //
                .into(imageview);


        //Instantiate User;
        if (currentUser.getDindinProfile() != null) {
            currentProf = currentUser.getDindinProfile();
        } else {
            currentProf = new dindinProfile();
            currentUser.setDindinProfile(currentProf);
        }
        setProfileInfo(currentUser.getName(),
                currentUser.getGender(),
                currentUser.getAge(),
                currentUser.getDindinProfile().getShortBio(),
                currentUser.getDindinProfile().getCustomLocation(),
                currentUser.getDindinProfile().getFavoriteDishes(),
                currentUser.getDindinProfile().getFavoriteCuisines()
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
                .load("https://graph.facebook.com/v2.2/" + currentUser.getfbId() + "/picture?height=120&type=normal") //extract as User instance method
                .error(R.drawable.dislike_off) //

                .into(imageview);
        favcuisine.setText(cuisine);
        if(gen == null){gen = "gender: n/a";}
        gender.setText(gen);
    }
}