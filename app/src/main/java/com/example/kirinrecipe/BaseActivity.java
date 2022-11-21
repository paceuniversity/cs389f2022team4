package com.example.kirinrecipe;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {

    static ProgressBar progressbar;
    double sum = 1000;
    private  LinearLayout layout;

    static  int Calorie=100;
    static  int TempCalorie=50;
    static  int MaxCalorie=1000;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

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
    public  void CreateProgress(){
        layout = findViewById(R.id.layout);
        progressbar=findViewById(R.id.progressBar2);
        progressbar.setProgress(Calorie);
    }
    //Set Weight
    public void setWeight(float type,int weight){
        //MainProgress.setLayoutParams(new LinearLayout.LayoutParams(0, 60, weight));
        //MainProgress.setWidth(20);
        //ScaleAnimation s = new ScaleAnimation(sizex, sizey, sizex, sizey, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    }



    public void setMax(int num) {
        MaxCalorie=num;
    }

    public void AddCalorie(int prog) {
        Calorie+=prog;
        progressbar.incrementProgressBy(prog);

    }

    public void AddTempCalorie(int prog) {

    }
}
