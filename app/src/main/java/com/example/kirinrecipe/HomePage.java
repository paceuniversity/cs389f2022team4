package com.example.kirinrecipe;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class HomePage extends BaseActivity {
    private int stage = 1;
    ImageView SettingIcon;
    ImageView RandomIcon;
    ImageView RecipeIcon;


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