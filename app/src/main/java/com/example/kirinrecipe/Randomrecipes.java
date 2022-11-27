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
        recipe r1 = MyrecipeList.GetRandomRecipe();
        recipe r2 = MyrecipeList.GetRandomRecipe();
        recipe r3 = MyrecipeList.GetRandomRecipe();
        LeftImage.setImageResource(r1.ImageId);
        MiddleImage.setImageResource(r2.ImageId);
        RightImage.setImageResource(r3.ImageId);
        ModifyTempCalorie(r1.perCalorie+r2.perCalorie+r3.perCalorie);


        //Log.d("", "RecipeList"+MyrecipeList.GetImageId(0));
    }

}