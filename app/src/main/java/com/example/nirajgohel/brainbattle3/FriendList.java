package com.example.nirajgohel.brainbattle3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FriendList extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.friends);

        /*Intent intent = getIntent();
        String jsondata = intent.getStringExtra("jsondata");

        JSONArray friendlist;
        ArrayList<String> frineds = new ArrayList<String>();

        try{
            friendlist = new JSONArray(jsondata);
            for(int i=0; i<friendlist.length(); i++){
                frineds.add(friendlist.getJSONObject(i).getString("name"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,frineds);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);*/
    }
}
