package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DIYrecipes extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes);

        CreateProgress();
    }
}