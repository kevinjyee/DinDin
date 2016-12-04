package com.example.dindin;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.example.dindin.utilities.Constants.currentUser;

/**
 * Created by Davin on 10/23/2016.
 */

public class ProfileEditFragment extends Fragment implements View.OnClickListener{
    private View view;
    private Button saveButton;
    private Button cancelButton;
    private EditText short_bio;
    private EditText location;
    private EditText dish;
    private EditText cuisine;
    private ImageView imageview;
    private dindinProfile currentProf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profileedit, null);
        view = root;
        short_bio = (EditText)view.findViewById(R.id.profile_short_bio);
        location = (EditText)view.findViewById(R.id.profile_location);
        dish = (EditText)view.findViewById(R.id.profile_dish);
        cuisine = (EditText) view.findViewById(R.id.profile_cuisine);

        saveButton = (Button) view.findViewById(R.id.saveProfileButton);
        saveButton.setOnClickListener(this);
        cancelButton = (Button)view.findViewById(R.id.cancelProfileButton);
        cancelButton.setOnClickListener(this);
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
        setCurrentValues(
                currentUser.getDindinProfile().getShortBio(),
                currentUser.getDindinProfile().getCustomLocation(),
                currentUser.getDindinProfile().getFavoriteDishes(),
                currentUser.getDindinProfile().getFavoriteCuisines()
        );

        return root;
    }
    public void setCurrentValues(String bio, String location, String dish, String cuisine){
        short_bio.setText(bio);
        this.location.setText(location);
        this.dish.setText(dish);
        this.cuisine.setText(cuisine);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.saveProfileButton:

                String short_bio_text = short_bio.getText().toString();
                String location_text = location.getText().toString();
                String dish_text = dish.getText().toString();
                String cuisine_text = cuisine.getText().toString();
                currentUser.getDindinProfile().setShortBio(short_bio_text);
                currentUser.getDindinProfile().setCustomLocation(location_text);
                currentUser.getDindinProfile().setFavoriteDishes(dish_text);
                currentUser.getDindinProfile().setFavoriteCuisines(cuisine_text);

                break;
            case R.id.cancelProfileButton:
                break;
        }
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_main_content_fragment, new ProfileFragment());
        ft.commit();
    }
}
