package com.when.threemb.when;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 1/18/2017.
 */
public class VotingAdapter extends ArrayAdapter<EachVote> {
    TextView tvyescount,tvnocount,motion;
    Button y,n;
    EachVote currentVote;
    View listItemView;


    public VotingAdapter(Context context, int resource, List<EachVote> objects) {
        super(context, resource, objects);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        listItemView=convertView;
        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.vote_item,parent,false);
        }
        currentVote=getItem(position);

        motion=(TextView)listItemView.findViewById(R.id.motionX);
        tvyescount=(TextView)listItemView.findViewById(R.id.yescount);
        tvnocount=(TextView)listItemView.findViewById(R.id.nocount);
        tvyescount.setText(""+currentVote.getYescount());
        tvnocount.setText(""+currentVote.getNocount());
        motion.setText(currentVote.getMotion());
        y=(Button)listItemView.findViewById(R.id.yes);
        n=(Button)listItemView.findViewById(R.id.no);

        if(currentVote.getAnswer()!=(-1))
        {
            y.setVisibility(View.INVISIBLE);
            n.setVisibility(View.INVISIBLE);
        }
        else
        {
            y.setVisibility(View.VISIBLE);
            n.setVisibility(View.VISIBLE);
        }


        return listItemView;
    }
    /*public void yesclick(View view)
    {


    }
    public void noclick(View view)
    {


    }*/


}
