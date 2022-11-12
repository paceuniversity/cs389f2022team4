package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingpage);
    }

    public void GotoAbout(View view) {
        Intent intent=new Intent(Settingpage.this,About.class);
        startActivity(intent);
    }

    public void GotoPKwithus(View view) {
        Intent intent=new Intent(Settingpage.this,PKwithus.class);
        startActivity(intent);
    }

    public void GoBack(View view) {
        Intent intent=new Intent(Settingpage.this,HomePage.class);
        startActivity(intent);
    }
    public void Gotobasicinformation(View view) {
        Intent intent=new Intent(Settingpage.this,Basicinformation.class);
        startActivity(intent);
    }
}