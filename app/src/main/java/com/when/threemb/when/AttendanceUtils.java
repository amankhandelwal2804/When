package com.when.threemb.when;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 9/14/2016.
 */
public final class AttendanceUtils {


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private AttendanceUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(SharedPreferences sp, SharedPreferences spt) {

        // Create an empty ArrayList that we can start adding earthquakes to


        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        MainActivity obj = new MainActivity();
        String day[]=obj.getDay();
        String sub[]=obj.getSub();
        int time[]=obj.getTime();
        String attendance="AMAN",subject="AMAN",startTime="AMAN",endTime="AMAN",teacher="AMAN",checkString="AMAN";
        int start=900,end=900,t=1,d=0,counter=0;
        char ch='a';
        //checkString=check.getString("settings","0");
        /*subname=""+day[dow].charAt(0);
        for(int i=1;i<10;i++)
        {
            if(day[dow].charAt(i-1)!=day[dow].charAt(i))
                subname+=day[dow].charAt(i);
        }
        subcode = "" + subname.charAt(position);
        int a = sp.getInt(subcode, 0);
        int b = spt.getInt(subcode, 0);*/

        for(int i=0;sub[i]!=null && sub[i]!="";i++,ch=(char)((int)ch+1)) {
            /*start = time[d];
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

            ch=day[dow].charAt(i);*/
            int a = sp.getInt(ch+"", 0);//attended
            int b = spt.getInt(ch+"", 0);//total
            if(b!=0)
                attendance = Integer.toString(a*100/b);
            else
                attendance="0";
            teacher = "";
            subject = sub[i];
            String str="0";
            /*if(counter<checkString.length())
            {str=""+checkString.charAt(counter);counter++;}*/
            //if(!subject.equals("FREE"))
            earthquakes.add(new Earthquake(attendance,subject,teacher,"Attended: "+Integer.toString(a),"Total: "+Integer.toString(b),"0"));
            // Return the list of earthquakes
        }
        return earthquakes;
    }

}
