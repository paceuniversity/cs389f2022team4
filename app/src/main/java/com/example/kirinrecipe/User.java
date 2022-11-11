package com.example.kirinrecipe;

public class User {
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

    public void setWeight(double weight){
        this.Weight = weight;
    }

    public void setHeight(double height){
        this.Height = height;
    }

    public void setAge(int age){
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
