package com.example.kirinrecipe;

public class User {
    //Create a user class to store user information.
    public String Name,ID,Gender,Favorite,Dislike;
    public double Weight,Height;
    public int Age;
    public boolean firstUse = true;


    public void setFirstUse(){
        firstUse = false;
    }

    public void setName(String name){
        this.Name = name;
    }

    public void setWeight(Double weight){
        this.Weight = weight;
    }

    public void setHeight(Double height){
        this.Height = height;
    }

    public void setAge(Integer age){
        this.Age = age;
    }

    public void setGender(String gender){
        this.Gender = gender;
    }

    public void setFavorite(String favorite){
        this.Favorite = favorite;
    }

    public void setDislike(String dislike){
        this.Dislike = dislike;
    }

    public void setID(String id){
        this.ID = id;
    }

}
