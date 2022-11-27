package com.example.kirinrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Basicinformation extends AppCompatActivity {
    private TextView infoName, infoID, infoGender, infoAge, infoHeight, infoWeight, infoFavorite, infoDislike;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicinformation);

        //link to TextView in Basciinformation page.
        infoName=(TextView) findViewById(R.id.name_text2);
        infoGender=(TextView)findViewById(R.id.gender_text2);
        infoAge=(TextView)findViewById(R.id.age_text2);
        infoHeight=(TextView)findViewById(R.id.height_text2);
        infoWeight=(TextView)findViewById(R.id.weight_text2);
        infoFavorite=(TextView) findViewById(R.id.favorite_text2);
        infoDislike=(TextView)findViewById(R.id.dislike_text2);
        Button button3 = (Button)findViewById(R.id.button3);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference myRef = db.getReference();


            myRef.child("users").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        infoName.setText(String.valueOf(task.getResult().child("Name").getValue()));
                        infoGender.setText(String.valueOf(task.getResult().child("Gender").getValue()));
                        infoAge.setText(String.valueOf(task.getResult().child("Age").getValue()));
                        infoHeight.setText(String.valueOf(task.getResult().child("Height").getValue()));
                        infoWeight.setText(String.valueOf(task.getResult().child("Weight").getValue()));
                        infoFavorite.setText(String.valueOf(task.getResult().child("Favorite").getValue()));
                        infoDislike.setText(String.valueOf(task.getResult().child("Dislike").getValue()));
                 }
                }
            });



        /*wrong code, need to fix.
        //get intent
        Intent intent= getIntent();
        if(intent !=null){
            //Get the packet carried by the intent
            Bundle data =intent.getExtras();
            String strName=data.getString("name");
            String strGender=data.getString("gender");
            String strAge=data.getString("age");
            String strHeight=data.getString("height");
            String strWeight=data.getString("weight");
            String strFavorite=data.getString("favorite");
            String strDislike=data.getString("dislike");
            //Set text to display information
            infoName.setText(strName);
            infoGender.setText(strGender);
            infoAge.setText(strAge);
            infoHeight.setText(strHeight);
            infoWeight.setText(strWeight);
            infoFavorite.setText(strFavorite);
            infoDislike.setText(strDislike);

        }*/

    }
    public void GotoSetting2(View view) {
        Intent intent=new Intent(Basicinformation.this,Settingpage.class);
        startActivity(intent);
    }
}