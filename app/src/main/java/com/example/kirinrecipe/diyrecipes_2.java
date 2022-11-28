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
    private ImageView MainRecipe, BackImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes2);
        MainRecipe = (ImageView) findViewById(R.id.MainImage);
        MainRecipe.setImageDrawable(ImageList[0]);
        BackImage = (ImageView) findViewById(R.id.Back2);
        BackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TempCalorie = 0;
                ModifyTempCalorie(TempCalorie);
                Intent intent=new Intent(diyrecipes_2.this,DIYrecipes.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();
    }
}