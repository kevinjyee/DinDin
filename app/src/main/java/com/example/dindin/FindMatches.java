package com.example.dindin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.example.dindin.utilities.Utilities;

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
        View view = inflater.inflate(R.layout.fragment_layout1, null);

        preferences = PreferenceManager.getDefaultSharedPreferences(inflater.getContext());

        findingpeopletextlayout = (RelativeLayout) view
                .findViewById(R.id.findingpeopletextlayout);


        invitebuttonlayout.setVisibility(View.GONE);

        swipeviewlayout = (RelativeLayout) view
                .findViewById(R.id.swipeviewlayout);
        userProfilImage = (ImageView) view.findViewById(R.id.userProfilImage);
        messagetextview = (TextView) view.findViewById(R.id.messagetextview);
        amimagetedview = (ImageView) view.findViewById(R.id.circleimageview);
        noMatchFound = (RelativeLayout) view.findViewById(R.id.noMatchFound);

        matchedUserInfoButton = (Button) view
                .findViewById(R.id.matchedUserInfoButton);
        likeButton = (Button) view.findViewById(R.id.likeButton);
        dislikeButton = (Button) view.findViewById(R.id.dislikeButton);
        likedislikelayout = (RelativeLayout) view
                .findViewById(R.id.likedislikelayout);

        matchedUserInfoButton.setOnClickListener(this);
        likeButton.setOnClickListener(this);
        dislikeButton.setOnClickListener(this);
        inviteButton.setOnClickListener(this);

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
        invitlayoutParams.setMargins(0, topMarginForInvitelayoutAndText[1], 0,
                0);
        invitebuttonlayout.setLayoutParams(invitlayoutParams);

//        ScreenSize screenSize = new ScreenSize(getActivity());
//        // Log.e(TAG,
//        // "his : "
//        // + getActivity().getWindowManager().getDefaultDisplay()
//        // .getWidth() + "my : "
//        // + screenSize.getScreenWidthPixel());
//        windowwidth = (int) screenSize.getScreenWidthPixel();
//        screenCenter = windowwidth / 2;
        RelativeLayout.LayoutParams likedislikeparam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        likedislikeparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        likedislikeparam.setMargins(0, imageLayoutHeightandWidth[3], 0, 0);
        likedislikelayout.setLayoutParams(likedislikeparam);

        try {
//            TODO set profile picture here
        } catch (Exception e) {
            AppLog.handleException(TAG + " onCreateView  Exception ", e);
        }
//        anime = AnimationUtils.loadAnimation(getActivity(), R.anim.zoomin);
//        amimagetedview.startAnimation(anime);



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

    @Override
    public void onClick(View v) {

        };

    }





