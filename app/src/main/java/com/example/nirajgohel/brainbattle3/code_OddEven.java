package com.example.nirajgohel.brainbattle3;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Vatsal on 1/19/2019.
 */

class code_OddEven {
    Random r;
    int n,t1,counter;
    String str,s;
    TextView txt1;
    TextView cs,hs;
    Activity act;
    Button btn_home,btn_pa;
    ImageView img1,img2,img3;

    code_OddEven(Activity act){
        cs=(TextView) act.findViewById(R.id.cs);
        //hs=(TextView)act.findViewById( R.id.hs);
        t1=0;
        counter=0;
        this.act=act;
        img1=(ImageView) act.findViewById(R.id.img1);
        img2=(ImageView) act.findViewById(R.id.img2);
        img3=(ImageView) act.findViewById(R.id.img3);

        cs=(TextView) act.findViewById(R.id.cs);
        //hs=(TextView) act.findViewById(R.id.hs);

        //btn_home=(Button) act.findViewById(R.id.id_home);
        //btn_pa=(Button) act.findViewById(R.id.id_home);
    }

    void getRand() {
        r = new Random();
        n = r.nextInt(100) + 1;
        txt1 = (TextView) act.findViewById(R.id.txt);
        str = String.valueOf(n);
        txt1.setText(str);
    }


    void checkOdd() {
        if (n % 2 != 0) {
            /*int tmp=Integer.parseInt(String.valueOf(hs));
            if(t1==tmp){
                t1++;
                tmp++;
                cs.setText(String.valueOf(t1));
                hs.setText(String .valueOf(tmp));
            }*/
            t1++;
        } else {
            //t1++;
            //cs.setText(String.valueOf(t1));
              counter++;
            if(counter==1){
                img1.setImageResource(R.drawable.x_img2);
            }
            if (counter==2){
               img2.setImageResource(R.drawable.x_img2);
            }
            if(counter==3)
            {
                img3.setImageResource(R.drawable.x_img2);
                Dialog d=new Dialog(this.act,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                d.setContentView(R.layout.odd_even_dialog);
                cs = (TextView) d.findViewById(R.id.cs);
                hs = (TextView) d.findViewById(R.id.highscore);
                Button home=(Button)d.findViewById(R.id.btn_home);
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(act, "Home", Toast.LENGTH_SHORT).show();
                    }
                });

                cs.setText(t1+"");
                hs.setText(t1+"");
                d.setTitle("Opps!");
                d.show();
                d.setCancelable(false);
            }

        }
    }

    void checkEven() {
        if (n % 2 == 0) {
           /* int tmp=Integer.parseInt(String.valueOf(hs));
            if(t1==tmp){
                t1++;
                tmp++;
                cs.setText(String.valueOf(t1));
                hs.setText(String.valueOf(tmp));
            }*/
           t1++;
        } else {
            counter++;
            if(counter==1){
                img1.setImageResource(R.drawable.x_img2);
            }
            if (counter==2){
                img2.setImageResource(R.drawable.x_img2);
            }
            if(counter==3) {
                img3.setImageResource(R.drawable.x_img2);
                Dialog d = new Dialog(this.act,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                d.setContentView(R.layout.odd_even_dialog);
                cs = (TextView) d.findViewById(R.id.cs);
                hs = (TextView) d.findViewById(R.id.highscore);
                Button play_again=(Button) d.findViewById(R.id.btn_playagain);
                play_again.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(act,"Play Again",Toast.LENGTH_SHORT).show();
                    }
                });

                cs.setText(t1+"");
                hs.setText(t1+"");
                d.setTitle("Opps!");
                d.show();
                d.setCancelable(false);
            }
        }
    }


}

