package com.example.kirinrecipe;

import android.graphics.drawable.Drawable;
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
import com.example.kirinrecipe.Splash;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {
    static RecipeList MyrecipeList=new RecipeList();
    static recipe[] LinkRecipeList = new recipe[3];
    static ProgressBar progressbar;

    private  LinearLayout layout;
    static TextView textView;
    static  int Calorie=0;
    static  int TempCalorie=0;
    static  int MaxCalorie=114514;
    static recipe recipeInfo;

    //static FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
    //FirebaseStorage storage = FirebaseStorage.getInstance();

    static int AnimateCalorie=0;
    static int AnimateTempCalorie=0;

    public  void CreateProgress(){
        progressbar=findViewById(R.id.progressBar2);
        if(MaxCalorie!= GetMaxCalorie()){
            MaxCalorie=GetMaxCalorie();
        }
        progressbar.setMax(MaxCalorie);
        progressbar.setProgress(AnimateCalorie);
        progressbar.setSecondaryProgress(Calorie+AnimateTempCalorie);
        textView=findViewById(R.id.ProgressBarText);
        int currentC=Calorie+TempCalorie;
        textView.setText("Toady's Calories: "+currentC+" / "+ MaxCalorie);
        Log.d("", "CalorieCreate"+Calorie+" "+AnimateTempCalorie+" "+progressbar.getMax());

    }
    int GetMaxCalorie(){
        double result=0;
        if(Splash.Myuser.getGender()=="Female"){
            result = 655+(9.563*Splash.Myuser.getWeight())+(1.85*Splash.Myuser.getHeight())-(4.676*Splash.Myuser.getAge());
        }
        else{
            result = 66.47+(13.75*Splash.Myuser.getWeight())+(5.003*Splash.Myuser.getHeight())-(6.755*Splash.Myuser.getAge());
        }
        return (int)result;
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
        int currentC=Calorie+TempCalorie;
        textView.setText("Toady's Calories: "+currentC+" / "+ MaxCalorie);
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
        int currentC=Calorie+TempCalorie;
        textView.setText("Toady's Calories: "+currentC+" / "+ MaxCalorie);
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
    int RandomAnimeduration(float time){
        int result = (int)lerp(300,50,time/3000);
        if(result<50)result=50;
        return result;
    }
    float lerp (float x,float y,float weight){
        return (x+(y-x)*weight);
    }
    public void AddTempCalorie(int prog) {

    }


}
