package com.when.threemb.when;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        //Toolbar tool=(Toolbar)findViewById(R.id.toolbar);
        //final Intent i=new Intent(this,TimeTable.class);
        // BACK BUTTON
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Attendance");
        firstfn();


    }

    public void firstfn() {
        EarthquakeAdapter adapter;
        ListView earthquakeListView;

        SharedPreferences asp = getSharedPreferences("attended", Context.MODE_PRIVATE);
        SharedPreferences aspt = getSharedPreferences("totalclass", Context.MODE_PRIVATE);
        //SharedPreferences acheck=getSharedPreferences("kyanaamdu", Context.MODE_PRIVATE);
        ArrayList<Earthquake> earthquakes = AttendanceUtils.extractEarthquakes(asp,aspt);//new ArrayList<>();
        /*earthquakes.add(new Earthquake("7.2","San Fransisco","Feb 2,2016"));
        earthquakes.add(new Earthquake("5.2","San","Feb 2,2016"));
        earthquakes.add(new Earthquake("4.8","San Fransisco","Dec 2,2010"));
        earthquakes.add(new Earthquake("8.2","Fransisco","Feb 2,2016"));
        earthquakes.add(new Earthquake("5.2","San Fransisco","Feb 2,2016"));
        earthquakes.add(new Earthquake("6.2","San Fransisco","Feb 2,2016"));*/
        earthquakes.remove(0);

        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter=new EarthquakeAdapter(this,3,earthquakes);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        //this, android.R.layout.simple_list_item_1, earthquakes);
        //Toast.makeText(TimeTable.this, "This is done", Toast.LENGTH_SHORT).show();
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface

        final Intent intt=new Intent(this,ChangeAttendance.class);
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                char ch=(char)(position+98);
                intt.putExtra("Attendance",ch);
                startActivity(intt);
            }



        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        firstfn();

    }
}
