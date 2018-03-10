package com.when.threemb.when;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    static String day[]=new String[10];
    static String sub[]=new String[52];
    int time[]={900,955,1050,1145,1225,1320,1415,1510,1605,1700,1755};
    //TextView tv;
    public static String localauth="";
    String filename = "it_210";
    String fname="it_211";
    public static Activity mainwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*ImageView b=(ImageView)findViewById(R.id.imageView2);
        b.setImageResource(R.drawable.logo);
        Thread timer=new Thread(){
            public void run(){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent i=new Intent("android.intent.action.PROCESS");
                    startActivity(i);
                }
            }
        };
        timer.start();*/

        //temporary part
        //Intent innt=new Intent(this,PostRequest.class);
        //startActivity(innt);
        //end of temporary part
        mainwa=this;
        //tv=(TextView)findViewById(R.id.tv);
        //Intent in =new Intent(this,Register.class);
        //startActivity(in);
        SharedPreferences sp=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        Boolean status=sp.getBoolean("status",false);
        if(status)
        {
            filename=sp.getString("filename1","it_210");
            fname=sp.getString("filename2","it_211");
            localauth=filename;
            dayarray();
            subarray();
            Intent intent =new Intent(this,TimeTable.class);
            startActivity(intent);
        }
        else
        {
            Intent intent =new Intent(this,Register.class);
            startActivity(intent);
        }



        //displayTT();

    }



    public void dayarray()
    {

        String collected="";

        try {
           /* fis=openFileInput(fname);
            byte [] data = new byte[fis.available()];
            while(fis.read(data) != -1) {
                collected=new String(data);}*/
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier(fname,
                            "raw", getPackageName()));
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);
            collected = new String(buffer);

            day[0]="aaaaaaaaaa";
            day[1]=collected.substring(0,10);
            day[2]=collected.substring(10,20);
            day[3]=collected.substring(20,30);
            day[4]=collected.substring(30,40);
            day[5]=collected.substring(40,50);
            day[6]="aaaaaaaaaa";
            day[7]="aaaaaaaaaa";
            day[8]="aaaaaaaaaa";
            day[9]="aaaaaaaaaa";

        }catch(FileNotFoundException x)
        {
            Toast.makeText(MainActivity.this, "File not found :(", Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, "File not found :(", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }
    }


    public void subarray()
    {
        int i,a,c=0;
        String collected="";

        try {
            /*fis=openFileInput(filename);
            byte [] data = new byte[fis.available()];
            while(fis.read(data) != -1) {
                collected=new String(data);}*/
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier(filename,
                            "raw", getPackageName()));
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);
            collected = new String(buffer);


            for(a=0,i=0;i<collected.length();i++)
            {
                if(collected.charAt(i)=='\n'){
                    sub[c++]=collected.substring(a,i);
                    a=i+1;
                }

            }
            //fis.close();
        }catch(FileNotFoundException x)
        {
            Toast.makeText(MainActivity.this, "File not found :(", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "File not found :(", Toast.LENGTH_SHORT).show();
            finish();
            e.printStackTrace();
        }

    }

    public void displayTT()
    {
        String collected="";
        int start,end=900,t=1,d=0;
        for(int i=0;i<10;i++)
        {
            start=time[d];
            if(i<9)
            {
                if(day[1].charAt(i)==day[1].charAt(i+1)){
                    t++;
                    end=time[t];continue;}
            }

            end=time[t];
            d=t;
            t++;
            collected+=Integer.toString(start/100)+":"+Integer.toString(start%100)+" - "+Integer.toString(end/100)+":"+Integer.toString(end%100)+"  ";

            collected+=sub[(int)(day[1].charAt(i))-97];
            collected+='\n';

        }
        //tv.setText(collected);
    }

    public String[] getDay() {
        return day;
    }

    public String[] getSub() {
        return sub;
    }

    public int[] getTime() {
        return time;
    }
}
