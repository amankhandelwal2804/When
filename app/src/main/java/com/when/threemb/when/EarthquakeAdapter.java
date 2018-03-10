package com.when.threemb.when;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 8/21/2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    TextView attendanceView,subjectView,teacherView,startTimeView,endTimeView,statusView;
    int res=0;

    public EarthquakeAdapter(Context context, int resource, List<Earthquake> objects) {
        super(context, resource, objects);
        res=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.timetable_list_item,parent,false);
        }
        Earthquake currentEarthquake=getItem(position);



        attendanceView=(TextView)listItemView.findViewById(R.id.magnitude);
        attendanceView.setText(currentEarthquake.getmAttendance());

        statusView=(TextView)listItemView.findViewById(R.id.status);
        statusView.setText(currentEarthquake.getmStatus());

        subjectView=(TextView)listItemView.findViewById(R.id.Plocation);
        subjectView.setText(currentEarthquake.getmSubject());

        teacherView=(TextView)listItemView.findViewById(R.id.Slocation);
        teacherView.setText(currentEarthquake.getmTeacher());

        startTimeView=(TextView)listItemView.findViewById(R.id.date);
        startTimeView.setText(currentEarthquake.getmStart());

        endTimeView=(TextView)listItemView.findViewById(R.id.time);
        endTimeView.setText(currentEarthquake.getmEnd());

        GradientDrawable attendanceCircle = (GradientDrawable) attendanceView.getBackground();

        // Get the appropriate background color based on the current earthquake attendance
        int attendanceColor = getAttendanceColor(currentEarthquake.getmAttendance());

        // Set the color on the attendance circle
        attendanceCircle.setColor(attendanceColor);



        //For STATUS

        GradientDrawable checkCircle = (GradientDrawable) statusView.getBackground();

        // Get the appropriate background color based on the current earthquake attendance
        int checkColor = getCheckColor(currentEarthquake.getmStatus());

        // Set the color on the attendance circle
        checkCircle.setColor(checkColor);
        if(res==1)
        {
            statusView.setVisibility(View.INVISIBLE);
            attendanceView.setVisibility(View.GONE);
        }
        return listItemView;
    }

    private int getCheckColor(String s) {
        int magnitudeColorResourceId;

        switch (Integer.parseInt(s)) {
            case 1:
                magnitudeColorResourceId = R.color.green;//attend mag2
                break;
            case 2:
                magnitudeColorResourceId = R.color.colorAccent;//dismiss
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude9;//bunk
                break;
            default:
                magnitudeColorResourceId = R.color.white;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private int getAttendanceColor(String s) {
        int magnitude=Integer.parseInt(s);
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)(magnitude/10);
        switch (magnitudeFloor) {
            case 10:magnitudeColorResourceId = R.color.magnitude10my;
                break;
            case 9:magnitudeColorResourceId = R.color.magnitude9my;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 1:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 0:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
