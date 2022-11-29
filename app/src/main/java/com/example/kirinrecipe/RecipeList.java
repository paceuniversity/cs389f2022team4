package com.example.kirinrecipe;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

enum RecipeType
{
    Seafood,
    Pork,
    Beef,
    Mutton,
    Vegetables,
    Poultry,
    Soup,
}
public class RecipeList {
    recipe[] list = new recipe[10];
    RecipeList(){
        list[0]=new recipe(R.drawable.scalded_prawns,150,RecipeType.Seafood,false);
        list[1]=new recipe(R.drawable.char_siu,200,RecipeType.Pork,true);
        list[2]=new recipe(R.drawable.braised_pork_balls_in_gravy,261,RecipeType.Pork,true);
        list[3]=new recipe(R.drawable.stir_fried_pork_with_scallions,143,RecipeType.Seafood,true);
        list[4]=new recipe(R.drawable.sauted_meat_shreds_with_soy_bean_paste,162,RecipeType.Pork,true);
        list[5]=new recipe(R.drawable.grilled_eggplant,65,RecipeType.Vegetables,false);
        list[6]=new recipe(R.drawable.fish_head_with_minced_pepper,113,RecipeType.Vegetables,true);
        list[7]=new recipe(R.drawable.granny_smith,89,RecipeType.Vegetables,false);
        list[8]=new recipe(R.drawable.roast_duck,269,RecipeType.Poultry,true);
        list[9]=new recipe(R.drawable.lamb_soup,62,RecipeType.Soup,false);
    }
    public ArrayList GetAllSpecific(RecipeType R,boolean maindish){

        int Amount = 0;
        ArrayList <recipe> result = new ArrayList<>();
        for(recipe r : list){

            if(r.type==R){
                if(maindish){
                    if(r.MainDish){
                        Amount++;
                        result.add(r);
                    }
                }
                else{
                    if(!r.MainDish){
                        Amount++;
                        result.add(r);
                    }
                }
            }
        }
        if(Amount>0)return result;
        return null;
    }
    public ArrayList GetAllSubDish(){
        ArrayList <recipe> result = new ArrayList<>();
        for(recipe r : list){
            if(!r.MainDish)result.add(r);
        }
        return result;
    }
    public ArrayList GetAllRecommendSubDish(recipe maindish){
        ArrayList <recipe> result = GetAllSubDish();
        for(recipe subdish: result){
            subdish.Pivot+=AddPivotToRecipeBaseOnOil(maindish,subdish);
            //Log.d("", "SubDishPivot"+ subdish.Pivot);
            if(subdish.type!=maindish.type&& (subdish.type==RecipeType.Vegetables|maindish.type==RecipeType.Vegetables)){
                //have one and only one vegetables
                subdish.Pivot*=(1.5+ 3*(1-2*Math.random()));
            }
        }
        for(recipe r : result){
            //Log.d("", "RecipeNoSort"+ r.Pivot);
        }
        return result;
    }
    public ArrayList GetSortedRecommendSubDish(recipe maindish){
        ArrayList <recipe> Temp = GetAllRecommendSubDish(maindish);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collections.sort(Temp,Comparator.comparingInt(recipe::getPivot));
        }
        for(recipe r : Temp){
            Log.d("", "Recipe"+ r.Pivot);
        }
        return Temp;
    }

    public float AddPivotToRecipeBaseOnOil(recipe maindish,recipe subdish){
        float result=0;
        int totaloil=maindish.oil+subdish.oil;//0 10 20 || 0 100 -100
        if(totaloil<=10){
            result= lerp(100,0,(10-totaloil)/10f);
        }
        else result = lerp(400,100,(totaloil)/10f);
        return result;

    }
    float lerp (float x,float y,float weight){
        return (x+(y-x)*weight);
    }

    public recipe FindRecipeByID(int ImageId){
        for(recipe temp : list){
            if(temp.ImageId==ImageId){
                return temp;
            }
        }
        return null;
    }

    public int GetImageId(int index){
        return list[index].ImageId;
    }
    public recipe GetRandomRecipe(){
        int random = (int)(Math.random()*10);
        return list[random];
    }
    public recipe GetRandomMainDish(){
        recipe result=GetRandomRecipe();
        while (!result.MainDish){
            result=GetRandomRecipe();
        }
        return result;
    }
    public recipe GetRandomSubDish(){
        recipe result = GetRandomRecipe();
        while (result.MainDish){
            result=GetRandomRecipe();
        }
        return  result;
    }

    public RecipeType translate(String s){
        switch (s){
            case "Beef": return RecipeType.Beef;
            case "Pork": return RecipeType.Pork;
            case "Mutton": return RecipeType.Mutton;
            case "Seafood": return RecipeType.Seafood;
            case "Poultry": return RecipeType.Poultry;
            case "Vegetables": return RecipeType.Vegetables;
            default: return RecipeType.Beef;
        }
    }

}
class recipe{
    int ImageId;
    int perCalorie;
    RecipeType type;
    boolean MainDish;
    float Pivot=0;
    int oil;
    recipe(){}

    public int getPivot(){return (int)Pivot;}
    recipe(int ImageId, int perCalorie,RecipeType type,boolean mainDish){
        this.ImageId=ImageId;
        this.perCalorie = perCalorie;
        this.type=type;
        this.MainDish= mainDish;
    }
    int GetRecipeCalorie(int MaxCalorie){
        double PerDishMaxCalorie=0;
        double result=0;
        double RecipeNeedCalorie=0;
        //Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>10&&Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<14
        if(true){
            PerDishMaxCalorie=MaxCalorie*0.6f;
        }
        else{
            PerDishMaxCalorie=MaxCalorie*0.4f;
        }
        if(MainDish){
            if(type==RecipeType.Vegetables){
                RecipeNeedCalorie = PerDishMaxCalorie*0.4f;
            }
            else RecipeNeedCalorie = PerDishMaxCalorie*0.7f;
        }
        else{
            if(type==RecipeType.Soup){
                RecipeNeedCalorie = PerDishMaxCalorie*0.1f;
            }
            else if (type==RecipeType.Vegetables){
                RecipeNeedCalorie = PerDishMaxCalorie*0.15f;
            }
            else{
                RecipeNeedCalorie = PerDishMaxCalorie*0.35f;
            }
        }
        while(result<RecipeNeedCalorie-perCalorie){
            result+=perCalorie;
        }
        return (int) result;
    }
    int GetRecipeAmount(int MaxCalorie){
        return GetRecipeCalorie(MaxCalorie)/perCalorie;
    }
}
