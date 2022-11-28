package com.example.kirinrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Random;

public class Randomrecipes extends BaseActivity {
    ImageView LeftImage;
    ImageView MiddleImage;
    ImageView RightImage;
    float animetime=0;
    recipe r1 = MyrecipeList.GetRandomMainDish();
    recipe r2 = MyrecipeList.GetRandomSubDish();
    recipe r3 = MyrecipeList.GetRandomSubDish();
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomrecipes);

        LeftImage = findViewById(R.id.RandomImageLeft);
        MiddleImage = findViewById(R.id.RandomImageMiddle);
        RightImage = findViewById(R.id.RandomImageRight);
        //Log.d("", "Calorie2"+Calorie);
    }
    @Override
    protected void onStart() {
        CreateProgress();
        super.onStart();
    }

    public void Random(View view) {
        if(animetime<=0){
            animetime=3000;
            new Thread(){
                public void run(){
                    super.run();

                    while (animetime>0){
                        int duration = RandomAnimeduration(animetime);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                 r1 = MyrecipeList.GetRandomMainDish();
                                 r2 = MyrecipeList.GetRandomSubDish();
                                 r3 = MyrecipeList.GetRandomSubDish();
                                while (r3.ImageId==r2.ImageId)r3=MyrecipeList.GetRandomSubDish();
                                LeftImage.setImageResource(r1.ImageId);
                                MiddleImage.setImageResource(r2.ImageId);
                                RightImage.setImageResource(r3.ImageId);
                            }
                        });
                        try {
                            Thread.sleep(duration);
                            //Log.d("", "duration"+duration2());
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        animetime-=duration;
                        //if(animetime<=0)ModifyTempCalorie(r1.perCalorie+r2.perCalorie+r3.perCalorie);
                        //Log.d("", "animetime"+animetime);
                    }
                    int maxcalorie = GetMaxCalorie();//==1866
                    Log.d("", "CalorieTempCurrentEnd"+r1.GetRecipeCalorie(maxcalorie)+" "+r2.GetRecipeCalorie(maxcalorie)+" "+r3.GetRecipeCalorie(maxcalorie));
                    ModifyTempCalorie(r1.GetRecipeCalorie(maxcalorie)+r2.GetRecipeCalorie(maxcalorie)+r3.GetRecipeCalorie(maxcalorie));
                }
            }.start();
        }


        //ModifyTempCalorie(r1.perCalorie+r2.perCalorie+r3.perCalorie);
        //Log.d("", "RecipeList"+MyrecipeList.GetImageId(0));
    }


}