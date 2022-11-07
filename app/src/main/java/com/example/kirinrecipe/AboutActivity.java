package com.example.kirinrecipe;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {
    public static final  int RESULT_CODE = 11;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_CODE) {

                    }
                });
    }

    public void onAboutOnClick(View view) {
        Intent intent = new Intent(AboutActivity.this, AboutInfoActivity.class);
        intent.putExtra("type","1");
        intentActivityResultLauncher.launch(intent);
    }

    public void onContactOnClick(View view) {
        Intent intent = new Intent(AboutActivity.this, AboutInfoActivity.class);
        intent.putExtra("type","2");
        intentActivityResultLauncher.launch(intent);
    }

    public void onCalorieOnClick(View view) {
        Intent intent = new Intent(AboutActivity.this, CalorieActivity.class);
        intentActivityResultLauncher.launch(intent);
    }
}