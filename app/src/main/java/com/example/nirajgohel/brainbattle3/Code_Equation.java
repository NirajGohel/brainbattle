package com.example.nirajgohel.brainbattle3;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class Code_Equation{

    private  Activity activity;
    private int limit;
    private int operation[] = new int[5];
    private int operand1[] = new int[5];
    private int operand2[] = new int[5];
    private int ans[] = new int[5];
    private int player_ans[] = new int[5];
    private String op[] = new String[5];
    String str_ans[] = new String[5];
    static public int var_score = 0;
    static public int wrong = 0;

    ImageView img1 , img2 , img3;
    TextView eq1Op1 , eq1Op2 , Op1;
    TextView eq2Op1 , eq2Op2 , Op2;
    TextView eq3Op1 , eq3Op2 , Op3;
    TextView eq4Op1 , eq4Op2 , Op4;
    TextView eq5Op1 , eq5Op2 , Op5;
    TextView score , highscore , dialog_score , dialog_highscore;

    EditText ans1 , ans2 ,ans3 , ans4 , ans5;
    Button btn;

    Button btn_home , btn_playagain;

    Code_Equation(Activity activity){
        this.activity = activity;

        eq1Op1 = (TextView)this.activity.findViewById(R.id.eq1Op1);
        eq1Op2 = (TextView)this.activity.findViewById(R.id.eq1Op2);
        Op1 = (TextView)this.activity.findViewById(R.id.Op1);

        eq2Op1 = (TextView)this.activity.findViewById(R.id.eq2Op1);
        eq2Op2 = (TextView)this.activity.findViewById(R.id.eq2Op2);
        Op2 = (TextView)this.activity.findViewById(R.id.Op2);

        eq3Op1 = (TextView)this.activity.findViewById(R.id.eq3Op1);
        eq3Op2 = (TextView)this.activity.findViewById(R.id.eq3Op2);
        Op3 = (TextView)this.activity.findViewById(R.id.Op3);

        eq4Op1 = (TextView)this.activity.findViewById(R.id.eq4Op1);
        eq4Op2 = (TextView)this.activity.findViewById(R.id.eq4Op2);
        Op4 = (TextView)this.activity.findViewById(R.id.Op4);

        eq5Op1 = (TextView)this.activity.findViewById(R.id.eq5Op1);
        eq5Op2 = (TextView)this.activity.findViewById(R.id.eq5Op2);
        Op5 = (TextView)this.activity.findViewById(R.id.Op5);

        ans1 = (EditText)this.activity.findViewById(R.id.ans1);
        ans2 = (EditText)this.activity.findViewById(R.id.ans2);
        ans3 = (EditText)this.activity.findViewById(R.id.ans3);
        ans4 = (EditText)this.activity.findViewById(R.id.ans4);
        ans5 = (EditText)this.activity.findViewById(R.id.ans5);

        score = (TextView)this.activity.findViewById(R.id.score);
        highscore = (TextView)this.activity.findViewById(R.id.highscore);

        img1 = (ImageView)this.activity.findViewById(R.id.img1);
        img2 = (ImageView)this.activity.findViewById(R.id.img2);
        img3 = (ImageView)this.activity.findViewById(R.id.img3);
    }

    void start(){
        var_score = 0;
        wrong = 0;

        difficulty();
        getValue();
        setValue();
    }

    void difficulty(){
         if(var_score == 0)
             limit = 10;
         else if(var_score <= 4)
             limit = 15;
         else if(var_score <= 8)
             limit = 20;
         else if(var_score <= 12)
             limit = 25;
    }

    int number(){
        Random r = new Random();
        return r.nextInt(limit)+1;
    }

    int number(int limit){
        Random r = new Random();
        return r.nextInt(limit)+1;
    }

    void getValue(){
        for(int i = 0; i<5; i++) {
            operand1[i] = number();
            operand2[i] = number();
            operation[i] = number(4);

            if(operation[i]== 1)
                op[i] = "+";
            else if(operation[i] == 2)
                op[i] = "-";
            else if(operation[i] == 3)
                op[i] = "*";
            else if(operation[i] == 4)
                op[i] = "/";

            /*if(i!=0) {
                while (operation[i] == operation[j]) {
                    while (operand1[i] == operand1[j] && operand2[i] == operand2[j]) {
                        operand1[i] = number(10);
                        operand2[i] = number(10);
                    }
                    j++;
                }
            }*/

            for(int k = 1; k<=i; k++){
                if(operation[i]==operation[k]){
                    if(operand1[i]==operand1[k] && operand2[i]==operand2[k]){
                        operand1[i] = number();
                        operand2[i] = number();
                    }
                }
            }

            //Setting up Value For Subtraction Operation
            if(operation[i] == 2){
                while (operand1[i] < operand2[i]){
                    int swap =  operand1[i];
                    operand1[i] = operand2[i];
                    operand2[i] = swap;
                }
            }

            //Setting up Value For Division Operation
            if(operation[i] == 4){
                int swap;
                int div , temp1 , temp2;

                if(operand1[i] < operand2[i]){
                    swap = operand1[i];
                    operand1[i] = operand2[i];
                    operand2[i] = swap;
                }

                div = operand1[i] / operand2[i];
                temp1 = operand2[i] * div;
                temp1 = operand1[i] - temp1;
                temp2 = operand2[i] - temp1;

                if(temp1 <= temp2)
                    operand1[i] = operand1[i] + temp2;
                else
                    operand1[i] = operand1[i] - temp1;
            }

            switch (operation[i]){
                case 1:
                    add(operand1[i],operand2[i],i);
                    break;
                case 2:
                    sub(operand1[i],operand2[i],i);
                    break;
                case 3:
                    mul(operand1[i],operand2[i],i);
                    break;
                case 4:
                    div(operand1[i],operand2[i],i);
                    break;
            }
        }
    }

    void add(int a,int b,int tmp){
        ans[tmp] = a + b;
    }

    void sub(int a,int b,int tmp){
        ans[tmp] = a - b;
    }

    void mul(int a,int b,int tmp){
        ans[tmp] = a * b;
    }

    void div(int a,int b,int tmp){
        ans[tmp] = a / b;
    }

    void setValue(){
        eq1Op1.setText(String.valueOf(operand1[0]));
        eq1Op2.setText(String.valueOf(operand2[0]));

        eq2Op1.setText(String.valueOf(operand1[1]));
        eq2Op2.setText(String.valueOf(operand2[1]));

        eq3Op1.setText(String.valueOf(operand1[2]));
        eq3Op2.setText(String.valueOf(operand2[2]));

        eq4Op1.setText(String.valueOf(operand1[3]));
        eq4Op2.setText(String.valueOf(operand2[3]));

        eq5Op1.setText(String.valueOf(operand1[4]));
        eq5Op2.setText(String.valueOf(operand2[4]));

        Op1.setText(op[0]);
        Op2.setText(op[1]);
        Op3.setText(op[2]);
        Op4.setText(op[3]);
        Op5.setText(op[4]);
    }

    int get_Answer(){
        int tmp = 0;

        str_ans[0] = ans1.getText().toString();
        str_ans[1] = ans2.getText().toString();
        str_ans[2] = ans3.getText().toString();
        str_ans[3] = ans4.getText().toString();
        str_ans[4] = ans5.getText().toString();

        for(int i = 0; i<5; i++){
            if(str_ans[i].equals(""))
                tmp = 1;
        }

        if(tmp != 1){
            for(int i = 0; i<5; i++){
                player_ans[i] = Integer.parseInt(str_ans[i]);
            }

            for(int i = 0; i<5; i++){
                if(player_ans[i] == ans[i])
                    var_score++;
                else {
                    wrong++;
                }
            }

            score(wrong);
            ans1.setText("");
            ans2.setText("");
            ans3.setText("");
            ans4.setText("");
            ans5.setText("");
        }
        else{
            Toast.makeText(activity,"Please give the answers",Toast.LENGTH_SHORT).show();
        }

        return tmp;
    }

    void score(final int wrong){
        int flag=0;
        int tmp = Integer.parseInt(mode_equation.hscore);

        if(var_score<=tmp){
            score.setText(String.valueOf(var_score));
        }
        else {
            flag=1;
            score.setText(String.valueOf(var_score));
            highscore.setText(String.valueOf(var_score));
        }

        if(wrong>=3){
            img1.setImageResource(R.drawable.x_red);
            img2.setImageResource(R.drawable.x_red);
            img3.setImageResource(R.drawable.x_red);

            final Dialog dialog = new Dialog(this.activity,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
            dialog.setContentView(R.layout.equ_dialog);
            dialog.setCancelable(false);
            dialog.show();

            /*SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Highscore", highscore.getText().toString());
            editor.commit();*/

            dialog_score = (TextView)dialog.findViewById(R.id.dialog_score);
            dialog_score.setText(String.valueOf(var_score));

            dialog_highscore = (TextView)dialog.findViewById(R.id.dialog_highscore);
            //dialog_highscore.setText(sharedPreferences.getString("Highscore",""));;

            if(flag==1)
                dialog_highscore.setText(String.valueOf(var_score));
            else {
                if (var_score > Integer.parseInt(mode_equation.hscore))
                    dialog_highscore.setText(String.valueOf(var_score));

                dialog_highscore.setText(mode_equation.hscore);
            }

            btn_home = (Button)dialog.findViewById(R.id.btn_home);
            btn_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity,MainActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });

            btn_playagain = (Button)dialog.findViewById(R.id.btn_playagain);
            btn_playagain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    score.setText(String.valueOf(0));
                    var_score = 0;
                    Code_Equation.wrong = 0;

                    img1.setImageResource(R.drawable.x_grey);
                    img2.setImageResource(R.drawable.x_grey);
                    img3.setImageResource(R.drawable.x_grey);

                    SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("Brain Battle",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Highscore", dialog_highscore.getText().toString());
                    editor.commit();

                    difficulty();
                    getValue();
                    setValue();
                }
            });
        }
        else if(wrong==1)
            img1.setImageResource(R.drawable.x_red);
        else if(wrong==2) {
            img1.setImageResource(R.drawable.x_red);
            img2.setImageResource(R.drawable.x_red);
        }
    }
}
