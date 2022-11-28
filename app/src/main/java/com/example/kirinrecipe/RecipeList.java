package com.example.kirinrecipe;

import java.util.ArrayList;
import java.util.Calendar;

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
        list[2]=new recipe(R.drawable.braised_pork_balls_in_gravy,300,RecipeType.Seafood,true);
        list[3]=new recipe(R.drawable.stir_fried_pork_with_scallions,143,RecipeType.Seafood,true);
        list[4]=new recipe(R.drawable.shredded_pork_in_beijing_sauce,162,RecipeType.Pork,true);
        list[5]=new recipe(R.drawable.grilled_eggplant,65,RecipeType.Vegetables,false);
        list[6]=new recipe(R.drawable.fish_head_with_minced_pepper,113,RecipeType.Vegetables,true);
        list[7]=new recipe(R.drawable.granny_smith,89,RecipeType.Vegetables,false);
        list[8]=new recipe(R.drawable.roast_duck,269,RecipeType.Poultry,true);
        list[9]=new recipe(R.drawable.braised_pork_ball_in_brown_sauce,261,RecipeType.Pork,true);
    }
    public ArrayList GetAllSpecific(RecipeType R){
        int Amount = 0;
        ArrayList <recipe> result = new ArrayList<>();
        for(recipe r : list){
            if(r.type==R){
                Amount++;
                result.add(r);
            }
        }
        if(Amount>0)return result;
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
    recipe(){}

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
            RecipeNeedCalorie = PerDishMaxCalorie*0.7f;
        }
        else{
            if(type==RecipeType.Soup){
                RecipeNeedCalorie = PerDishMaxCalorie*0.1f;
            }
            else{
                RecipeNeedCalorie = PerDishMaxCalorie*0.15f;
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
