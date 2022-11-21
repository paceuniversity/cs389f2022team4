package com.example.kirinrecipe;


import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kirinrecipe.BaseActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;


public class HomePage extends BaseActivity implements GestureDetector.OnGestureListener {
    private int stage = 1;
    ImageView SettingIcon;
    ImageView RandomIcon;
    ImageView RecipeIcon;
    int centerX;
    int centerY;
    int distance=0;
    String hint="Choose Your Recipe";
    GestureDetector detector;
    private VelocityTracker mVelocityTracker = null;
    private static final String LOG_TAG = "";

    ProgressBar progressBar2;

    @Override
    protected void onStart() {
        CreateProgress();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        DisplayMetrics dm = getResources().getDisplayMetrics();

        centerX=dm.widthPixels/2;
        centerY=dm.heightPixels/2;
        detector = new GestureDetector(HomePage.this,this);
        SettingIcon = findViewById(R.id.setting_icon);
        RandomIcon = findViewById(R.id.random_icon);
        RecipeIcon = findViewById(R.id.recipe_icon);
        move(RandomIcon, 0, 0, 1, 0.7f, 1, 0.7f, 0);
        move(SettingIcon, 0, 0, 1, 0.7f, 1, 0.7f, 0);


        //CreateProgress();

        Log.d(LOG_TAG, "onStart"+centerX+" "+centerY);
        Log.d("", "Calorie"+Calorie);

    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        RecipeIcon.hasOnClickListeners()
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                if(mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the
                    // velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                Log.d("", ""+event.getX()+" "+event.getY());
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                // When you want to determine the velocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the velocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(1000);
                // Log velocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
                //Log.d("", "X velocity: " + mVelocityTracker.getXVelocity(pointerId));
                //Log.d("", "Y velocity: " + mVelocityTracker.getYVelocity(pointerId));
                distance+=mVelocityTracker.getXVelocity(pointerId);
                break;
            case MotionEvent.ACTION_UP:
                    if(distance>10000){
                        GotoRight();
                        Log.d("", ""+distance);
                    }
                    else if(distance<-10000){
                        GotoLeft();
                        Log.d("", ""+distance);
                    }
                    else Log.d("", ""+distance);
                    distance=0;
                    break;
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker.recycle();
                break;
        }
        return true;
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    public void GotoRight() {
        //Intent intent=new Intent(HomePage.this,Settingpage.class);
        //startActivity(intent);
        switch (stage) {
            case 1: {
                //SettingIcon.startAnimation(Translate(0,100));
                move(SettingIcon, 0, 100, 0.7f, 0, 0.7f, 0, 300);
                move(RecipeIcon, 0, 500, 1, 0.7f, 1, 0.7f, 300);
                move(RandomIcon, 0, centerX/2+70, 0.7f, 1, 0.7f, 1, 300);
                stage = 0;
                hint="Random Recipe";
            }
            break;
            case 2: {
                move(SettingIcon, -400, 0, 1, 0.7f, 1, 0.7f, 300);
                move(RecipeIcon, -500, 0, 0.7f, 1, 0.7f, 1, 300);
                move(RandomIcon, -100, 0, 0, 0.7f, 0, 0.7f, 300);
                stage = 1;
                hint="Choose Your Recipe";
            }
            break;
        }
    }

    public void GotoLeft() {
        switch (stage) {
            case 0: {
                //SettingIcon.startAnimation(Translate(0,100));
                move(SettingIcon, 100, 0, 0, 0.7f, 0, 0.7f, 300);
                move(RecipeIcon, 500, 0, 0.7f, 1, 0.7f, 1, 300);
                move(RandomIcon, centerX/2+70, 0, 1, 0.7f, 1, 0.7f, 300);
                stage = 1;
                hint="Choose Your Recipe";
            }
            break;
            case 1: {
                move(SettingIcon, 0, -400, 0.7f, 1, 0.7f, 1, 300);
                move(RecipeIcon, 0, -500, 1, 0.7f, 1, 0.7f, 300);
                move(RandomIcon, 0, -100, 0.7f, 0, 0.7f, 0, 300);
                stage = 2;
                hint="Setting Page";
            }
            break;
        }
    }

    public void move(ImageView icon, int movefrom, int moveto, float alphax, float alphay, float sizex, float sizey, int duration) {
        AnimationSet move = new AnimationSet(true);
        move.addAnimation(new AlphaAnimation(alphax, alphay));
        move.addAnimation(new TranslateAnimation(movefrom, moveto, 0, 0));
        move.addAnimation(new ScaleAnimation(sizex, sizey, sizex, sizey, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        move.setDuration(duration);
        move.setFillAfter(true);
        icon.startAnimation(move);
    }

    public void NextPage() {
        Intent intent;
        switch (stage) {
            case 0:
                intent = new Intent(HomePage.this, Randomrecipes.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(HomePage.this, DIYrecipes.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(HomePage.this, Settingpage.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if(Math.abs(e.getX()-centerX)<200&&Math.abs(e.getY()-centerY)<200)
            NextPage();
        else Log.d("", "tap+ "+e.getX()+" "+e.getY());
        AddCalorie(100);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("", "Longepress+ ");
        Toast toast;
        toast = Toast.makeText(this, hint,
                Toast.LENGTH_SHORT);
        toast.show();

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX()-e2.getX()>100){
            GotoLeft();
            return true;
        }
        if(e1.getX()-e2.getX()<-100){
            GotoRight();
            return true;
        }
        return false;
    }
}