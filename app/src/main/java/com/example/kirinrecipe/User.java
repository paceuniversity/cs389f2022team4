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
        //myRef.child("users").child(uid).setValue(us);
        myRef.child("users").child(ID).child("Name").setValue(Name);
        myRef.child("users").child(ID).child("Gender").setValue(Gender);
        myRef.child("users").child(ID).child("Age").setValue(Age);
        myRef.child("users").child(ID).child("Height").setValue(Height);
        myRef.child("users").child(ID).child("Weight").setValue(Weight);
        myRef.child("users").child(ID).child("Favorite").setValue(Favorite);
        myRef.child("users").child(ID).child("Dislike").setValue(Dislike);
    }




}
