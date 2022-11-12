package com.example.kirinrecipe;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.core.view.MotionEventCompat;

public class HomePage extends BaseActivity {
    private int stage = 1;
    ImageView SettingIcon;
    ImageView RandomIcon;
    ImageView RecipeIcon;
    int distance=0;
    private VelocityTracker mVelocityTracker = null;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        DisplayMetrics dm = getResources().getDisplayMetrics();

        SettingIcon = findViewById(R.id.setting_icon);
        RandomIcon = findViewById(R.id.random_icon);
        RecipeIcon = findViewById(R.id.recipe_icon);
        move(RandomIcon, 0, 0, 1, 0.7f, 1, 0.7f, 0);
        move(SettingIcon, 0, 0, 1, 0.7f, 1, 0.7f, 0);

        //The system progress bar has a relatively large limit and can only set 2 colors
        findViewProg(R.id.progressbar);
        setProg(350);

        //Customize the use of the progress bar, you can set 3 colors
        findViewText();
        //The number of textviews of type, the proportion of weight weight
        setWeight(1, 2);
        setWeight(2, 3);
        setWeight(3, 1);
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

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
                    if(distance>20000){
                        GotoRight(RecipeIcon);
                        Log.d("", ""+distance);
                    }
                    else if(distance<-20000){
                        GotoLeft(RecipeIcon);
                        Log.d("", ""+distance);
                    }
                    distance=0;
                    break;
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker.recycle();
                break;
        }
        return true;
    }

    public void GotoRight(View view) {
        //Intent intent=new Intent(HomePage.this,Settingpage.class);
        //startActivity(intent);
        switch (stage) {
            case 1: {
                //SettingIcon.startAnimation(Translate(0,100));
                move(SettingIcon, 0, 100, 0.7f, 0, 0.7f, 0, 300);
                move(RecipeIcon, 0, 500, 1, 0.7f, 1, 0.7f, 300);
                move(RandomIcon, 0, 400, 0.7f, 1, 0.7f, 1, 300);
                stage = 0;
            }
            break;
            case 2: {
                move(SettingIcon, -400, 0, 1, 0.7f, 1, 0.7f, 300);
                move(RecipeIcon, -500, 0, 0.7f, 1, 0.7f, 1, 300);
                move(RandomIcon, -100, 0, 0, 0.7f, 0, 0.7f, 300);
                stage = 1;
            }
            break;
        }
    }

    public void GotoLeft(View view) {
        switch (stage) {
            case 0: {
                //SettingIcon.startAnimation(Translate(0,100));
                move(SettingIcon, 100, 0, 0, 0.7f, 0, 0.7f, 300);
                move(RecipeIcon, 500, 0, 0.7f, 1, 0.7f, 1, 300);
                move(RandomIcon, 400, 0, 1, 0.7f, 1, 0.7f, 300);
                stage = 1;
            }
            break;
            case 1: {
                move(SettingIcon, 0, -400, 0.7f, 1, 0.7f, 1, 300);
                move(RecipeIcon, 0, -500, 1, 0.7f, 1, 0.7f, 300);
                move(RandomIcon, 0, -100, 0.7f, 0, 0.7f, 0, 300);
                stage = 2;
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


    public void NextPage(View view) {
        Intent intent;
        switch (stage) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                intent = new Intent(HomePage.this, Settingpage.class);
                startActivity(intent);
                break;

        }
    }
}