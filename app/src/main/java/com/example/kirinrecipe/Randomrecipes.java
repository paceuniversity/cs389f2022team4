package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.google.firebase.database.FirebaseDatabase;


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
        Log.d("", "Calorie2"+Calorie);
    }
    @Override
    protected void onStart() {
        CreateProgress();
        super.onStart();
    }

    public void Random(View view) {
        recipe r = MyrecipeList.GetRandomRecipe();
        LeftImage.setImageResource(r.ImageId);
        ModifyTempCalorie(r.perCalorie);
        Log.d("", "RecipeList"+MyrecipeList.GetImageId(0));
    }
}