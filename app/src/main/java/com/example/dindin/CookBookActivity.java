package com.example.dindin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dindin.utilities.Constants;
import com.example.dindin.utilities.Utilities;
import com.google.android.gms.cast.framework.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CookBookActivity extends Fragment {
    private ListView matcheslistview;
    private View view;
    private EditText searchThings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_message, null);
        view = root;

        matcheslistview = (ListView) view.findViewById(R.id.menu_right_ListView);
        Utilities currUtils = new Utilities();
        int imageHeightandWidht[] = currUtils.getImageHeightandWidthforMatchView(getActivity());


        final MatchedDataAdapter adapter = new MatchedDataAdapter(getActivity(), Constants.recipsesMatchedwith);
        matcheslistview.setAdapter(adapter);

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


        private ArrayList<Recipe> listContact;
        private ArrayList<Recipe> filteredData;
        private LayoutInflater mInflater;


        public MatchedDataAdapter(Context context,
                                  ArrayList<Recipe> objects
        ) {
            listContact = objects;
            filteredData = objects;
            mInflater = LayoutInflater.from(context);

        }


        @Override
        public int getCount() {
            return filteredData.size();
        }

        @Override
        public Recipe getItem(int position) {
            return filteredData.get(position);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
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
            holder.textview.setText(getItem(position).getFoodName());

            Picasso.with(convertView.getContext())
                    .load(getItem(position).getImage_url())
                    .into(holder.imageview);
            try {
                holder.lastMasage.setText("Click for Recipe!");
            } catch (Exception e) {

            }

            holder.imageview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int[] location = new int[2];
                    int currentRowId = position;
                    View currentRow = v;
                    // Get the x, y location and store it in the location[] array
                    // location[0] = x, location[1] = y.
                    v.getLocationOnScreen(location);

                    //Initialize the Point with x, and y positions
                    Point point = new Point();
                    point.x = location[0];
                    point.y = location[1];
                    showStatusPopup(getActivity(), point, getItem(position));
                }
            });


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
                        ArrayList<Recipe> filterResultsData = new ArrayList<Recipe>();

                        for (Recipe data : listContact) {
                            //In this loop, you'll filter through originalData and compare each item to charSequence.
                            //If you find a match, add it to your new ArrayList
                            //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional
                            if (data.getFoodName().contains(charSequence)) {
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
                    filteredData = (ArrayList<Recipe>) filterResults.values;
                    notifyDataSetChanged();
                }


            };
        }

        private void showStatusPopup(final Activity context, Point p, Recipe currentRecipe) {

            // Inflate the popup_layout.xml
            LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.recipe_view);
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.recipe_list, null);

            TextView recipeText = (TextView) layout.findViewById(R.id.recipes);
            recipeText.setText(currentRecipe.getRecipe());
            // Creating the PopupWindow
            PopupWindow changeStatusPopUp = new PopupWindow(context);
            changeStatusPopUp.setContentView(layout);
            changeStatusPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            changeStatusPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            changeStatusPopUp.setFocusable(true);

            // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
            int OFFSET_X = -20;
            int OFFSET_Y = 50;

            //Clear the default translucent background
            ColorDrawable colorDrawable = new ColorDrawable();
            colorDrawable.setColor(0xffFEBB31);
            changeStatusPopUp.setBackgroundDrawable(colorDrawable);

            // Displaying the popup at the specified location, + offsets.
            changeStatusPopUp.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
        }


    }
}
