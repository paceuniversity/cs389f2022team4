package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AboutInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_info);
        TextView infoTv = (TextView) findViewById(R.id.info_tv);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if(type.equals("1")){
            infoTv.setText("Team members:\nKuan Zhang\nXiaofeng Leng\nZhifu Chen");
        }

        if(type.equals("2")){
            infoTv.setText("Team Contact:\nczhifu1@gmail.com");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}