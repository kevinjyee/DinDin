package com.example.dindin;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindMatches.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindMatches#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindMatches extends Fragment{
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
    private SharedPreferences preferences;
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


        invitebuttonlayout = (RelativeLayout) view
                .findViewById(R.id.invitebuttonlayout);
        findingpeopletextlayout = (RelativeLayout) view
                .findViewById(R.id.findingpeopletextlayout);
        inviteButton = (Button) view.findViewById(R.id.inviteButton);

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

       // matchedUserInfoButton.setOnClickListener(this);
       // likeButton.setOnClickListener(this);
        //dislikeButton.setOnClickListener(this);
        //inviteButton.setOnClickListener(this);
        return view;
    }



}
