package com.example.kirinrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
    private ImageView Confirm,LeftInfo,MiddleInfo,RightInfo;
    int maxcalorie = GetMaxCalorie();
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomrecipes);

        LeftImage = findViewById(R.id.RandomImageLeft);
        MiddleImage = findViewById(R.id.RandomImageMiddle);
        RightImage = findViewById(R.id.RandomImageRight);
        LeftInfo = findViewById(R.id.RandomImageLeftInfo);
        MiddleInfo = findViewById(R.id.RandomImageMiddleInfo);
        RightInfo = findViewById(R.id.RandomImageRightInfo);
        //Log.d("", "Calorie2"+Calorie);
        Confirm = (ImageView) findViewById(R.id.RandomConfirmButton);

        while (r3.ImageId==r2.ImageId)r3=MyrecipeList.GetRandomSubDish();
        LeftImage.setImageResource(r1.ImageId);
        MiddleImage.setImageResource(r2.ImageId);
        RightImage.setImageResource(r3.ImageId);
        ModifyTempCalorie(r1.GetRecipeCalorie(maxcalorie)+r2.GetRecipeCalorie(maxcalorie)+r3.GetRecipeCalorie(maxcalorie));

        LeftInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GoToInfo(r1);
            }
        });
        MiddleInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GoToInfo(r2);
            }
        });
        RightInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GoToInfo(r3);
            }
        });

        Confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LinkRecipeList[0] = r1;
                LinkRecipeList[1] = r2;
                LinkRecipeList[2] = r3;

                TempCalorie=0;
                AnimateTempCalorie=0;
                progressbar.setSecondaryProgress(0);
                AddCalorie(r1.GetRecipeCalorie(maxcalorie)+r2.GetRecipeCalorie(maxcalorie)+r3.GetRecipeCalorie(maxcalorie));
                Calorie = r1.GetRecipeCalorie(maxcalorie)+r2.GetRecipeCalorie(maxcalorie)+r3.GetRecipeCalorie(maxcalorie);
                DatabaseReference myRef = db.getReference();
                myRef.child("users").child(uid).child("Calories").setValue(String.valueOf(Calorie));
                Intent intent = new Intent(Randomrecipes.this, Step_by_step.class);
                startActivity(intent);
            }
        });

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
                    //==1866
                    Log.d("", "CalorieTempCurrentEnd"+r1.GetRecipeCalorie(maxcalorie)+" "+r2.GetRecipeCalorie(maxcalorie)+" "+r3.GetRecipeCalorie(maxcalorie));
                    ModifyTempCalorie(r1.GetRecipeCalorie(maxcalorie)+r2.GetRecipeCalorie(maxcalorie)+r3.GetRecipeCalorie(maxcalorie));
                }
            }.start();
        }


        //ModifyTempCalorie(r1.perCalorie+r2.perCalorie+r3.perCalorie);
        //Log.d("", "RecipeList"+MyrecipeList.GetImageId(0));
    }

    public void GoToInfo(recipe r){
        recipeInfo = r;
        //Log.d("recipeInfo", "recipeInfo"+ recipeInfo.type);
        Intent intent=new Intent(Randomrecipes.this,MoreInfo.class);
        startActivity(intent);
    }

}