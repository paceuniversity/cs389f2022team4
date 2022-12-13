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
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                            int Age, Calories;
                            Name = String.valueOf(task.getResult().child("Name").getValue());
                            Gender = String.valueOf(task.getResult().child("Gender").getValue());
                            Favorite = String.valueOf(task.getResult().child("Favorite").getValue());
                            Dislike = String.valueOf(task.getResult().child("Dislike").getValue());
                            ID = uid;
                            Weight = Double.valueOf(task.getResult().child("Weight").getValue().toString());
                            Height = Double.valueOf(task.getResult().child("Height").getValue().toString());
                            Age = Integer.valueOf(task.getResult().child("Age").getValue().toString());
                            Calories = Integer.valueOf(task.getResult().child("Calories").getValue().toString());
                            Myuser = new User(Name,Gender,Favorite,Dislike,ID,Weight,Height,Age,Calories);
                            if(String.valueOf(task.getResult().child("LikeRecipeList").getValue())!="null"){
                                for(int i=0;i<55;i++){
                                    Myuser.LikeRecipe[i]=Boolean.valueOf(task.getResult().child("LikeRecipeList").child("LikeRecipe?"+i).getValue().toString());
                                }
                            }
                            if(String.valueOf(task.getResult().child("History").getValue())!="null"){
                                String History = String.valueOf(task.getResult().child("History").getValue());
                                Pattern pattern0 = Pattern.compile("=");
                                Matcher findMatcher0 = pattern0.matcher(History);
                                ArrayList<Integer> equal2 = new ArrayList<Integer>();
                                int e2 = 0;
                                while(findMatcher0.find()) {
                                    equal2.add(findMatcher0.start());
                                    e2++;
                                }
                                int size = Integer.parseInt(History.substring(equal2.get(equal2.size()-1)+1,History.length()-2));
                                Log.d("History","history " + History + " " +  size);

                                for (int i = 0; i < size; i++){
                                    String count = String.valueOf(i + 1);
                                    String histories = String.valueOf(task.getResult().child("History").child(count).getValue());
                                    int [] equal = new int[3];
                                    int [] comma = new int[2];
                                    Pattern pattern1 = Pattern.compile("=");
                                    Matcher findMatcher = pattern1.matcher(histories);
                                    int j = 0;
                                    while(findMatcher.find()) {
                                        equal[j] = findMatcher.start();
                                        j++;
                                    }
                                    Pattern pattern2 = Pattern.compile(",");
                                    findMatcher = pattern2.matcher(histories);
                                    int c = 0;
                                    while(findMatcher.find()) {
                                        comma[c] = findMatcher.start();
                                        c++;
                                    }
                                    int [] history = new int[j];
                                    for (int m = 0; m < history.length; m++){
                                        if (m == 0){
                                            history[m] = Integer.parseInt(histories.substring(1,equal[m]));
                                        }else {
                                            int x = m-1;
                                            int commaIndex = comma[x] + 2;
                                            history[m] = Integer.parseInt(histories.substring(commaIndex,equal[m]));
                                        }
                                        Log.d("History","Key " + history[m]);
                                    }
                                    Log.d("History","Key " + histories);
                                    recipe [] TempHistory = new recipe[3];
                                    for (int n = 0; n < history.length; n++){
                                        TempHistory[n] = BaseActivity.MyrecipeList.FindRecipeByID(history[n]);
                                        //Log.d("History","Recipes " + TempHistory[n].ImageId + " " + TempHistory[n].type);
                                    }
                                    Splash.Myuser.setHistoryRecipe(TempHistory);
                                    //Log.d("History","Recipes " + Splash.Myuser.HistoryRecipe.get(i)[0].ImageId + " " + Splash.Myuser.HistoryRecipe.get(0)[0].type);
                                    //Log.d("History","Recipes " + Splash.Myuser.HistoryRecipe.get(i)[1].ImageId + " " + Splash.Myuser.HistoryRecipe.get(0)[1].type);
                                    //Log.d("History","Recipes " + Splash.Myuser.HistoryRecipe.get(i)[2].ImageId + " " + Splash.Myuser.HistoryRecipe.get(0)[2].type);
                                }


                            }
                            if (String.valueOf(task.getResult().child("Date").getValue())!="null" &&
                                    Integer.valueOf(String.valueOf(task.getResult().child("Date").getValue())) != Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
                                myRef.child("users").child(uid).child("Calories").setValue(0);
                                myRef.child("users").child(uid).child("Date").setValue(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
                                Myuser.setCalories(0);
                            }

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