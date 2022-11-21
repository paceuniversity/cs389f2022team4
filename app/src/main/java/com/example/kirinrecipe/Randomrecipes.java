package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

public class Randomrecipes extends BaseActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomrecipes);
        Log.d("", "Calorie2"+Calorie);
    }
    @Override
    protected void onStart() {
        CreateProgress();
        super.onStart();
    }
}