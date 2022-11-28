package com.example.kirinrecipe;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    //Create a user class to store user information.
    private String Name,Gender,Favorite,Dislike,ID;
    private double Weight,Height;
    private int Age;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    public User(){
        Name = null;
        Gender = null;
        Favorite = null;
        Dislike = null;
        ID= null;
    }

    public User(String Name, String Gender, String Favorite, String Dislike,String ID,
                double Weight, double Height, int Age){
        this.Name = Name;
        this.Gender = Gender;
        this.Favorite = Favorite;
        this.Dislike = Dislike;
        this.ID = ID;
        this.Weight = Weight;
        this.Height = Height;
        this.Age = Age;
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public double getWeight(){
        return Weight;
    }

    public void setWeight(Double weight){
        this.Weight = weight;
    }

    public double getHeight(){
        return Height;
    }

    public void setHeight(Double height){
        this.Height = height;
    }

    public int getAge(){
        return Age;
    }

    public void setAge(Integer age){
        this.Age = age;
    }

    public String getGender(){
        return Gender;
    }

    public void setGender(String gender){
        this.Gender = gender;
    }

    public String getFavorite(){
        return Favorite;
    }

    public void setFavorite(String favorite){
        this.Favorite = favorite;
    }

    public String getDislike(){
        return Dislike;
    }

    public void setDislike(String dislike){
        this.Dislike = dislike;
    }

    public void updateInfo(){
        DatabaseReference myRef = db.getReference();
        myRef.child("users").child(ID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    setName(String.valueOf(task.getResult().child("Name").getValue()));
                    setGender(String.valueOf(task.getResult().child("Gender").getValue()));
                    setAge(Integer.parseInt(String.valueOf(task.getResult().child("Age").getValue())));
                    setHeight(Double.parseDouble((String) task.getResult().child("Height").getValue()));
                    setWeight(Double.parseDouble((String) task.getResult().child("Weight").getValue()));
                    setFavorite(String.valueOf(task.getResult().child("Favorite").getValue()));
                    setDislike(String.valueOf(task.getResult().child("Dislike").getValue()));
                }
            }
        });

    }




}
