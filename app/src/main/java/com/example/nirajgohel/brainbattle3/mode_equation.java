package com.example.nirajgohel.brainbattle3;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.facebook.FacebookSdk.getApplicationContext;

public class mode_equation extends AppCompatActivity implements View.OnClickListener{

    static int DEFAULT = 0;
    public static String hscore="";

    TextView highscore , score , dialog_highscore;
    public Button number1, number2, number3, number4, number5, number6, number7, number8, number9, number0, btnNext, btnClear;
    public static EditText setAnswer;
    Code_Equation e1;
    Button btn_home , btn_continue , btn_playagain;
    ImageView img1 , img2 , img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mode_equation);

        e1 = new Code_Equation(this);
        e1.difficulty();
        e1.getValue();
        e1.setValue();

        highscore = (TextView)findViewById(R.id.highscore);
        score = (TextView)findViewById(R.id.score);

        setAnswer = new EditText(this);
        //setAnswer.setInputType(InputType.TYPE_NULL);
        //setAnswer = findViewById(R.id.ans1);

        number0 = findViewById(R.id.number0);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        number3 = findViewById(R.id.number3);
        number4 = findViewById(R.id.number4);
        number5 = findViewById(R.id.number5);
        number6 = findViewById(R.id.number6);
        number7 = findViewById(R.id.number7);
        number8 = findViewById(R.id.number8);
        number9 = findViewById(R.id.number9);
        btnNext = findViewById(R.id.btnNext);
        btnClear = findViewById(R.id.btnClear);

        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);

        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
        hscore = sharedPreferences.getString("Highscore",String.valueOf(DEFAULT));
        highscore.setText(hscore);
//
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int flag = e1.get_Answer();
//                if(flag == 0) {
//                    e1.difficulty();
//                    e1.getValue();
//                    e1.setValue();
//                }
//                else {
//                    //Toast.makeText(mode_equation.this,"Please give the answers",Toast.LENGTH_SHORT).show();
//                }
//                //SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("Highscore", highscore.getText().toString());
//                editor.commit();
//            }
//        });
    }

    public void setAnswer(View v) {
        if (v.getId() == R.id.ans1) {
            setAnswer = findViewById(R.id.ans1);
            setAnswer.setBackgroundResource(R.drawable.et);
            findViewById(R.id.ans2).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans3).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans4).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans5).setBackgroundResource(R.drawable.et_border);

        } else if (v.getId() == R.id.ans2) {
            setAnswer = findViewById(R.id.ans2);
            setAnswer.setBackgroundResource(R.drawable.et);
            findViewById(R.id.ans1).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans3).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans4).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans5).setBackgroundResource(R.drawable.et_border);

        } else if (v.getId() == R.id.ans3) {
            setAnswer = findViewById(R.id.ans3);
            setAnswer.setBackgroundResource(R.drawable.et);
            findViewById(R.id.ans1).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans2).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans4).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans5).setBackgroundResource(R.drawable.et_border);

        } else if (v.getId() == R.id.ans4) {
            setAnswer = findViewById(R.id.ans4);
            setAnswer.setBackgroundResource(R.drawable.et);
            findViewById(R.id.ans1).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans2).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans3).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans5).setBackgroundResource(R.drawable.et_border);

        }else if(v.getId() == R.id.ans5){
            setAnswer = findViewById(R.id.ans5);
            setAnswer.setBackgroundResource(R.drawable.et);
            findViewById(R.id.ans1).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans2).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans3).setBackgroundResource(R.drawable.et_border);
            findViewById(R.id.ans4).setBackgroundResource(R.drawable.et_border);

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.number0:
                setAnswer.setText(setAnswer.getText().toString().concat("0"));
                break;

            case R.id.number1:
                setAnswer.setText(setAnswer.getText().toString().concat("1"));
                break;

            case R.id.number2:
                setAnswer.setText(setAnswer.getText().toString().concat("2"));
                break;

            case R.id.number3:
                setAnswer.setText(setAnswer.getText().toString().concat("3"));
                break;

            case R.id.number4:
                setAnswer.setText(setAnswer.getText().toString().concat("4"));
                break;

            case R.id.number5:
                setAnswer.setText(setAnswer.getText().toString().concat("5"));
                break;

            case R.id.number6:
                setAnswer.setText(setAnswer.getText().toString().concat("6"));
                break;

            case R.id.number7:
                setAnswer.setText(setAnswer.getText().toString().concat("7"));
                break;

            case R.id.number8:
                setAnswer.setText(setAnswer.getText().toString().concat("8"));
                break;

            case R.id.number9:
                setAnswer.setText(setAnswer.getText().toString().concat("9"));
                break;

            case R.id.btnNext:
                int flag = e1.get_Answer();
                if(flag == 0) {
                    e1.difficulty();
                    e1.getValue();
                    e1.setValue();
                }
                else {
                    //Toast.makeText(mode_equation.this,"Please give the answers",Toast.LENGTH_SHORT).show();
                }
                SharedPreferences sharedPreferences = getSharedPreferences("Brain Battle",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Highscore", highscore.getText().toString());
                editor.commit();
                break;

            case R.id.btnClear:
                if (!setAnswer.getText().toString().equals("")) {
                    String str = setAnswer.getText().toString();
                    String str1 = str;
                    str1 = str1.substring(0, str.length() - 1);
                    setAnswer.setText(str1);
                }
                break;
        }
    }

    @Override
    public void onBackPressed(){
        final Dialog dialog = new Dialog(mode_equation.this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialog.setContentView(R.layout.equ_pause_dialog);
        dialog.setCancelable(false);
        dialog.show();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
        hscore = sharedPreferences.getString("Highscore",String.valueOf(DEFAULT));
        dialog_highscore = (TextView)dialog.findViewById(R.id.dialog_highscore);
        dialog_highscore.setText(hscore);

        btn_home = (Button)dialog.findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mode_equation.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_continue = (Button)dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        btn_playagain = (Button)dialog.findViewById(R.id.btn_playagain);
        btn_playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                score.setText(String.valueOf(0));
                Code_Equation.var_score = 0;
                Code_Equation.wrong = 0;

                img1.setImageResource(R.drawable.x_grey);
                img2.setImageResource(R.drawable.x_grey);
                img3.setImageResource(R.drawable.x_grey);

                e1.difficulty();
                e1.getValue();
                e1.setValue();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*SharedPreferences pref = getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Highscore", highscore.getText().toString());
        editor.commit();*/
    }
}
