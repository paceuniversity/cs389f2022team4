package com.example.kirinrecipe;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

public class Userinfo extends AppCompatActivity {
    public User user;
    public String ID;
    private EditText editName, editID, editGender, editAge, editHeight, editWeight, editFavorite, editDislike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        /*editName = (EditText) findViewById(R.id.name_text);
        editGender = (EditText) findViewById(R.id.gender_text);
        editAge = (EditText) findViewById(R.id.age_text);
        editHeight = (EditText) findViewById(R.id.height_text);
        editWeight = (EditText) findViewById(R.id.weight_text);
        editFavorite = (EditText) findViewById(R.id.favorite_text);
        editDislike = (EditText) findViewById(R.id.dislike_text);*/

    }


    public void SaveandNext(View view) {
        /*user.setName(String.valueOf(editName.getText()));
        user.setGender(String.valueOf(editGender.getText()));
        user.setFavorite(String.valueOf(editFavorite.getText()));
        user.setDislike(String.valueOf(editDislike.getText()));
        user.setAge(Integer.parseInt(String.valueOf(editAge.getText())));
        user.setHeight(Double.parseDouble(String.valueOf(editHeight.getText())));
        user.setWeight(Double.parseDouble(String.valueOf(editWeight.getText())));*/
        Intent intent = new Intent(Userinfo.this, HomePage.class);
        startActivity(intent);
    }

}

