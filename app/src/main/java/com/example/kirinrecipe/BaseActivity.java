package com.example.kirinrecipe;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    ProgressBar progressbar;
    double sum = 1000;
    private LinearLayout layout;

    public void findViewProg(int id) {
        progressbar = findViewById(id);
        setMax((int) sum);
    }

    public void findViewText() {
        layout = findViewById(R.id.layout);
    }

    public void clear(int weight1,int weight2,int weight3){
        if(layout != null){
            layout.removeAllViews();
        }
    }
    //Set Weight
    public void setWeight(float type,int weight){
        TextView textView = new TextView(layout.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 60, weight);
        textView.setLayoutParams(params);
        if(type == 1){
            textView.setBackground(getDrawable(R.drawable.caloreis_3));
        }

        if(type == 2){
            textView.setBackground(getDrawable(R.drawable.caloreis_2));
        }

        if(type == 3){
            textView.setBackground(getDrawable(R.drawable.caloreis_4));
        }

        if(layout != null){
            layout.addView(textView);
        }
    }



    public void setMax(int num) {
        progressbar.setMax(num);
    }

    public void setProg(int prog) {
        double num = (double) (prog / sum);
        progressbar.setProgress((int) (num*sum));
        setProg2((int) (sum - num*sum));
    }

    public void setProg2(int prog) {
        progressbar.setSecondaryProgress(prog);
    }
}
