package com.example.nirajgohel.brainbattle3;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btn_play , btn_practice , btn_exit , btn_yes , btn_no;
    LoginButton loginButton;
    TextView title;
    Dialog dialog;

    public static String name;
    public static String id;
   
    private static final String EMAIL = "email";
    private static final String PUBLIC_PROFILE = "public_profile";
   
    CallbackManager callbackManager;
	String url;
	
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btn_play = (Button)findViewById(R.id.btn_play);
        btn_practice = (Button)findViewById(R.id.btn_practice);
        btn_exit = (Button)findViewById(R.id.btn_exit);
        title = (TextView)findViewById(R.id.Title);

        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL,PUBLIC_PROFILE));
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            name = object.getString("name");
                            id = object.getString("id");

                            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username",name);
                            editor.putString("id",id);
                            editor.commit();
                            insert();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //Play Button
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,home.class);
                startActivity(intent);
            }
        });

        //Practice Button
        btn_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, practice.class);
                startActivity(intent);                
            }
        });

        //Exit Button
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });
    }

    public void insert(){
        String tmp=name.replace(" ","%20");
        url = "https://brainbattle2.000webhostapp.com/player_insert.php?name="+tmp+"&id="+id+"";

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        // prepare the Request
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response1) {
                        // display response
                        Log.e("Response", response1.toString());
                        Toast.makeText(MainActivity.this,"Sucess",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", error.toString());
                        Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // add it to the RequestQueue
        requestQueue.add(getRequest);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode,resultCode,data);
	}

    public void exit(){
        dialog = new Dialog(MainActivity.this, R.style.Theme_AppCompat_Light_Dialog);
        dialog.setContentView(R.layout.alert_exit);
        dialog.show();

        btn_yes = (Button)dialog.findViewById(R.id.btn_yes);
        btn_no = (Button)dialog.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
                finish();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    @Override
    public void onBackPressed(){
        exit();
    }
}