package com.when.threemb.when;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    ListView motionsLV;
    TextView output;
    VotingAdapter adapter;
    RequestQueue requestQueue;
    String KEY_NAME="";
    String KEY_FILENAME="";
    String urli="https://whencollegebuddy.000webhostapp.com/voteupdate.php?filename="+KEY_FILENAME+"&username="+KEY_FILENAME+"&name="+KEY_NAME+"&";
    DatabaseHandler db=new DatabaseHandler(this);
    FloatingActionButton fab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // BACK BUTTON
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        final int dow = (calendar.get(Calendar.DATE));

        datahandler();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
               /* Intent i=new Intent(Main2Activity.this,DialogActivity.class);
                startActivity(i);*/

                SharedPreferences ss=getSharedPreferences("Voter",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=ss.edit();
                if(ss.getInt("dow",0)!=dow) {
                    ed.putInt("dow", dow);
                    ed.apply();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main2Activity.this);
                    alertDialogBuilder.setTitle("New Arguement");
                    alertDialogBuilder.setMessage("Enter Motion to Vote");

                    final EditText input = new EditText(Main2Activity.this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    alertDialogBuilder.setView(input);

                    alertDialogBuilder.setPositiveButton("UPLOAD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(getBaseContext(),"You clicked "+input.getText().toString(), Toast.LENGTH_LONG).show();
                            volley2(input.getText().toString());

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else
                {
                    Toast.makeText(Main2Activity.this, "You can upload a motion only once per day", Toast.LENGTH_SHORT).show();
                }

            }
        });
        /*Intent i=new Intent(this,AboutUs.class);
        startActivity(i);
*/

        motionsLV=(ListView)findViewById(R.id.motions);
        output = (TextView) findViewById(R.id.empty_view);
        /*adapter=new VotingAdapter(Main2Activity.this,0,motion1);
        motionsLV.setAdapter(adapter);*/

        /*motion1.add(new EachVote(-1,"How You Doin?",1,20,50));
        motion1.add(new EachVote(0,"This was already answered before",2,60,30));
        motion1.add(new EachVote(-1,"whatcha Doin?",3,10,10));
        adapter = new VotingAdapter(this, 0, motion1);
        motionsLV.setAdapter(adapter);*/
        laaDo();
    }

    public void laaDo(/*View view*/)
    {

        //Toast.makeText(Main2Activity.this, "Please Wait !!", Toast.LENGTH_SHORT).show();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            volley(-1,-1);

        } else {
            // display error
            fab.setVisibility(View.INVISIBLE);
            output.setText("NO INTERNET CONNECTIVITY");


        }

    }

    public void yesclick(View view)
    {
        view.setEnabled(false);
        view.setClickable(false);

        View parentRow = (View) view.getParent();
        final int position = motionsLV.getPositionForView(parentRow);


        EachVote currentVote=(EachVote)motionsLV.getItemAtPosition(position);
        db.updateAnswer(currentVote.getMotionNo(),1);
        volley(currentVote.getMotionNo(),1);
        currentVote.setAnswer(1);
        /*
        currentVote.setYescount(currentVote.getYescount()+1);
        adapter.notifyDataSetChanged();*/
        //tvyescount.setText(""+currentVote.getMotionNo());
    }

    public void noclick(View view)
    {
        view.setEnabled(false);
        view.setClickable(false);

        View parentRow = (View) view.getParent();
        final int position = motionsLV.getPositionForView(parentRow);


        EachVote currentVote=(EachVote)motionsLV.getItemAtPosition(position);
        db.updateAnswer(currentVote.getMotionNo(),1);
        volley(currentVote.getMotionNo(),0);
        currentVote.setAnswer(0);
        /*
        currentVote.setNocount(currentVote.getNocount()+1);
        adapter.notifyDataSetChanged();*/
        //tvyescount.setText(""+currentVote.getMotionNo());
    }

    public void volley(int mN,int ans){
        requestQueue = Volley.newRequestQueue(this);
        //Toast.makeText(this, urli, Toast.LENGTH_SHORT).show();
        final ArrayList<EachVote> motion1=new ArrayList<>();
        urli="https://whencollegebuddy.000webhostapp.com/voteupdate.php?filename="+KEY_FILENAME+"&username="+KEY_FILENAME+"&name="+KEY_NAME+"&";
        urli+="motionNo="+mN+"&answer="+ans;
        //Toast.makeText(this, urli, Toast.LENGTH_SHORT).show();
        final JsonObjectRequest kor=new JsonObjectRequest(Request.Method.GET, urli, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject ob) {

                        //Toast.makeText(SeeAnnouncement.this, "fetching response", Toast.LENGTH_SHORT).show();
                        if(ob!=null){
                            JSONArray allu= ob.optJSONArray("allu");
                            //data="";
                            output.setText("");
                            for(int i=allu.length()-1;i>-1;i--)
                            {
                                JSONObject alluobj=allu.optJSONObject(i);
                                String motion=alluobj.optString("motion");
                                int motionNo=alluobj.optInt("motionNo");
                                int nocount=alluobj.optInt("nocount");
                                int yescount=alluobj.optInt("yescount");//TODO display name of user
                                int answer=db.getVote(motionNo);// TODO add username filename blahh from sharedprefs
                                if(!motion.equalsIgnoreCase("end"))
                                    motion1.add(new EachVote( answer,  motion,  motionNo,  nocount,  yescount));
                            }
                            adapter=new VotingAdapter(Main2Activity.this,0,motion1);
                            motionsLV.setAdapter(adapter);
                            if(allu.length()<2)
                            output.setText("No Motions");


                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                        error.printStackTrace();
                        Toast.makeText(Main2Activity.this, "No data recieved", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(kor);
    }


    public void volley2(String motion){

        for(int i=0;i<motion.length();i++)
        {
            if(motion.charAt(i)==' ')
                motion=motion.substring(0,i)+"%20"+motion.substring(i+1);
            else if(motion.charAt(i)=='\n')
                motion=motion.substring(0,i)+"%0A"+motion.substring(i+1);

        }

        requestQueue = Volley.newRequestQueue(this);
        //Toast.makeText(this, urli, Toast.LENGTH_SHORT).show();
        urli="https://whencollegebuddy.000webhostapp.com/addmotion.php?filename="+KEY_FILENAME+"&username="+KEY_FILENAME+"&name="+KEY_NAME+"&yescount=0&nocount=0&motion="+motion;

        //Toast.makeText(this, urli, Toast.LENGTH_SHORT).show();
        final JsonObjectRequest kor=new JsonObjectRequest(Request.Method.GET, urli, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject ob) {

                        Toast.makeText(Main2Activity.this, "Uploading...", Toast.LENGTH_SHORT).show();
                        volley(-1,-1);

                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                        error.printStackTrace();
                        Toast.makeText(Main2Activity.this, "No data recieved "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(kor);
    }

    public void datahandler()
    {
        SharedPreferences sp=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        KEY_NAME=sp.getString("name", "");
        KEY_FILENAME=sp.getString("filename2", "");
        for(int i=0;i<KEY_NAME.length();i++)
        {
            if(KEY_NAME.charAt(i)==' ')
                KEY_NAME=KEY_NAME.substring(0,i)+"%20"+KEY_NAME.substring(i+1);
            else if(KEY_NAME.charAt(i)=='\n')
                KEY_NAME=KEY_NAME.substring(0,i)+"%20%20%20"+KEY_NAME.substring(i+1);

        }
    }


}
