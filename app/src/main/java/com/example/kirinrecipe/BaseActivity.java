package com.example.kirinrecipe;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class BaseActivity extends AppCompatActivity {
    static RecipeList MyrecipeList=new RecipeList();

    static ProgressBar progressbar;

    private  LinearLayout layout;

    static  int Calorie=100;
    static  int TempCalorie=50;
    static  int MaxCalorie=2000;

    //static FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
    FirebaseStorage storage = FirebaseStorage.getInstance();

    static int AnimateCalorie=100;
    static int AnimateTempCalorie=50;

    public  void CreateProgress(){
        progressbar=findViewById(R.id.progressBar2);
        progressbar.setMax(MaxCalorie);
        progressbar.setProgress(AnimateCalorie);
        progressbar.setSecondaryProgress(Calorie+AnimateTempCalorie);

        Log.d("", "CalorieCreate"+Calorie+" "+AnimateTempCalorie+" "+progressbar.getMax());
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
        //progressbar.incrementProgressBy(prog);
        new Thread(){
            public void run(){
                super.run();
                while (AnimateCalorie<Calorie){
                    AnimateCalorie++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressbar.incrementProgressBy(1);
                        }
                    });
                    try {
                        Thread.sleep(duration());
                        //Log.d("", "duration"+duration());
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //Log.d("", "CalorieCurrent"+Calorie);
            }
        }.start();
    }
    public void ModifyTempCalorie(int prog){
        TempCalorie=prog;
        //Log.d("", "StartRandom"+TempCalorie+" "+AnimateTempCalorie);
        new Thread(){
            public void run(){
                super.run();
                while (AnimateTempCalorie!=TempCalorie){
                    if(TempCalorie>AnimateTempCalorie)AnimateTempCalorie++;
                    if(TempCalorie<AnimateTempCalorie)AnimateTempCalorie--;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressbar.setSecondaryProgress(Calorie+AnimateTempCalorie);
                        }
                    });
                    try {
                        Thread.sleep(duration2());
                        //Log.d("", "duration"+duration2());
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //Log.d("", "CalorieTempCurrent"+TempCalorie);
            }
        }.start();
    }
    int duration(){
        int time = (int)lerp(1f,100f,1f/(Calorie+1-AnimateCalorie));
        //if(time>15)time = 15;
        return time;
    }
    int duration2(){
        int gap=Math.abs(AnimateTempCalorie-TempCalorie);
        int time = (int)lerp(1f,100f,1f/(gap+1));
        //if(time>15)time = 15;
        return time;
    }
    float lerp (float x,float y,float weight){
        return (x+(y-x)*weight);
    }
    public void AddTempCalorie(int prog) {

    }
}
