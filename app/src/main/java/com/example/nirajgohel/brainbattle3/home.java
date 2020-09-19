package com.example.nirajgohel.brainbattle3;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    TextView player_name;
    ProfilePictureView profilePictureView;
    Button btn_leadeboard , btn_friends;
    RelativeLayout btn_play1;
    String url , id , name;
    ProgressDialog progressDialog;

    Dialog dialog;
    ToggleButton btn_solo , btn_duo , btn_squad;
    String tmp;

    ArrayList<String> info;
    ProfilePictureView player_ProfilePicture , opponent_ProfilePicture , prof_ProfilePicture;
    TextView dio_player_name , opponent_name;

    TextView prof_player_name,matches,winrate,trophy,win_matches,lost_matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home);

        player_name = (TextView)findViewById(R.id.player_name);
        profilePictureView = (ProfilePictureView) findViewById(R.id.ProfilePicture);
        btn_leadeboard = (Button)findViewById(R.id.btn_leaderboard);
        btn_friends = (Button)findViewById(R.id.btn_friends);
        btn_play1 = (RelativeLayout) findViewById(R.id.btn_play1);

        progressDialog = new ProgressDialog(home.this);
        info = new ArrayList<>();

        final AccessToken token = AccessToken.getCurrentAccessToken();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
        player_name.setText(sharedPreferences.getString("username",String.valueOf("")));
        profilePictureView.setProfileId(sharedPreferences.getString("id",String.valueOf("")));
        id = sharedPreferences.getString("id",String.valueOf(""));
        name = sharedPreferences.getString("username",String.valueOf(""));
        /*if(token != null) {
            btn_friends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*GraphRequestAsyncTask graphRequestAsyncTask = new GraphRequest(
                            token, "/me/friends", null, HttpMethod.GET, new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            Intent intent = new Intent(home.this,FriendList.class);
                            try {
                                JSONArray rawName = response.getJSONObject().getJSONArray("data");
                                intent.putExtra("jsondata",rawName.toString());
                                startActivity(intent);
                            }
                            catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }).executeAsync();
                }
            });
        }
        else
            Toast.makeText(this,"Token Empty",Toast.LENGTH_SHORT).show();*/

        btn_play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find_player();
            }
        });
    }

    public void find_player(){
        url = "https://brainbattle2.000webhostapp.com/find_player.php?id="+id+"";
        progressDialog.setMessage("Connecting to the server");
        progressDialog.show();

        //Toast.makeText(home.this,id,Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(home.this);
        // prepare the Request;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        try {
                            progressDialog.cancel();
                            Log.e("Response", response.toString());

                            /*for(int i=0; i<response.length(); i++){
                                String jsonObject = response.getString("name");
                                Log.e("name",jsonObject);
                            }*/

                            if(!response.getString("id").equals("0")){
                                Log.e("in","546");
                                start(response);
                                //Toast.makeText(home.this,"Sucess",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(home.this,"Try After Some Time",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.cancel();
                        Log.e("Error.Response", error.toString());
                        Toast.makeText(home.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );

        int socketTimeout = 20000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        getRequest.setRetryPolicy(policy);
        // add it to the RequestQueue
        requestQueue.add(getRequest);
    }

    void start(JSONObject response) throws JSONException {
        dialog = new Dialog(home.this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_match_making);

        player_ProfilePicture = (ProfilePictureView)dialog.findViewById(R.id.player_ProfilePicture);
        opponent_ProfilePicture = (ProfilePictureView)dialog.findViewById(R.id.opponent_ProfilePicture);

        dio_player_name = (TextView)dialog.findViewById(R.id.dio_player_name);
        opponent_name = (TextView)dialog.findViewById(R.id.opponent_name);

        player_ProfilePicture.setProfileId(id);
        opponent_ProfilePicture.setProfileId(response.getString("id"));

        dio_player_name.setText(name);
        opponent_name.setText(response.getString("name"));

        dialog.show();
    }

    public void dialog_profile(View v){
        url = "https://brainbattle2.000webhostapp.com/profile.php?id="+id+"";
        final RequestQueue requestQueue = Volley.newRequestQueue(home.this);
        // prepare the Request;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        dialog = new Dialog(home.this,R.style.Theme_AppCompat_Light_Dialog);
                        dialog.setContentView(R.layout.profile_demo);
                        dialog.show();

                        prof_ProfilePicture = (ProfilePictureView)dialog.findViewById(R.id.prof_ProfilePicture);
                        prof_player_name = (TextView)dialog.findViewById(R.id.prof_player_name);

                        prof_ProfilePicture.setProfileId(id);
                        prof_player_name.setText(name);

                        matches = (TextView)dialog.findViewById(R.id.matches);
                        winrate = (TextView)dialog.findViewById(R.id.winrate);
                        trophy = (TextView)dialog.findViewById(R.id.trophy);
                        win_matches = (TextView)dialog.findViewById(R.id.win_matches);
                        lost_matches = (TextView)dialog.findViewById(R.id.lost_matches);

                        try {
                            Log.e("Response", response.toString());
                            matches.setText(response.getString("total_matches"));
                            winrate.setText(response.getString("win_matches"));
                            win_matches.setText(response.getString("win_matches"));

                            int total = Integer.parseInt(response.getString("total_matches"));
                            int win = Integer.parseInt(response.getString("win_matches"));
                            int lost = total - win;

                            lost_matches.setText(String.valueOf(lost));
                            trophy.setText(response.getString("trophy"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", error.toString());
                    }
                }
        );

        // add it to the RequestQueue
        requestQueue.add(getRequest);
      /*btn_solo = (ToggleButton) dialog.findViewById(R.id.btn_solo);
        btn_duo = (ToggleButton) dialog.findViewById(R.id.btn_duo);
        btn_squad = (ToggleButton) dialog.findViewById(R.id.btn_squad);

        btn_solo.setOnClickListener(this);
        btn_duo.setOnClickListener(this);
        btn_squad.setOnClickListener(this);*/
    }

    /*@Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_solo){
            btn_solo.setChecked(true);
            btn_duo.setChecked(false);
            btn_squad.setChecked(false);
        }
        if(view.getId()==R.id.btn_duo){
            btn_duo.setChecked(true);
            btn_solo.setChecked(false);
            btn_squad.setChecked(false);
        }
        if(view.getId()==R.id.btn_squad){
            btn_squad.setChecked(true);
            btn_solo.setChecked(false);
            btn_duo.setChecked(false);
        }
    }*/

    public void leaderboard(View view){
        String tmp=name.replace(" ","%20");
        url = "https://brainbattle2.000webhostapp.com/leaderboard.php";

        RequestQueue requestQueue = Volley.newRequestQueue(home.this);
        // prepare the Request
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response1) {
                        // display response
                        Log.e("Response", response1.toString());
                        Intent intent = new Intent(home.this,Leaderboard.class);
                        startActivity(intent);
                        //Toast.makeText(home.this,"Sucess",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", error.toString());
                        Toast.makeText(home.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // add it to the RequestQueue
        requestQueue.add(getRequest);
    }
}
