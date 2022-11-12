package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Basicinformation extends AppCompatActivity {
    private TextView infoName, infoID, infoGender, infoAge, infoHeight, infoWeight, infoFavorite, infoDislike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinformation);

        infoName=(TextView) findViewById(R.id.name_text2);
        infoGender=(TextView)findViewById(R.id.gender_text2);
        infoAge=(TextView)findViewById(R.id.age_text2);
        infoHeight=(TextView)findViewById(R.id.height_text2);
        infoWeight=(TextView)findViewById(R.id.weight_text2);
        infoFavorite=(TextView) findViewById(R.id.favorite_text2);
        infoDislike=(TextView)findViewById(R.id.dislike_text2);

    }
    public void midToast() {
        Toast toast = Toast.makeText(Basicinformation.this, "xianshi", Toast.LENGTH_SHORT);
    }
    public void GotoSetting2(View view) {
        Intent intent=new Intent(Basicinformation.this,Settingpage.class);
        startActivity(intent);
    }
}