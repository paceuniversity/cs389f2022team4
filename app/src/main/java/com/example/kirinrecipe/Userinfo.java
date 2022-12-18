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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Userinfo extends AppCompatActivity {
    private EditText editName, editID, editAge, editHeight, editWeight;
    private Spinner editGender, editFavorite, editDislike;
    private Button btnSubmit;
    //FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        //Link to EditText
        editName = (EditText) findViewById(R.id.name_text);
        editGender = (Spinner) findViewById(R.id.gender_text);
        editAge = (EditText) findViewById(R.id.age_text);
        editHeight = (EditText) findViewById(R.id.height_text);
        editWeight = (EditText) findViewById(R.id.weight_text);
        editFavorite = (Spinner) findViewById(R.id.favorite_text);
        editDislike = (Spinner) findViewById(R.id.dislike_text);
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);

        //Add a listener to the submit button to get the EditText text content and jump to the Homepage.
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //get text
                String strName = editName.getText().toString().trim();
                String strGender = editGender.getSelectedItem().toString().trim();
                String strAge = editAge.getText().toString().trim();
                String strHeight = editHeight.getText().toString().trim();
                String strWeight = editWeight.getText().toString().trim();
                String strFavorite = editFavorite.getSelectedItem().toString().trim();
                String strDislike = editDislike.getSelectedItem().toString().trim();

                FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                //new User(strName,strGender,strFavorite,strDislike,uid,18,18,18,true);
                DatabaseReference myRef = db.getReference();
                //myRef.child("users").child(uid).setValue(us);


                if(strName.length()>0 && strAge.length()>0 && strHeight.length()>0 && strWeight.length()>0
                        && !strGender.equals("Choose your Gender here") && !strFavorite.equals("Choose your favorite type here") &&
                        !strDislike.equals("Choose your dislike type here")){
                    myRef.child("users").child(uid).child("Name").setValue(strName);
                    myRef.child("users").child(uid).child("Gender").setValue(strGender);
                    myRef.child("users").child(uid).child("Age").setValue(strAge);
                    myRef.child("users").child(uid).child("Height").setValue(strHeight);
                    myRef.child("users").child(uid).child("Weight").setValue(strWeight);
                    myRef.child("users").child(uid).child("Favorite").setValue(strFavorite);
                    myRef.child("users").child(uid).child("Dislike").setValue(strDislike);
                    myRef.child("users").child(uid).child("Calories").setValue(String.valueOf(0));
                    myRef.child("users").child(uid).child("Date").setValue(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
                    String Name,  Gender,  Favorite,  Dislike, ID;
                    double Weight,  Height;
                    int Age, Calories;
                    Name = strName;
                    Gender = strGender;
                    Favorite = strFavorite;
                    Dislike = strDislike;
                    ID = uid;
                    Weight = Double.valueOf(strWeight);
                    Height = Double.valueOf(strHeight);
                    Age = Integer.valueOf(strAge);
                    Calories = 0;
                    Splash.Myuser = new User(Name,Gender,Favorite,Dislike,ID,Weight,Height,Age, Calories);
                    Splash.Myuser.setCalories(0);
                    Intent intent = new Intent(Userinfo.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast toast;
                    toast = Toast.makeText(Userinfo.this,"You must input all information here!",Toast.LENGTH_SHORT);
                    toast.show();
                }

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

