package com.example.nirajgohel.brainbattle3;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    TextView c1 ,  w , h;
    public static RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.profile_demo);

        c1 = (TextView)findViewById(R.id.c1);
        w = (TextView)findViewById(R.id.width);
        h = (TextView)findViewById(R.id.height);

        /*Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int x = (size.x - 50)/3;*/

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int height = (size.y - 50)/2;
        int width = (size.x - 50)/2;

        /*ViewGroup.LayoutParams l = new ViewGroup.LayoutParams(width,height);
        layout.setLayoutParams(l);

        w.setText(String.valueOf(width));
        h.setText(String.valueOf(height));*/

        /*Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        float density = getResources().getDisplayMetrics().density;
        int dpHeight = (int) (displayMetrics.heightPixels/density);
        int dpWidth = (int) (displayMetrics.widthPixels/density);

        ViewGroup.LayoutParams l = new ViewGroup.LayoutParams(dpWidth,dpHeight);
        layout.setLayoutParams(l);

        w.setText(String.valueOf(dpWidth));
        h.setText(String.valueOf(dpHeight));*/

        /*relativeLayout = new RelativeLayout(this);
        ViewGroup.LayoutParams l = new ViewGroup.LayoutParams(width,height);
        relativeLayout.setLayoutParams(l);
        /*int colorRes = getColor(R.color.background);
        relativeLayout.setBackgroundColor(Color.RED);
        relativeLayout.setVerticalGravity(RelativeLayout.CENTER_VERTICAL);
        relativeLayout.setHorizontalGravity(RelativeLayout.CENTER_HORIZONTAL);
        setContentView(relativeLayout);*/
    }

}