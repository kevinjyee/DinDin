package com.example.dindin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dindin.utilities.Constants;
import com.example.dindin.utilities.MessageAdapter;
import com.example.dindin.utilities.Utilities;
import com.google.android.gms.cast.framework.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static android.R.attr.filter;

public class MessageActivity extends Fragment {
    private ListView matcheslistview;
    private View view;
    private FloatingActionButton fab;
    private EditText searchThings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_message, null);

        view = root;

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO: STEFAN DO THIS HERE TOO
            }
        });
        matcheslistview = (ListView) view.findViewById(R.id.menu_right_ListView);
        Utilities currUtils = new Utilities();
        int imageHeightandWidht[] = currUtils.getImageHeightandWidthforMatchView(getActivity());
        HashSet<User> usersHashSet = new HashSet<>();
        usersHashSet.addAll(Constants.usersMatchedwith);
        Constants.usersMatchedwith.clear();
        Constants.usersMatchedwith.addAll(usersHashSet);
        final MatchedDataAdapter adapter = new MatchedDataAdapter(getActivity(), Constants.usersMatchedwith);
        matcheslistview.setAdapter(adapter);
        matcheslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Bundle mBundle = new Bundle();
                String targetID;
                User targetUser = (User) matcheslistview.getAdapter().getItem(position);
                targetID = targetUser.getfbId();

                mBundle.putString("targetID", targetID);
                Intent mIntent = new Intent(getActivity(), MessagingActivity.class);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
            }
        });


        // search
        searchThings = (EditText) view
                .findViewById(R.id.et_serch_right_side_menu);
        searchThings.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


                adapter.getFilter().filter(s.toString().trim());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }


        });

        return root;
    }


    private class MatchedDataAdapter extends
            BaseAdapter implements Filterable {


        private ArrayList<User> listContact;
        private LayoutInflater mInflater;
        // Shared Preferences
        private SharedPreferences pref;
        // Editor for Shared preferences
        private SharedPreferences.Editor editor;


        private ArrayList<User> filteredData;


        public MatchedDataAdapter(Context context,
                                  ArrayList<User> objects
        ) {
            listContact = objects;
            filteredData = objects;
            mInflater = LayoutInflater.from(context);

            pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            editor = pref.edit();

        }


        @Override
        public int getCount() {
            return filteredData.size();
        }

        @Override
        public User getItem(int position) {
            return filteredData.get(position);
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
                holder.lastMasage.setText(pref.getString(getItem(position).getfbId(), null));
            } catch (Exception e) {

            }

            return convertView;

        }

        class ViewHolder {
            ImageView imageview;
            TextView textview;
            TextView lastMasage;

        }


        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults results = new FilterResults();

                    //If there's nothing to filter on, return the original data for your list
                    if (charSequence == null || charSequence.length() == 0) {
                        results.values = listContact;
                        results.count = listContact.size();
                    } else {
                        ArrayList<User> filterResultsData = new ArrayList<User>();

                        for (User data : listContact) {
                            //In this loop, you'll filter through originalData and compare each item to charSequence.
                            //If you find a match, add it to your new ArrayList
                            //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional
                            if (data.getName().contains(charSequence)) {
                                filterResultsData.add(data);
                            }
                        }

                        results.values = filterResultsData;
                        results.count = filterResultsData.size();
                    }

                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    filteredData = (ArrayList<User>) filterResults.values;
                    notifyDataSetChanged();
                }


            };
        }
    }
}