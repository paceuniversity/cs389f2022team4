package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.FirebaseDatabase;

public class diyrecipes_2 extends BaseActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private ImageView MainRecipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes2);
        MainRecipe = (ImageView) findViewById(R.id.MainImage);
        MainRecipe.setImageDrawable(ImageList[0]);

    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();
    }
}