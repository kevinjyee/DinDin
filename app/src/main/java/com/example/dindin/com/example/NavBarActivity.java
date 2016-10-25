
        package com.example.dindin.com.example;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.NavigationView;
        import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

import com.example.dindin.MessageActivity;
import com.example.dindin.PreferencesFragment;
import com.example.dindin.ProfileFragment;
import com.example.dindin.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;



        public class NavBarActivity extends AppCompatActivity
                implements NavigationView.OnNavigationItemSelectedListener {
            JSONObject response, profile_pic_data, profile_pic_url;
            TextView user_name, user_email;
            ImageView user_picture;
            NavigationView navigation_view;
            /**
             * ATTENTION: This was auto-generated to implement the App Indexing API.
             * See https://g.co/AppIndexing/AndroidStudio for more information.
             */
            private GoogleApiClient client;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                Intent intent = getIntent();
                String jsondata = intent.getStringExtra("jsondata");
                setNavigationHeader();    // call setNavigationHeader Method.
                setUserProfile(jsondata);  // call setUserProfile Method.
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();
                navigation_view.setNavigationItemSelectedListener(this);
                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
            }


            /*
                Set Navigation header by using Layout Inflater.
             */
            public void setNavigationHeader() {
                navigation_view = (NavigationView) findViewById(R.id.nav_view);
                //View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
                View header = navigation_view.getHeaderView(0);
                // navigation_view.addHeaderView(header);
                user_name = (TextView) header.findViewById(R.id.username);
                user_picture = (ImageView) header.findViewById(R.id.profile_pic);
                user_email = (TextView) header.findViewById(R.id.email);
            }

            /*
               Set User Profile Information in Navigation Bar.
             */
            public void setUserProfile(String jsondata) {
                try {
                    response = new JSONObject(jsondata);
                    user_email.setText(response.get("email").toString());
                    user_name.setText(response.get("name").toString());
                    profile_pic_data = new JSONObject(response.get("picture").toString());
                    profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
                    Picasso.with(this).load(profile_pic_url.getString("url"))
                            .into(user_picture);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.main, menu);

                // Define the listener
                MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when action item collapses
                        return true;  // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true;  // Return true to expand action view
                    }
                };
                // Get the MenuItem for the action item
                MenuItem actionMenuItem = menu.findItem(R.id.message_select);

                // Assign the listener to that action item
                MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);
               // menu.findItem(R.id.message_select).setActionView(rightmenu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();
                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                } else if (id == R.id.message_select) {
                    Intent intent = new Intent(NavBarActivity.this,MessageActivity.class);
                    NavBarActivity.this.startActivity(intent);
                }
                return super.onOptionsItemSelected(item);
            }

            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                displayView(item.getItemId());
                return true;
            }

            public void displayView(int viewId) {
                Fragment fragment = null;
                String title = getString(R.string.app_name);

                switch (viewId) {
                    case R.id.nav_profile:
                        fragment = new ProfileFragment();
                        title = "Profile";
                        break;
                    case R.id.nav_manage:
                        fragment = new PreferencesFragment();
                        title = "Preferences";
                        break;

                }

                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }

                // set the toolbar title
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(title);
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }

            /**
             * ATTENTION: This was auto-generated to implement the App Indexing API.
             * See https://g.co/AppIndexing/AndroidStudio for more information.
             */
            public Action getIndexApiAction() {
                Thing object = new Thing.Builder()
                        .setName("NavBar Page") // TODO: Define a title for the content shown.
                        // TODO: Make sure this auto-generated URL is correct.
                        .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                        .build();
                return new Action.Builder(Action.TYPE_VIEW)
                        .setObject(object)
                        .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                        .build();
            }

            @Override
            public void onStart() {
                super.onStart();

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                client.connect();
                AppIndex.AppIndexApi.start(client, getIndexApiAction());
            }

            @Override
            public void onStop() {
                super.onStop();

                // ATTENTION: This was auto-generated to implement the App Indexing API.
                // See https://g.co/AppIndexing/AndroidStudio for more information.
                AppIndex.AppIndexApi.end(client, getIndexApiAction());
                client.disconnect();
            }
        }
