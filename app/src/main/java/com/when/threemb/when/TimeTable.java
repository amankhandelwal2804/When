package com.when.threemb.when;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TimeTable extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String subname="",x="",y="";
    String subject="";
    String multattend="";
    SharedPreferences sp,spt,check;
    SharedPreferences.Editor editor,editort,checkeditor;
    int a,b,pos=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent intent1=new Intent(this,SeeAnnouncement.class);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "An initiative of Team 3MB", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //RATE US ON PLAYSTORE


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TimeTable.this);
                alertDialogBuilder.setTitle("Love our App ?");
                alertDialogBuilder.setMessage("Rate us on the Playstore");


                alertDialogBuilder.setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("market://details?id=" + TimeTable.this.getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + TimeTable.this.getPackageName())));
                        }
                    }
                });


                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),"Maybe Later...", Toast.LENGTH_LONG).show();
                        //volley2(input.getText().toString());

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



                //Toast.makeText(TimeTable.this, "Rate us on Playstore", Toast.LENGTH_SHORT).show();


                //startActivity(intent1);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        firstFunction();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            MainActivity.mainwa.finish();
            finish();

        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.time_table, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent,inn,preq,vote,aboutus;
        intent=new Intent(this,OtherDays.class);
        inn=new Intent(this,SeeAnnouncement.class);
        preq=new Intent(this,PostRequest.class);
        vote=new Intent(this,Main2Activity.class);
        aboutus=new Intent(this,AboutUs.class);


        if (id == R.id.nav_camera) {
            // Handle the attendance action
            intent=new Intent(this,Attendance.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            // Handle the monday action

            startActivity(intent);

        } /*else if (id == R.id.nav_slideshow) {
            // Handle the tuesday action
            intent.putExtra("Day",2);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            // Handle the wednesday action
            intent.putExtra("Day",3);
            startActivity(intent);
        } else if (id == R.id.nav_manage2) {
            // Handle the thursday action
            intent.putExtra("Day",4);
            startActivity(intent);
        }else if (id == R.id.nav_manage3) {
            // Handle the friday action
            startNotify(this);
            intent.putExtra("Day",5);
            startActivity(intent);
        }*/else if (id == R.id.nav_share) {
            //Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            startActivity(preq);

        } else if (id == R.id.nav_send) {
            //Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();

            startActivity(inn);

        }

        else if (id == R.id.nav_vote) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();

            startActivity(vote);

        }
        else if (id == R.id.nav_aboutus) {
            Toast.makeText(this, " We are Team 3MB ", Toast.LENGTH_SHORT).show();

            startActivity(aboutus);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    EarthquakeAdapter adapter;
    ListView earthquakeListView;
    public void firstFunction() {

            SharedPreferences asp = getSharedPreferences("attended", Context.MODE_PRIVATE);
            SharedPreferences aspt = getSharedPreferences("totalclass", Context.MODE_PRIVATE);
            //SharedPreferences acheck = getSharedPreferences("kyanaamdu", Context.MODE_PRIVATE);

        /*************************************/


        MainActivity obj = new MainActivity();
        String day[] = obj.getDay();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int dow = (calendar.get(Calendar.DAY_OF_WEEK)) - 1;

        int mult = 1;
        multattend = "";

        subname = "" + day[dow].charAt(0);
        for (int i = 1; i < 10; i++) {
            if (day[dow].charAt(i - 1) != day[dow].charAt(i)) {
                subname += day[dow].charAt(i);
                multattend += Integer.toString(mult);
                mult = 1;
            } else {
                mult++;
            }
        }
        if (subname.length() != multattend.length())
            multattend += Integer.toString(mult);

        /*****************************************************/

        check = getSharedPreferences("kyanaamdu", Context.MODE_PRIVATE);
        checkeditor = check.edit();
        x = check.getString("status", "aaaaa");
        y = check.getString("settings", "00000");
        String datestring=check.getString("date","00000");
        if (x.equals(subname) == false && datestring.equals(now.toString())==false) {
            x = subname;
            y = "";
            for (int i = 0; i < x.length(); i++) {
                y += "0";
            }
            checkeditor.putString("status", x);
            checkeditor.putString("settings", y);
            checkeditor.putString("date",now.toString());
            checkeditor.apply();

        }

        /******************************************************/
            final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(asp, aspt, check, -1);//new ArrayList<>();

        /*earthquakes.add(new Earthquake("7.2","San Fransisco","Feb 2,2016"));
        earthquakes.add(new Earthquake("5.2","San","Feb 2,2016"));
        earthquakes.add(new Earthquake("4.8","San Fransisco","Dec 2,2010"));
        earthquakes.add(new Earthquake("8.2","Fransisco","Feb 2,2016"));
        earthquakes.add(new Earthquake("5.2","San Fransisco","Feb 2,2016"));
        earthquakes.add(new Earthquake("6.2","San Fransisco","Feb 2,2016"));*/

        /*
        * THE BASIC ARRAY ADAPTER
        ListView earthquakeListView;
        final ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("Hello World");
        earthquakeListView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, earthquakes);
        earthquakeListView.setAdapter(adapter);

        * */


            // Find a reference to the {@link ListView} in the layout
            earthquakeListView = (ListView) findViewById(R.id.list);

            // Create a new {@link ArrayAdapter} of earthquakes
            adapter = new EarthquakeAdapter(this, 0, earthquakes);
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            //this, android.R.layout.simple_list_item_1, earthquakes);
            //Toast.makeText(TimeTable.this, "This is done", Toast.LENGTH_SHORT).show();
            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(adapter);

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {
                    //Toast.makeText(TimeTable.this,Integer.toString(position), Toast.LENGTH_SHORT).show();


                    //Toast.makeText(TimeTable.this,x+y, Toast.LENGTH_SHORT).show();
                    if (y.charAt(position) == '0' && subname.charAt(position)!='a') {


                        subject = "" + subname.charAt(position);

                        sp = getSharedPreferences("attended", Context.MODE_PRIVATE);
                        editor = sp.edit();

                        spt = getSharedPreferences("totalclass", Context.MODE_PRIVATE);
                        editort = spt.edit();

                        check = getSharedPreferences("kyanaamdu", Context.MODE_PRIVATE);
                        checkeditor = check.edit();

                        a = sp.getInt(subject, 0);
                        b = spt.getInt(subject, 0);
                        pos = position;


                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TimeTable.this);
                        alertDialogBuilder.setMessage("What did you do?");

                        alertDialogBuilder.setNegativeButton("Attend", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                //Toast.makeText(getBaseContext(),"You clicked ATTEND button", Toast.LENGTH_LONG).show();
                                a += (int) multattend.charAt(pos) - 48;
                                b += (int) multattend.charAt(pos) - 48;
                                editor.putInt(subject, a);
                                editort.putInt(subject, b);
                                editor.apply();
                                editort.apply();
                                y = y.substring(0, pos) + '1' + y.substring(pos + 1, y.length());


                                checkeditor.putString("settings", y);
                                checkeditor.apply();
                                final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(sp, spt, check, -1);
                                EarthquakeAdapter adapter = new EarthquakeAdapter(TimeTable.this, 0, earthquakes);
                                earthquakeListView.setAdapter(adapter);
                                //Toast.makeText(getBaseContext(), y+"Attended" + Integer.toString(a) + "Total" + Integer.toString(b), Toast.LENGTH_LONG).show();
                            }
                        });

                        alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getBaseContext(),"You clicked DISMISS button", Toast.LENGTH_LONG).show();
                                editor.putInt(subject, a);
                                editort.putInt(subject, b);
                                editor.apply();
                                editort.apply();
                                y = y.substring(0, pos) + '2' + y.substring(pos + 1, y.length());

                                checkeditor.putString("settings", y);
                                checkeditor.apply();
                                final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(sp, spt, check, -1);
                                EarthquakeAdapter adapter = new EarthquakeAdapter(TimeTable.this, 0, earthquakes);
                                earthquakeListView.setAdapter(adapter);
                                //Toast.makeText(getBaseContext(), y+"Attended" + Integer.toString(a) + "Total" + Integer.toString(b), Toast.LENGTH_LONG).show();
                            }
                        });

                        alertDialogBuilder.setPositiveButton("BUNK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(getBaseContext(),"You clicked BUNK button", Toast.LENGTH_LONG).show();
                                b += (int) multattend.charAt(pos) - 48;
                                editor.putInt(subject, a);
                                editort.putInt(subject, b);
                                editor.apply();
                                editort.apply();
                                y = y.substring(0, pos) + '3' + y.substring(pos + 1, y.length());

                                checkeditor.putString("settings", y);
                                checkeditor.apply();
                                final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes(sp, spt, check, -1);
                                EarthquakeAdapter adapter = new EarthquakeAdapter(TimeTable.this, 0, earthquakes);
                                earthquakeListView.setAdapter(adapter);
                                //Toast.makeText(getBaseContext(), "Attended" + Integer.toString(a) + "Total" + Integer.toString(b), Toast.LENGTH_LONG).show();
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    } else
                        Toast.makeText(TimeTable.this, "Already Marked !", Toast.LENGTH_SHORT).show();

                }
            });

        }

    /*public void startNotify(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        //Intent intent1 = new Intent(context, menu.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        //PendingIntent pIntent1 = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent1, 0);


        Notification noti = new Notification.Builder(context)
                .setContentTitle("FREE")
                .setSmallIcon(R.drawable.ic_menu_share)
                .setContentText("UPNEXT Lab/DIG-ELECTRONICS")
                .setContentIntent(pIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                //.addAction(R.drawable.ic_menu_send, "Turn OFF Notification", pIntent1)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, noti);
    }*/
    }





