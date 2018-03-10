package com.when.threemb.when;

/**
 * Created by User on 9/24/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Message;
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
public class AnnouncementAdapter extends ArrayAdapter<MessageObj> {
    TextView usernametv, messagetv;
    int res = 0;

    public AnnouncementAdapter(Context context, int resource, List<MessageObj> objects) {
        super(context, resource, objects);
        res = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.announcement_list_item, parent, false);
        }
        MessageObj obj = getItem(position);

        usernametv = (TextView) listItemView.findViewById(R.id.usernametv);
        usernametv.setText(obj.getUsername());
        messagetv = (TextView) listItemView.findViewById(R.id.messagetv);
        messagetv.setText(obj.getMessage());

        return listItemView;
    }
}