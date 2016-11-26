package com.example.dindin;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dindin.com.example.NavBarActivity;
import com.example.dindin.com.example.UserMatchData;
import com.example.dindin.utilities.AppLog;
import com.example.dindin.utilities.ConnectionDetector;
import com.example.dindin.utilities.Constants;
import com.example.dindin.utilities.ScalingUtilities;
import com.example.dindin.utilities.ScreenSize;
import com.example.dindin.utilities.Utilities;
import com.facebook.internal.Utility;
import com.facebook.login.widget.ProfilePictureView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.action;
import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * handle interaction events.
 * Use the {@link FindMatches#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindRecipe extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RelativeLayout swipeviewlayout;
    private RelativeLayout noMatchFound;
    private ImageView userProfilImage, amimagetedview;
    private TextView messagetextview;

    private ArrayList<Recipe> MatchedRecipeList;
    private Recipe UserData;
    private String matchedUsersFaceBookID;

    private Button matchedUserInfoButton;
    private int[] matchUserHeightAndWidth;
    private int[] topMarginForInvitelayoutAndText;
    private int[] profileImageHeightAndWidth;
    private int numMatches;
    private int x_cord, y_cord;
    private int Likes = 0;
    // private RelativeLayout parentView;
    private float alphaValue = 0;
    private int _xDelta;
    private int _yDelta;
    static int xd, yd;
    private int windowwidth;
    private int screenCenter;

    private int[] imageLayoutHeightandWidth;
    private Button likeButton, dislikeButton;
    private RelativeLayout /* imagevewsecondlayout, */likedislikelayout;
    private RelativeLayout invitebuttonlayout;
    private RelativeLayout findingpeopletextlayout;
    private boolean downloadcallfirsttime = true;
    private int imageindex = 0;
    private int MatchCount;
    private int numberOfImageDownload = 3;
    private Button inviteButton;
    private ProfilePictureView profileImage;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences preferences;
    private Animation anime;


    public FindRecipe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindMatches.
     */
    // TODO: Rename and change types and number of parameters
    public static FindRecipe newInstance(String param1, String param2) {
        FindRecipe fragment = new FindRecipe();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate Fragment_layout1, the fragment with our main view.
        View view = inflater.inflate(R.layout.fragment_layout1, null);

        //preferences store our current user preferences from device (i.e. profile pics)
        preferences = PreferenceManager.getDefaultSharedPreferences(inflater.getContext());

        //Inflate Layout
       // findingpeopletextlayout = (RelativeLayout) view
         //       .findViewById(R.id.findingpeopletextlayout);



        //Layout for swipe section
        swipeviewlayout = (RelativeLayout) view
                .findViewById(R.id.swipeviewlayout);

        //Location for userProfilImage section
        userProfilImage = (ImageView) view.findViewById(R.id.userProfilImage);


        //"Finding People near you" text
        messagetextview = (TextView) view.findViewById(R.id.messagetextview);

        //Circle to encapsulate profile image
        amimagetedview = (ImageView) view.findViewById(R.id.circleimageview);

        //if no matches foud, inflate thie view
        noMatchFound = (RelativeLayout) view.findViewById(R.id.noMatchFound);

        //Middle Info button
        matchedUserInfoButton = (Button) view
                .findViewById(R.id.matchedUserInfoButton);
        //Like Button
        likeButton = (Button) view.findViewById(R.id.likeButton);

        //DislikeButton
        dislikeButton = (Button) view.findViewById(R.id.dislikeButton);


        //profileImage = (ProfilePictureView) view.findViewById(R.id.iv_user_image_user_matches);
        //Layout containing all our buttons
        likedislikelayout = (RelativeLayout) view
                .findViewById(R.id.likedislikelayout);

        // Set listeners to our buttons
        matchedUserInfoButton.setOnClickListener(this);
        likeButton.setOnClickListener(this);
        dislikeButton.setOnClickListener(this);





        //Create new Utilities to get screen sizes
        Utilities mUltilities = new Utilities();
        matchUserHeightAndWidth = mUltilities
                .getImageHeightAndWidthForMatchedUser(getActivity());
        profileImageHeightAndWidth = mUltilities
                .getImageHeightAndWidthForProFileImageHomsecreen(getActivity());
        imageLayoutHeightandWidth = mUltilities
                .imageLayoutHeightandWidth(getActivity());
        topMarginForInvitelayoutAndText = mUltilities
                .getTopMarginForInviteLayoutAndText(getActivity());

        RelativeLayout.LayoutParams findPeoplelayoutParams = mUltilities
                .getRelativelayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        findPeoplelayoutParams.addRule(RelativeLayout.BELOW,
                R.id.imageviewlayout);
        findPeoplelayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        findPeoplelayoutParams.setMargins(0,
                topMarginForInvitelayoutAndText[0], 0, 0);
       // findingpeopletextlayout.setLayoutParams(findPeoplelayoutParams);

        RelativeLayout.LayoutParams invitlayoutParams = mUltilities
                .getRelativelayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        invitlayoutParams.addRule(RelativeLayout.BELOW,
                R.id.findingpeopletextlayout);
        invitlayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        invitlayoutParams.setMargins(0, topMarginForInvitelayoutAndText[1], 0, 0);

        RelativeLayout.LayoutParams likedislikeparam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        likedislikeparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        likedislikeparam.setMargins(0, imageLayoutHeightandWidth[3], 0, 0);
        likedislikelayout.setLayoutParams(likedislikeparam);
//
        ScreenSize screenSize = new ScreenSize(getActivity());
//        // Log.e(TAG,
//        // "his : "
//        // + getActivity().getWindowManager().getDefaultDisplay()
//        // .getWidth() + "my : "
//        // + screenSize.getScreenWidthPixel());
        windowwidth = (int) screenSize.getScreenWidthPixel();
        screenCenter = windowwidth / 2;

        try {
            //sets user profile image nad pic
            setProfilePic(userProfilImage, profileImageHeightAndWidth[0],
                    profileImageHeightAndWidth[1]);
        } catch (Exception e) {
            AppLog.handleException(TAG + " onCreateView  Exception ", e);
        }

        ConnectionDetector connectionDetector = new ConnectionDetector(
                getActivity());

        if (connectionDetector.isConnectingToInternet()) {

            findMatch(); ///TODO here
            // }
        } else {


        }

        // addView Here

        return view;
    }

    private void setProfilePic(final ImageView userProfilImage,
                               final int height, final int width) {
        final Utilities mUltilities = new Utilities();


        new Thread(new Runnable() {

            @Override
            public void run() {
                //get image from our stored preference
                final Bitmap bitmapimage = Utilities.getBitmapFromURL(preferences
                        .getString("imageOne", ""));
                AppLog.Log(
                        TAG,
                        "Profile Image Url:"
                                + preferences.getString(
                                "imageOne", ""));
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Bitmap cropedBitmap = null;
                            ScalingUtilities mScalingUtilities = new ScalingUtilities();
                            Bitmap mBitmap = null;
                            if (bitmapimage != null) {
                                cropedBitmap = mScalingUtilities
                                        .createScaledBitmap(bitmapimage, width,
                                                height, ScalingUtilities.ScalingLogic.CROP);
                                bitmapimage.recycle();
                                mBitmap = mUltilities.getCircleBitmap(
                                        cropedBitmap, 1);
                                cropedBitmap.recycle();
                                userProfilImage.setImageBitmap(mBitmap);
                            } else {

                            }

                        }
                    });
                }

            }
        }).start();
    }

    /**
     *Use this method to start a new asynchronous task to findMatches
     */
    private void findMatch() {

        new BackGroundTask().execute();
    }

    private class BackGroundTask extends AsyncTask<String,Void, Void>{
        private Utilities currentUtils = new Utilities();
        private List<NameValuePair>  findMatchList;
        private String MatchResponse;
        private String fbId;

        private boolean success = true;

        @Override
        protected Void doInBackground(String... params){

            try {
                fbId = preferences.getString(Constants.FACEBOOK_ID, "");
                String[] findMatchParameter = { fbId };
//                findMatchList = currentUtils.getFindMatchParameter(findMatchParameter);
//                MatchResponse = currentUtils.makeHttpRequest(
//                        Constants.findMatch_url, Constants.methodeName,
//                        findMatchList);
//
//                Gson gson = new Gson();
//                matchData = gson.fromJson(MatchResponse,
//                        UserMatchData.class);



            } catch (Exception e) {
                e.printStackTrace();
                success = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // try {
                /*Kevin's Facebook ID: 100001411585746
                * 1	www.facebook.com/davin.siu?fref=ts	1151947893
2	www.facebook.com/stefan.bordovsky?fref=ts	1408027584
3	www.facebook.com/stephen.e.tran?fref=ts	705738627*/

            success = true;
            Recipe Bacon = new Recipe("Stuffing with Bacon","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2013/2/22/1/GH0503H_christmas-stuffing-with-bacon-recipe_s4x3.jpg.rend.sni12col.landscape.jpeg"
                   ,"Total Time:\n" +
                    "1 hr 40 min\n" +
                    "Prep:\n" +
                    "30 min\n" +
                    "Inactive:\n" +
                    "10 min\n" +
                    "Cook:\n" +
                    "1 hr\n" +
                    "\n" +
                    "\n" +
                    "Read more at: http://www.foodnetwork.com/recipes/giada-de-laurentiis/christmas-stuffing-with-bacon-recipe.html?oc=linkback","");

            Recipe Panini = new Recipe("Cobb Salada Panini","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2011/11/4/0/CCFOO121_Cobb-Salad-Panini_s4x3.jpg.rend.sni12col.landscape.jpeg",
                    "Total Time:\n" +
                            "30 min\n" +
                            "Prep:\n" +
                            "20 min\n" +
                            "Cook:\n" +
                            "10 min\n" +
                            "\n" +
                            "Read more at: http://www.foodnetwork.com/recipes/tyler-florence/cobb-salad-panini-recipe.html?oc=linkback","");
            Recipe PotPie = new Recipe("Turkey Pot Pie","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2013/6/21/0/FNK_Turkey-Pot-Pie_s4x3.jpg.rend.sni12col.landscape.jpeg"
            ,"Total Time:\n" +
                    "1 hr 10 min\n" +
                    "Prep:\n" +
                    "20 min\n" +
                    "Cook:\n" +
                    "50 min\n" +
                    "\n" +
                    "Read more at: http://www.foodnetwork.com/recipes/turkey-pot-pie-recipe.html?oc=linkback","");
            Recipe AppleCobbler = new Recipe("Apple Cobbler for Two","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2015/12/8/1/FNK_Apple-Cobbler-for-Two_s4x3.jpg.rend.sni12col.landscape.jpeg"
            ,"Total Time:\n" +
                    "1 hr 15 min\n" +
                    "Prep:\n" +
                    "15 min\n" +
                    "Inactive:\n" +
                    "10 min\n" +
                    "Cook:\n" +
                    "50 min\n" +
                    "\n" +
                    "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/apple-cobbler-for-two.html?oc=linkback","");

            Recipe CarrotCake = new Recipe("Carrot Cake","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2015/12/8/1/FNK_Carrot-Cake-for-Two_s4x3.jpg.rend.sni12col.landscape.jpeg" ,
                    "Total Time:\n" +
                            "2 hr 55 min\n" +
                            "Prep:\n" +
                            "30 min\n" +
                            "Inactive:\n" +
                            "1 hr 50 min\n" +
                            "Cook:\n" +
                            "35 min\n" +
                            "\n" +
                            "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/carrot-cake-for-two.html?oc=linkback","");
            Recipe TurkeyBolognese = new Recipe("Turkey Bolognese","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2011/8/10/1/Thanksgiving-2011_EI0707-turkey-bolognese_s4x3.jpg.rend.sni12col.landscape.jpeg"
            ,"Total Time:\n" +
                    "50 min\n" +
                    "Prep:\n" +
                    "20 min\n" +
                    "Cook:\n" +
                    "30 min\n" +
                    "\n" +
                    "Read more at: http://www.foodnetwork.com/recipes/giada-de-laurentiis/turkey-bolognese-recipe.html?oc=linkback","");

            Recipe ShrimpScampi = new Recipe("Baked Shrimp Scampi","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2012/11/12/0/FN_Ina-Garten-Baked-Shrimp-Scampi_s4x3.jpg.rend.sni12col.landscape.jpeg"
            ,"Total Time:\n" +
                    "43 min\n" +
                    "Prep:\n" +
                    "30 min\n" +
                    "Cook:\n" +
                    "13 min\n" +
                    "\n" +
                    "Read more at: http://www.foodnetwork.com/recipes/ina-garten/baked-shrimp-scampi-recipe.html?oc=linkback","");

              Recipe ChickenParm = new Recipe("Skillet Chicken Parmesan with Artichokes","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2016/5/11/1/FNM_060116-Skillet-Chicken-Parmesan-with-Artichokes_s4x3.jpg.rend.sni12col.landscape.jpeg"
            ,"Total Time:\n" +
                    "40 min\n" +
                    "Prep:\n" +
                    "15 min\n" +
                    "Inactive:\n" +
                    "5 min\n" +
                    "Cook:\n" +
                    "20 min\n" +
                    "\n" +
                    "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/skillet-chicken-parmesan-with-artichokes.html?oc=linkback","");

                Recipe PorkChop = new Recipe("Pork Chop with Pear Chutney","http://foodnetwork.sndimg.com/content/dam/images/food/fullset/2014/4/28/0/FNK_Pork-Chops-with-Pear-Chutney_s4x3.jpg.rend.sni12col.landscape.jpeg",
                        "Total Time:\n" +
                                "30 min\n" +
                                "Prep:\n" +
                                "15 min\n" +
                                "Cook:\n" +
                                "15 min\n" +
                                "\n" +
                                "Read more at: http://www.foodnetwork.com/recipes/food-network-kitchens/pork-chops-with-pear-chutney-recipe.html?oc=linkback","");

            if (success) {

                MatchedRecipeList = new ArrayList<>();
                // MatchedRecipeList = matchData.getMatches();



                MatchedRecipeList.add(Bacon);
                MatchedRecipeList.add(Panini);
                MatchedRecipeList.add(PotPie);
                MatchedRecipeList.add(AppleCobbler);
                MatchedRecipeList.add(CarrotCake);
                MatchedRecipeList.add(TurkeyBolognese);
                MatchedRecipeList.add(ShrimpScampi);
                MatchedRecipeList.add(ChickenParm);
                MatchedRecipeList.add(PorkChop);

                Log.i(TAG, "**** Matches Found MatchedRecipeList ****");



                addMatchesView(MatchedRecipeList);
            } else {

                messagetextview.setText("No new recipes");
                Constants.isMatchedFound = false;
            }

//            } catch (Exception e) {
//
//                messagetextview.setText("There`s no one new around you");
//
//            }
        }


    }

    /*Add Matches to View with ArrayList*/
    private void addMatchesView(final ArrayList<Recipe> MatchedRecipeList){

        numMatches = MatchedRecipeList.size();

        LayoutInflater inflater = getActivity().getLayoutInflater();

        for(int i = 0; i < numMatches; i++)
        {
            final int position = i;
            final RelativeLayout myRelativeView = (RelativeLayout) inflater.inflate(
                    R.layout.match_user_layout,null
            );

            UserData = this.MatchedRecipeList.get(i);
            matchedUsersFaceBookID = this.MatchedRecipeList.get(i).getFoodName();
            myRelativeView.setTag(i);

            // ProfilePictureView  profileImg = (ProfilePictureView) myRelativeView.findViewById(R.id.iv_user_image_user_matches);
            //profileImg.setProfileId(MatchedRecipeList.get(i).getfbId());

            /* Note to sefly, bmy
            ImageView imageView = (ImageView) myRelativeView.findViewById(R.id.iv_user_image_user_matches);

            Picasso.with(getActivity()).load(MatchedRecipeList.get(i).getFacebookProfile().getProfilePictureUri(50,50))
            */

            ImageView imageView = (ImageView) myRelativeView
                    .findViewById(R.id.iv_user_image_user_matches);
            TextView txtMatchPercent = (TextView) myRelativeView
                    .findViewById(R.id.txtMatchPerCent);
            txtMatchPercent.setText(
                     "");
            Picasso.with(getActivity())
                    .load(MatchedRecipeList.get(i).getImage_url()) //extract as User instance method
                    .error(R.drawable.dislike_off) //
                    .resize(matchUserHeightAndWidth[1],
                            matchUserHeightAndWidth[0]) //
                    .into(imageView);


            TextView tvAge = (TextView) myRelativeView
                    .findViewById(R.id.tv_name_age);
            tvAge.setText(MatchedRecipeList.get(i).getFoodName());



            final Button imageLike = new Button(getActivity());
            imageLike.setLayoutParams(new RelativeLayout.LayoutParams(100, 50));
            imageLike.setText("Liked");
            imageLike.setTextColor(android.graphics.Color.GREEN);
            imageLike.setBackgroundColor(android.graphics.Color.TRANSPARENT);
            imageLike.setX(20);
            imageLike.setY(80);
            imageLike.setAlpha(alphaValue);
            myRelativeView.addView(imageLike);// 3rd view

            final Button imagePass = new Button(getActivity());
            imagePass.setBackgroundColor(android.graphics.Color.TRANSPARENT);

            imagePass.setLayoutParams(new RelativeLayout.LayoutParams(100, 50));
            imagePass.setText("pass");
            imagePass.setTextColor(android.graphics.Color.RED);

            imagePass.setX((windowwidth - 200));
            imagePass.setY(100);
            imagePass.setRotation(45);
            imagePass.setAlpha(alphaValue);
            myRelativeView.addView(imagePass);// 4 th view

            myRelativeView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    NavBarActivity activity;
                    x_cord = (int) event.getRawX();
                    y_cord = (int) event.getRawY();

                    final int X = (int) event.getRawX();
                    final int Y = (int) event.getRawY();

                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_DOWN:
                            activity = (NavBarActivity) getActivity();

                            xd = X;
                            yd = Y;
                            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) myRelativeView
                                    .getLayoutParams();
                            _xDelta = X - lParams.leftMargin;
                            _yDelta = Y - lParams.topMargin;
                            break;
                        case MotionEvent.ACTION_MOVE:

                            x_cord = (int) event.getRawX();
                            y_cord = (int) event.getRawY();

                            myRelativeView.setX(X - _xDelta);
                            myRelativeView.setY(Y - _yDelta);

                            if (x_cord >= screenCenter) {
                                myRelativeView
                                        .setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                if (x_cord > (screenCenter + (screenCenter / 2))) {
                                    imageLike.setAlpha(1);
                                    if (x_cord > (windowwidth - (screenCenter / 4))) {
                                        Likes = 2;
                                    } else {
                                        Likes = 0;
                                    }
                                } else {
                                    Likes = 0;
                                    imageLike.setAlpha(0);
                                }
                                imagePass.setAlpha(0);
                            } else {
                                // rotate
                                myRelativeView
                                        .setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                if (x_cord < (screenCenter / 2)) {
                                    imagePass.setAlpha(1);
                                    if (x_cord < screenCenter / 4) {

                                        Likes = 1;

                                    } else {
                                        Likes = 0;
                                    }
                                } else {
                                    Likes = 0;
                                    imagePass.setAlpha(0);
                                }

                                imageLike.setAlpha(0);
                            }

                            break;
                        case MotionEvent.ACTION_UP:

                            x_cord = (int) event.getRawX();
                            y_cord = (int) event.getRawY();

                            Log.e("X Point", "" + x_cord + " , Y " + y_cord);
                            imagePass.setAlpha(0);
                            imageLike.setAlpha(0);

                            if (Likes == 0) {
                                Log.e("Event Status", "Nothing");
                                myRelativeView.setX(imageLayoutHeightandWidth[4]);
                                myRelativeView.setY(imageLayoutHeightandWidth[2]);
                                myRelativeView.setRotation(0);
                            } else if (Likes == 1) {
                                Log.e("Event Status", "Passed");
                                imageindex = imageindex + 1;

                                if (imageindex == MatchCount) {
                                    hideSwipeLayout();
                                }

                                UserData = FindRecipe.this.MatchedRecipeList
                                        .get(position);
                                matchedUsersFaceBookID = UserData.getFoodName();
                                AppLog.Log(TAG,
                                        "Event Status   matchedUsersFaceBookID  "
                                                + matchedUsersFaceBookID);
                                swipeviewlayout.removeView(myRelativeView);
                                // likeMatchedUser(Constant.isDisliked);
                                userisLiked(false,UserData);
                            } else if (Likes == 2) {
                                imageindex = imageindex + 1;
                                if (imageindex == MatchCount) {
                                    hideSwipeLayout();
                                }
                                Log.e("Event Status", "Liked");
                                int viewCount = swipeviewlayout.getChildCount();
                                Recipe matchesData = MatchedRecipeList
                                        .get(viewCount - 1);
                                matchedUsersFaceBookID = matchesData.getFoodName();
                                swipeviewlayout.removeView(myRelativeView);
                                userisLiked(true,matchesData);
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
            // set visible true if match user count is more than one
            if (numMatches > 0) {
                likedislikelayout.setVisibility(View.VISIBLE);
                swipeviewlayout.setVisibility(View.VISIBLE);

            }


            swipeviewlayout.addView(myRelativeView);

        }




    }


    private void hideSwipeLayout() {
        swipeviewlayout.setVisibility(View.GONE);
        likedislikelayout.setVisibility(View.GONE);

        noMatchFound.setVisibility(View.VISIBLE);
        amimagetedview.setVisibility(View.VISIBLE);

        messagetextview.setText("There`s no one new around you");


        imageindex = 0;
        MatchCount = 0;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.matchedUserInfoButton) {
            int viewCount = swipeviewlayout.getChildCount();
            Recipe matchesData = MatchedRecipeList.get(viewCount - 1);
            int selectedImageIndex = viewCount - 1;
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Info");
            alertDialog.setMessage(matchesData.getIngredients());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        if (v.getId() == R.id.likeButton) {
            // likeButton.setEnabled(false);

            int viewCount = swipeviewlayout.getChildCount();
            Recipe matchesData = MatchedRecipeList.get(viewCount - 1);
            matchedUsersFaceBookID = matchesData.getFoodName();
            userisLiked(true,matchesData);
            RelativeLayout animatedview = null;
            int removeViewindex = 0;
            if (viewCount > 0) {
                removeViewindex = viewCount - 1;
                animatedview = (RelativeLayout) swipeviewlayout
                        .getChildAt(removeViewindex);
                Button dislikesButton = (Button) animatedview.getChildAt(4);
                dislikesButton.setAlpha(1);
            }
            if (removeViewindex == 0) {

                swipeviewlayout.setVisibility(View.GONE);
                likedislikelayout.setVisibility(View.GONE);
                noMatchFound.setVisibility(View.VISIBLE);
                amimagetedview.setVisibility(View.VISIBLE);
                messagetextview.setText("There`s no one new around you");


            }

            // logDebug("dislikeButton  viewCount "+viewCount);
            imageindex = imageindex + 1;

            swipeAnime(true, removeViewindex, animatedview);
        }

        if (v.getId() == R.id.dislikeButton) {
            //  dislikeButton.setEnabled(false);

            int viewCount = swipeviewlayout.getChildCount();
            Recipe matchesData = MatchedRecipeList.get(viewCount - 1);
            matchedUsersFaceBookID = matchesData.getFoodName();
            userisLiked(false,matchesData);
            RelativeLayout animatedview = null;
            int removeViewindex = 0;
            if (viewCount > 0) {
                removeViewindex = viewCount - 1;
                animatedview = (RelativeLayout) swipeviewlayout
                        .getChildAt(removeViewindex);
                Button dislikesButton = (Button) animatedview.getChildAt(4);
                dislikesButton.setAlpha(1);
            }
            if (removeViewindex == 0) {
                // likedislikelayout.setVisibility(View.GONE);
                // invitebuttonlayout.setVisibility(View.VISIBLE);
                swipeviewlayout.setVisibility(View.GONE);
                likedislikelayout.setVisibility(View.GONE);
                //  setFullScreenMenuTouchEnable(true);
                noMatchFound.setVisibility(View.VISIBLE);
                amimagetedview.setVisibility(View.VISIBLE);
                messagetextview.setText("There`s no one new around you");


            }

            // logDebug("dislikeButton  viewCount "+viewCount);
            imageindex = imageindex + 1;

            swipeAnime(false, removeViewindex, animatedview);

        }
    }

    private void swipeAnime(boolean isLiked, final int viewindex,
                            RelativeLayout relativeLayout) {
        AnimationSet snowMov1 = null;
        // logDebug("rotedandRansletAnimation  isLiked "+isLiked);
        if (isLiked) {

            snowMov1 = new AnimationSet(true);
            RotateAnimation rotate1 = new RotateAnimation(0, 20,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
            rotate1.setStartOffset(50);
            rotate1.setDuration(1000);
            snowMov1.addAnimation(rotate1);
            TranslateAnimation trans1 = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 1.5f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            trans1.setDuration(1000);
            snowMov1.addAnimation(trans1);
        } else {
            snowMov1 = new AnimationSet(true);
            RotateAnimation rotate1 = new RotateAnimation(0, -20,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.0f);
            rotate1.setStartOffset(50);
            rotate1.setDuration(1000);
            snowMov1.addAnimation(rotate1);
            TranslateAnimation trans1 = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, -1.5f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            trans1.setDuration(1000);
            // snowMov1.setFillAfter(true);
            snowMov1.addAnimation(trans1);
        }


        relativeLayout.startAnimation(snowMov1);
        snowMov1.setAnimationListener(new Animation.AnimationListener() {

            int secondIndex = imageindex + 1;

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dislikeButton.setEnabled(true);
                likeButton.setEnabled(true);
                try {
                    swipeviewlayout.removeViewAt(viewindex);
                }
                catch(Exception e)
                {

                }
                if (viewindex == 1) {
                    hideSwipeLayout();
                } else {

                }


            }
        });

    }

    private void userisLiked(boolean isLiked, Recipe matchedUser)
    {
        if(isLiked)
        {
            String myFaceBookID = Constants.FACEBOOK_ID;
            String currentUserFaceBookId = matchedUsersFaceBookID;

           // Constants.usersMatchedwith.add(matchedUser);

        }
    }




}





