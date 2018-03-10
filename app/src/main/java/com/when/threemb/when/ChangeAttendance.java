package com.when.threemb.when;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeAttendance extends AppCompatActivity {

    EditText et1,et2;
    Button change,goback;
    char ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_attendance);
        // BACK BUTTON
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        Bundle kuchbhi=getIntent().getExtras();
        if(kuchbhi==null) {
            Toast.makeText(this, "Intercepted some problem !", Toast.LENGTH_SHORT).show();
            finish();
        }
        ch=kuchbhi.getChar("Attendance");
    }

    public void changetask(View view)
    {
        //Toast.makeText(this,""+ch,Toast.LENGTH_SHORT).show();
        int attended=Integer.parseInt(et1.getText().toString());
        int total=Integer.parseInt(et2.getText().toString());
        //Toast.makeText(this,""+ch+"|"+total+attended,Toast.LENGTH_SHORT).show();
        if(attended>total)
            Toast.makeText(this,"Attended cannot be more than total",Toast.LENGTH_SHORT).show();
        else
        {
            String subject=""+ch;
            //Toast.makeText(this,subject,Toast.LENGTH_SHORT).show();
            SharedPreferences asp = getSharedPreferences("attended", Context.MODE_PRIVATE);
            SharedPreferences aspt = getSharedPreferences("totalclass", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = asp.edit();
            SharedPreferences.Editor editort = aspt.edit();
            editor.putInt(subject, attended);
            editort.putInt(subject, total);
            editor.apply();
            editort.apply();
            finish();


        }

    }
    public void gobacktask(View view)
    {
        finish();
    }
}
