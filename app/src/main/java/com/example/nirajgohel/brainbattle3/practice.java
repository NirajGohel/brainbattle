package com.example.nirajgohel.brainbattle3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class practice extends Activity{

    Button btn_num , btn_odd_even , btn_equ , btn_shape;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.practice);

        btn_num = (Button)findViewById(R.id.btn_num);
        btn_odd_even = (Button)findViewById(R.id.btn_odd_even);
        btn_equ = (Button)findViewById(R.id.btn_equ);
        btn_shape = (Button)findViewById(R.id.btn_shape);

        //Equation Mode
        btn_equ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(practice.this,mode_equation.class);
                startActivity(intent);
                finish();
            }
        });

        //Odd Even Mode
        btn_odd_even.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(practice.this,mode_odd_even.class);
                startActivity(intent);
            }
        });

        //Shape Mode
        btn_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(practice.this,mode_shape.class);
                startActivity(intent);
            }
        });
    }
}
