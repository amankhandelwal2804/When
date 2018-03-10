package com.when.threemb.when;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        // BACK BUTTON
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        TextView tx3 = (TextView)findViewById(R.id.toptext);
        /*TextView tx4 = (TextView)findViewById(R.id.textView3);
        TextView tx5 = (TextView)findViewById(R.id.textView2);*/

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/FFF_Tusj.ttf");

        tx3.setTypeface(custom_font);
    }
}
