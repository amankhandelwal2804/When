package com.when.threemb.when;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rohit Agarwal on 25-08-2016.
 */
public class Register extends Activity implements AdapterView.OnItemSelectedListener {
    SharedPreferences sharedpreferences;
    Button save;
    EditText eName;
    String sem="",dept="",group="";
    Spinner spinner,spinner1,spinner2;
    public static String Name = "nameKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toast.makeText(this, "If the app closes or you get a file not found error :\n Its because your timetable hasn't been fed into the app \n Inconvenience is regretted ", Toast.LENGTH_LONG).show();
        TextView tx1 = (TextView)findViewById(R.id.textView11);
        TextView tx2 = (TextView)findViewById(R.id.textView22);
        TextView tx3 = (TextView)findViewById(R.id.textView33);
        /*TextView tx4 = (TextView)findViewById(R.id.textView3);
        TextView tx5 = (TextView)findViewById(R.id.textView2);*/

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/FFF_Tusj.ttf");

        tx1.setTypeface(custom_font);
        tx2.setTypeface(custom_font);
        tx3.setTypeface(custom_font);

        spinner = (Spinner) findViewById(R.id.dept_spinner);
        spinner1 = (Spinner) findViewById(R.id.sem_spinner);
        spinner2 = (Spinner) findViewById(R.id.grp_spinner);
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);

        save=(Button)findViewById(R.id.bSave);
        eName=(EditText)findViewById(R.id.etName);
        final Intent intent =new Intent(this,MainActivity.class);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name=eName.getText().toString();
                if(Name!=null && Name!="" && sem!=null && sem!="" && dept!=null && dept!="" && group!=null && group!="" && Name.length()>2) {

                    double yr=Double.parseDouble(sem);
                    yr/=2;
                    yr=Math.ceil(yr);
                    String semester="0"; //Integer.toString(Integer.parseInt(sem)%2);
                    String year=Integer.toString((int)yr);
                    String filename1=dept+"_"+year+semester+"0";
                    String filename2=dept+"_"+year+semester+group;

                    sharedpreferences =getSharedPreferences("userinfo",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("name", Name);
                    editor.putString("sem", sem);
                    editor.putString("dept", dept);
                    editor.putString("filename1",filename1);
                    editor.putString("filename2",filename2);
                    editor.putBoolean("status",true);
                    editor.apply();
                    //Toast.makeText(Register.this, "Thanks"+filename1+filename2, Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }
                else
                    Toast.makeText(Register.this, "Dude! You gotta fill all the fields", Toast.LENGTH_LONG).show();
            }
        });
                            // Create an ArrayAdapter using the string array and a default spinner layout
        sharedpreferences = getSharedPreferences("Secret", Context.MODE_PRIVATE);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dept_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Sem, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Grp, android.R.layout.simple_spinner_item);
                            // Specify the layout to use when the list of choices appears

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()==R.id.dept_spinner)
            dept=spinner.getItemAtPosition(i).toString().toLowerCase();
        if(adapterView.getId()==R.id.grp_spinner)
            group=spinner2.getItemAtPosition(i).toString();
       if(adapterView.getId()==R.id.sem_spinner)
            sem=spinner1.getItemAtPosition(i).toString();

        //Toast.makeText(Register.this,sem+group+dept, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        sem="";
        group="";
        dept="";
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
