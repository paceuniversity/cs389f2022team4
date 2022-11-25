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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Userinfo extends AppCompatActivity {
    private EditText editName, editID, editGender, editAge, editHeight, editWeight, editFavorite, editDislike;
    private Button btnSubmit;
    //FirebaseDatabase db = FirebaseDatabase.getInstance();

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

                FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                User us =new User("Xiaofeng","male","strFavorite","strDislike",uid,18,18,18,true); //new User(strName,strGender,strFavorite,strDislike,uid,18,18,18,true);
                DatabaseReference myRef = db.getReference();
                //myRef.child("users").child(uid).setValue(us);
                myRef.child("users").child(uid).child("Name").setValue(strName);
                myRef.child("users").child(uid).child("Gender").setValue(strGender);
                myRef.child("users").child(uid).child("Age").setValue(strAge);
                myRef.child("users").child(uid).child("Height").setValue(strHeight);
                myRef.child("users").child(uid).child("Weight").setValue(strWeight);
                myRef.child("users").child(uid).child("Favorite").setValue(strFavorite);
                myRef.child("users").child(uid).child("Dislike").setValue(strDislike);

                if(strName.length()>0){
                    Intent intent = new Intent(Userinfo.this, HomePage.class);
                    startActivity(intent);
                }
                else{
                    Toast toast;
                    toast = Toast.makeText(Userinfo.this,"You must input name!",Toast.LENGTH_SHORT);
                    toast.show();
                }
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

