package com.example.kirinrecipe;

import java.util.ArrayList;
import java.util.List;

public class Collocation {
    private int id;
    private ArrayList<recipe> RecpList;

    public Collocation(){
        this.RecpList = new ArrayList<>();
    }

    public Collocation(int id, ArrayList<recipe> RecpList){
        this.id = id;
        this.RecpList = RecpList;
    }

    public int getId(){
        return id;
    }

    public List<recipe> getRecpList(){
        return RecpList;
    }

    public void addRecipe(recipe r1){
        RecpList.add(r1);
    }

    public void setID(int ID){
        this.id = ID;
    }

}
