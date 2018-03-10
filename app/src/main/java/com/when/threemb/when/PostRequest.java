package com.when.threemb.when;



import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostRequest extends AppCompatActivity {

    private static final String REGISTER_URL = "https://whencollegebuddy.000webhostapp.com/uploadmessage.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "message";
    public String KEY_NAME="";
    public String KEY_FILENAME="";

    //public static final String KEY_EMAIL = "email";


    private EditText editTextUsername;
    //private EditText editTextEmail;
    private EditText editTextPassword;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);
        // BACK BUTTON
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Make an Announcement");
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);

        SharedPreferences sp=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        KEY_NAME=sp.getString("name", "");
        KEY_FILENAME=sp.getString("filename2", "");
        //editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        laaDo();

    }

    public void laaDo(/*View view*/)
    {

        //Toast.makeText(SeeAnnouncement.this, "Please Wait !!", Toast.LENGTH_SHORT).show();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            Toast.makeText(PostRequest.this, "Announcement Portal", Toast.LENGTH_SHORT).show();

        } else {
            // display error
            //output.setText("NO INTERNET CONNECTIVITY");
            Toast.makeText(PostRequest.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
            finish();

        }

    }

    private void registerUser() {
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (username.equals("hashcollege") || username.equals(KEY_FILENAME)) {

        //final String email = editTextEmail.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PostRequest.this, "ANNOUNCED successfully !", Toast.LENGTH_LONG).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PostRequest.this, "FAILED " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, username);
                params.put(KEY_PASSWORD, password);
                params.put("name",KEY_NAME);
                params.put("filename",KEY_FILENAME);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }
        else
        {
            Toast.makeText(this,"Permission Denied - you need correct Authorization key",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void bhejDo(View v) {
        buttonRegister.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Sending....", Toast.LENGTH_SHORT).show();
        registerUser();
    }

}