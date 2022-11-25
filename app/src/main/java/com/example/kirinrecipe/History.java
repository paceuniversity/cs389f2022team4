package com.example.kirinrecipe;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class History {
    private int historyid;
    private ArrayList<Collocation> history;


    public History(){
        history = new ArrayList<>();
    }

    public History(int id, ArrayList<Collocation> history){
        this.historyid = id;
        this.history = history;
    }

    public int getHistoryid(){
        return historyid;
    }

    public void setHistoryId(int id){
        this.historyid = id;
    }

    public ArrayList<Collocation> getHistory(){
        return history;
    }

    public void addCollocation(Collocation C1){
        history.add(C1);
    }



}
