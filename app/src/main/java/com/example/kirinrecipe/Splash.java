package com.example.kirinrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

// welcome page by Kuan
public class Splash extends AppCompatActivity {
    boolean firstin=true;
    public static User Myuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Timer();
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //Log.e("firebase", "time"+ Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        if(user!=null){
            String uid = user.getUid();

            DatabaseReference myRef = db.getReference();
            myRef.child("users").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        if(String.valueOf(task.getResult().child("Name").getValue())!="null"){
                            firstin=false;
                            String Name,  Gender,  Favorite,  Dislike, ID;
                            double Weight,  Height;
                            int Age;
                            Name = String.valueOf(task.getResult().child("Name").getValue());
                            Gender = String.valueOf(task.getResult().child("Gender").getValue());
                            Favorite = String.valueOf(task.getResult().child("Favorite").getValue());
                            Dislike = String.valueOf(task.getResult().child("Dislike").getValue());
                            ID = uid;
                            Weight = Double.valueOf(task.getResult().child("Weight").getValue().toString());
                            Height = Double.valueOf(task.getResult().child("Height").getValue().toString());
                            Age = Integer.valueOf(task.getResult().child("Age").getValue().toString());
                            Myuser = new User(Name,Gender,Favorite,Dislike,ID,Weight,Height,Age);
                        }
                    }
                }
            });
        }

    }
    private void Timer() {
    //Set a time control to automatically go to the next page.
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                    if(firstin){
                        Intent intent = new Intent(Splash.this, Login.class);
                        startActivity(intent);
                        Splash.this.finish();
                    }
                    else{
                        Intent intent = new Intent(Splash.this, HomePage.class);
                        startActivity(intent);
                        Splash.this.finish();
                    }


            }
        };
        timer.schedule(timerTask,3000);
    }
}