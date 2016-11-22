package com.example.dindin;



import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dindin.utilities.AppLog;
import com.example.dindin.utilities.ConnectionDetector;
import com.example.dindin.utilities.ScalingUtilities;
import com.example.dindin.utilities.Utilities;
import com.facebook.internal.Utility;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * handle interaction events.
 * Use the {@link FindMatches#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindMatches extends Fragment implements View.OnClickListener{
        // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RelativeLayout swipeviewlayout;
    private RelativeLayout noMatchFound;
    private ImageView userProfilImage, amimagetedview;
    private TextView messagetextview;
    private Button matchedUserInfoButton;
    private int[] matchUserHeightAndWidth;
    private int[] topMarginForInvitelayoutAndText;
    private int[] profileImageHeightAndWidth;

    private int[] imageLayoutHeightandWidth;
    private String machedUserFaceBookid;
    private Button likeButton, dislikeButton;
    private RelativeLayout /* imagevewsecondlayout, */likedislikelayout;
    private RelativeLayout invitebuttonlayout;
    private RelativeLayout findingpeopletextlayout;
    private boolean downloadcallfirsttime = true;
    private int imageindex = 0;
    private int MatchCount;
    private int numberOfImageDownload = 3;
    private Button inviteButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPreferences preferences;
    private Animation anime;


    public FindMatches() {
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
    public static FindMatches newInstance(String param1, String param2) {
        FindMatches fragment = new FindMatches();
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
        findingpeopletextlayout = (RelativeLayout) view
                .findViewById(R.id.findingpeopletextlayout);



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
        findingpeopletextlayout.setLayoutParams(findPeoplelayoutParams);

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

            //findMatches(); TODO here
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



        @Override
        protected Void doInBackground(String... params){

            try {


            } catch (Exception e) {

            }
            return null;
        }

    }



    @Override
    public void onClick(View v) {

        };

    }





