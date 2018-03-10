package com.when.threemb.when;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(SharedPreferences sp,SharedPreferences spt,SharedPreferences check,int dow) {

        // Create an empty ArrayList that we can start adding earthquakes to
        if(dow==-1) {
            Date now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            dow = (calendar.get(Calendar.DAY_OF_WEEK)) - 1;
        }

        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        MainActivity obj = new MainActivity();
        String day[]=obj.getDay();
        String sub[]=obj.getSub();
        int time[]=obj.getTime();
        String attendance="AMAN",subject="AMAN",startTime="AMAN",endTime="AMAN",teacher="AMAN",checkString="AMAN";
        int start=900,end=900,t=1,d=0,counter=0;
        char ch='a';
        checkString=check.getString("settings","00000");
        /*subname=""+day[dow].charAt(0);
        for(int i=1;i<10;i++)
        {
            if(day[dow].charAt(i-1)!=day[dow].charAt(i))
                subname+=day[dow].charAt(i);
        }
        subcode = "" + subname.charAt(position);
        int a = sp.getInt(subcode, 0);
        int b = spt.getInt(subcode, 0);*/
        for(int i=0;i<10;i++) {
            start = time[d];
            if (i < 9) {
                if (day[dow].charAt(i) == day[dow].charAt(i + 1)) {
                    t++;
                    end = time[t];
                    continue;
                }
            }

            end = time[t];
            d = t;
            t++;


            startTime = Integer.toString(start);
            endTime = Integer.toString(end);

            ch=day[dow].charAt(i);
            int a = sp.getInt(ch+"", 0);//attended
            int b = spt.getInt(ch+"", 0);//total
            if(b!=0)
            attendance = Integer.toString(a*100/b);
            else
            attendance="0";
            teacher = " ";
            subject = sub[(int) (day[dow].charAt(i)) - 97];
            String str="0";
            if(counter<checkString.length())
            {str=""+checkString.charAt(counter);counter++;}

            if(startTime.length()==4)
                startTime=startTime.substring(0,2)+":"+startTime.substring(2);
            if(endTime.length()==4)
                endTime=endTime.substring(0,2)+":"+endTime.substring(2);

            if(startTime.length()==3)
                startTime=startTime.charAt(0)+":"+startTime.substring(1);
            if(endTime.length()==3)
                endTime=endTime.charAt(0)+":"+endTime.substring(1);
            earthquakes.add(new Earthquake(attendance,subject,teacher,startTime,endTime,str));
            // Return the list of earthquakes
        }
        return earthquakes;
    }

}
