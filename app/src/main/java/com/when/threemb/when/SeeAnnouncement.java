package com.when.threemb.when;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


public class SeeAnnouncement extends AppCompatActivity {

    String username="asd",message="asdd";
    ListView earthquakeListView= null;
    TextView output ;
    Button go;
    String data = "";
    String urli="https://whencollegebuddy.000webhostapp.com/displaymessage.php?filename=";

    RequestQueue requestQueue;
    ArrayList<MessageObj> earthquakes;

    AnnouncementAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_announcement);
        // BACK BUTTON
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Announcements");
        SharedPreferences sp=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String fname=sp.getString("filename2", "hashcollege");
        urli+=fname;
        // Create a fake list of earthquake locations.
        earthquakeListView = (ListView) findViewById(R.id.listView1);
        output = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(output);

        /*ArrayList<MessageObj> */
        earthquakes = new ArrayList<>();


        /*earthquakes.add(new MessageObj(username,message));
        earthquakes.add(new MessageObj("abc",message));
        earthquakes.add(new MessageObj("siaya","sjhdsajd"));*/
        laaDo();


    }

    public void laaDo(/*View view*/)
    {

        Toast.makeText(SeeAnnouncement.this, "Please Wait !!", Toast.LENGTH_SHORT).show();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            volley();

        } else {
            // display error
            output.setText("NO INTERNET CONNECTIVITY");

        }

    }

    public void volley(){
        requestQueue = Volley.newRequestQueue(this);
        //Toast.makeText(this, urli, Toast.LENGTH_SHORT).show();
        final JsonObjectRequest kor=new JsonObjectRequest(Request.Method.GET, urli, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject ob) {

                        //Toast.makeText(SeeAnnouncement.this, "fetching response", Toast.LENGTH_SHORT).show();
                        if(ob!=null){
                            JSONArray allu= ob.optJSONArray("allu");
                            data="";
                            for(int i=allu.length()-1;i>-1;i--)
                            {
                                JSONObject alluobj=allu.optJSONObject(i);
                                String username=alluobj.optString("username");
                                String message=alluobj.optString("message");
                                if(!username.equalsIgnoreCase("end"))
                                earthquakes.add(new MessageObj(message,username));
                            }
                            adapter=new AnnouncementAdapter(SeeAnnouncement.this,0,earthquakes);
                            earthquakeListView.setAdapter(adapter);
                            if(allu.length()<2)
                                output.setText("No Announcements");


                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                        error.printStackTrace();
                        Toast.makeText(SeeAnnouncement.this, "No data recieved", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(kor);
    }

}
