package com.example.nirajgohel.brainbattle3;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class mode_odd_even extends AppCompatActivity {

    Button btneven;
    Button btnodd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_odd_even);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final code_OddEven o1=new code_OddEven(this);
        o1.getRand();

        btnodd = (Button) findViewById(R.id.btnodd);
        btnodd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o1.checkOdd();
                o1.getRand();
            }
        });


        btneven = (Button) findViewById(R.id.btneven);
        btneven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o1.checkEven();
                o1.getRand();
            }
        });
    }

}
