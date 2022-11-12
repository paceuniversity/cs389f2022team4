package com.example.kirinrecipe;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class Userinfo extends AppCompatActivity {
    public User user;
    public String ID;
    private EditText editName, editID, editGender, editAge, editHeight, editWeight, editFavorite, editDislike;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        //Link to EditText
        editName = (EditText) findViewById(R.id.name_text);
        editGender = (EditText) findViewById(R.id.gender_text);
        editAge = (EditText) findViewById(R.id.age_text);
        editHeight = (EditText) findViewById(R.id.height_text);
        editWeight = (EditText) findViewById(R.id.weight_text);
        editFavorite = (EditText) findViewById(R.id.favorite_text);
        editDislike = (EditText) findViewById(R.id.dislike_text);
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);

        //Add a listener to the submit button to get the EditText text content and jump to the Homepage.
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //get text
                String strName = editName.getText().toString().trim();
                String strGender = editGender.getText().toString().trim();
                String strAge = editAge.getText().toString().trim();
                String strHeight = editHeight.getText().toString().trim();
                String strWeight = editWeight.getText().toString().trim();
                String strFavorite = editFavorite.getText().toString().trim();
                String strDislike = editDislike.getText().toString().trim();

                Intent intent = new Intent(Userinfo.this, HomePage.class);
                /*Wrong code, need to fix.
                //create a data packet
                Bundle data=new Bundle();
                //Put text data into packets
                data.putString("Name",strName);
                data.putString("Gender",strGender);
                data.putString("age",strAge);
                data.putString("height",strHeight);
                data.putString("weight",strWeight);
                data.putString("favorite",strFavorite);
                data.putString("dislike",strDislike);
                //Carry packets through intent
                intent.putExtras(data);

                //Update the user content, but the database has not been established, and it is not yet possible to achieve.
                user.setName(strName);
                user.setGender(strGender);
                user.setHeight(Double.parseDouble(strHeight));
                user.setWeight(Double.parseDouble(strWeight));
                user.setAge(Integer.parseInt(strAge));
                user.setFavorite(strFavorite);
                user.setDislike(strDislike);*/

                startActivity(intent);


            }
        });



    }


    /*public void SaveandNext(View view) {
        user.setName(String.valueOf(editName.getText()));
        user.setGender(String.valueOf(editGender.getText()));
        user.setFavorite(String.valueOf(editFavorite.getText()));
        user.setDislike(String.valueOf(editDislike.getText()));
        user.setAge(Integer.parseInt(String.valueOf(editAge.getText())));
        user.setHeight(Double.parseDouble(String.valueOf(editHeight.getText())));
        user.setWeight(Double.parseDouble(String.valueOf(editWeight.getText())));
        Intent intent = new Intent(Userinfo.this, HomePage.class);
        startActivity(intent);
    }*/

}

