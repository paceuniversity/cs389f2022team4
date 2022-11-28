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

public class UpdateUserinfo extends AppCompatActivity {
    private EditText editName, editID, editAge, editHeight, editWeight;
    private Spinner editGender, editFavorite, editDislike;
    private Button btnSubmit;
    //FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_userinfo);
        //Link to EditText
        editName = (EditText) findViewById(R.id.name_text3);
        editGender = (Spinner) findViewById(R.id.gender_text3);
        editAge = (EditText) findViewById(R.id.age_text3);
        editHeight = (EditText) findViewById(R.id.height_text3);
        editWeight = (EditText) findViewById(R.id.weight_text3);
        editFavorite = (Spinner) findViewById(R.id.favorite_text3);
        editDislike = (Spinner) findViewById(R.id.dislike_text3);
        btnSubmit = (Button) findViewById(R.id.buttonSubmit);

        editName.setText(Splash.Myuser.getName());
        editAge.setText(String.valueOf(Splash.Myuser.getAge()));
        editHeight.setText(Splash.Myuser.getHeight()+"" );
        editWeight.setText(Splash.Myuser.getWeight()+""  );

        //Add a listener to the submit button to get the EditText text content and jump to the Homepage.
        btnSubmit.setOnClickListener(new View.OnClickListener() {

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
                if (strName.length() > 0) {
                    Splash.Myuser.setName(strName);
                }
                if (!strGender.equals("Choose your Gender here") ) {
                    Splash.Myuser.setGender(strGender);
                }

                if (strAge.length() > 0) {
                    Splash.Myuser.setAge(Integer.valueOf(strAge));
                }

                if (strHeight.length() > 0) {
                    Splash.Myuser.setHeight(Double.valueOf(strHeight));
                }

                if (strWeight.length() > 0) {
                    Splash.Myuser.setWeight(Double.valueOf(strWeight));
                }

                if (strFavorite!=("Choose your favorite type here")) {
                    Splash.Myuser.setFavorite(strFavorite);
                }

                if (strDislike!=("Choose your dislike type here")) {
                    Splash.Myuser.setDislike(strDislike);
                }

                if (strName.equals(Splash.Myuser.getName()) && strGender.equals("Choose your Gender here") && strAge.equals(String.valueOf(Splash.Myuser.getAge())) &&
                        strHeight.equals(String.valueOf(Splash.Myuser.getHeight()))&& strWeight.equals(String.valueOf(Splash.Myuser.getWeight())) &&
                        strFavorite.equals("Choose your favorite type here") &&
                        strDislike.equals("Choose your dislike type here")) {
                    Toast toast;
                    toast = Toast.makeText(UpdateUserinfo.this, "You must change at least 1 info", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Intent intent = new Intent(UpdateUserinfo.this, HomePage.class);
                    Splash.Myuser.updateInfo();
                    startActivity(intent);
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
    public void GotoSetting3(View view) {
        Intent intent=new Intent(UpdateUserinfo.this,Settingpage.class);
        startActivity(intent);
    }
}