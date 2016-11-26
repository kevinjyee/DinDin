package com.example.dindin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dindin.utilities.Constants;
import com.example.dindin.utilities.Utilities;
import com.google.android.gms.cast.framework.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MessageActivity extends Fragment {
    private ListView matcheslistview;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            // Inflate the layout for this fragment
            ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_message, null);
            view = root;

            matcheslistview = (ListView) view.findViewById(R.id.menu_right_ListView);
        Utilities  currUtils = new Utilities();
        int imageHeightandWidht[] = currUtils.getImageHeightandWidthforMatchView(getActivity());
        HashSet<User> usersHashSet = new HashSet<>();
        usersHashSet.addAll(Constants.usersMatchedwith);
        Constants.usersMatchedwith.clear();
        Constants.usersMatchedwith.addAll(usersHashSet);
        MatchedDataAdapter adapter = new MatchedDataAdapter(getActivity(), Constants.usersMatchedwith);
        matcheslistview.setAdapter(adapter);

            return root;
        }

    private class MatchedDataAdapter extends
            BaseAdapter {



        private ArrayList<User> listContact;
        private LayoutInflater mInflater;


        public MatchedDataAdapter(Context context,
                                  ArrayList<User> objects
                                  ) {
            listContact = objects;
            mInflater = LayoutInflater.from(context);

        }




        @Override
        public int getCount() {
            return listContact.size();
        }

        @Override
        public User getItem(int position) {
            return listContact.get(position);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.matchedlistview,
                        null);
                holder.imageview = (ImageView) convertView
                        .findViewById(R.id.userimage);
                holder.textview = (TextView) convertView
                        .findViewById(R.id.userName);
                holder.lastMasage = (TextView) convertView
                        .findViewById(R.id.lastmessage);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textview.setId(position);
            holder.imageview.setId(position);
            holder.lastMasage.setId(position);
            holder.textview.setText(getItem(position).getName());

            Picasso.with(convertView.getContext())
                    .load("https://graph.facebook.com/v2.2/" + getItem(position).getfbId() + "/picture?height=120&type=normal")
                    .into(holder.imageview);
            try {
                holder.lastMasage.setText(getItem(position).getPhoneNumber());
            } catch (Exception e) {

            }

            return convertView;
        }

        class ViewHolder {
            ImageView imageview;
            TextView textview;
            TextView lastMasage;

        }
    }



}
