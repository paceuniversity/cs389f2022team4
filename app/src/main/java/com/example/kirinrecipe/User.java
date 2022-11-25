package com.example.kirinrecipe;

public class User {
    //Create a user class to store user information.
    private String Name,Gender,Favorite,Dislike,ID;
    private double Weight,Height;
    private int Age;
    private boolean firstUse;

    public User(){
        Name = null;
        Gender = null;
        Favorite = null;
        Dislike = null;
        ID= null;
        firstUse = false;
    }

    public User(String Name, String Gender, String Favorite, String Dislike, String ID,
                double Weight, double Height, int Age, boolean firstUse){
        this.Name = Name;
        this.Gender = Gender;
        this.Favorite = Favorite;
        this.Dislike = Dislike;
        this.ID = ID;
        this.Weight = Weight;
        this.Height = Height;
        this.Age = Age;
        this.firstUse = firstUse;
    }

    public boolean getFirstUse(){
        return firstUse;
    }

    public void setFirstUse(){
        firstUse = false;
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




}
