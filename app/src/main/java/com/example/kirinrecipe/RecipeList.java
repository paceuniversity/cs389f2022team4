package com.example.kirinrecipe;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

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
    recipe[] list = new recipe[31];
    RecipeList(){
        list[0]=new recipe(R.drawable.scalded_prawns,150,RecipeType.Seafood,false);
        list[1]=new recipe(R.drawable.char_siu,206,RecipeType.Pork,true);
        list[2]=new recipe(R.drawable.braised_pork_balls_in_gravy,261,RecipeType.Pork,true);
        list[3]=new recipe(R.drawable.stir_fried_pork_with_scallions,143,RecipeType.Pork,true);
        list[4]=new recipe(R.drawable.sauted_meat_shreds_with_soy_bean_paste,162,RecipeType.Pork,true);
        list[5]=new recipe(R.drawable.grilled_eggplant,65,RecipeType.Vegetables,false);
        list[6]=new recipe(R.drawable.fish_head_with_minced_pepper,113,RecipeType.Seafood,true);
        list[7]=new recipe(R.drawable.granny_smith,89,RecipeType.Vegetables,false);
        list[8]=new recipe(R.drawable.roast_duck,269,RecipeType.Poultry,true);
        list[9]=new recipe(R.drawable.lamb_soup,62,RecipeType.Soup,false);
        list[10]=new recipe(R.drawable.cumin_lamb,142,RecipeType.Mutton,true);
        list[11]=new recipe(R.drawable.chinese_yam_in_hot_toffee,321,RecipeType.Vegetables,false);
        list[12]=new recipe(R.drawable.creamy_chinese_cabbage_in_soup,97,RecipeType.Vegetables,true);
        list[13]=new recipe(R.drawable.braised_eggplant,69,RecipeType.Vegetables,true);
        list[14]=new recipe(R.drawable.braised_beef_brisket,232,RecipeType.Beef,true);
        list[15]=new recipe(R.drawable.wenchang_coconut_chicken,144,RecipeType.Poultry,false);
        list[16]=new recipe(R.drawable.dongpo_pork,463,RecipeType.Pork,true);
        list[17]=new recipe(R.drawable.stir_fried_pork_with_green_peppers,101,RecipeType.Pork,false);
        list[18]=new recipe(R.drawable.poached_young_chinese_cabbage,11,RecipeType.Vegetables,false);
        list[19]=new recipe(R.drawable.steamed_yellow_croaker,209,RecipeType.Seafood,true);
        list[20]=new recipe(R.drawable.hakka_stuffed_tofu,89,RecipeType.Vegetables,true);
        list[21]=new recipe(R.drawable.sweet_and_sour_pork_tenderloin,321,RecipeType.Pork,false);
        list[22]=new recipe(R.drawable.ma_po_tofu,119,RecipeType.Vegetables,true);
        list[23]=new recipe(R.drawable.double_cooked_pork,315,RecipeType.Pork,true);
        list[24]=new recipe(R.drawable.kung_pao_chicken,268,RecipeType.Poultry,true);
        list[25]=new recipe(R.drawable.sliced_beef_and_ox_tongue_in_chilli_sauce,138,RecipeType.Beef,false);
        list[26]=new recipe(R.drawable.west_lake_fried_fish_in_vinegar,122,RecipeType.Seafood,true);
        list[27]=new recipe(R.drawable.braised_chicken_with_scallion,145,RecipeType.Poultry,true);
        list[28]=new recipe(R.drawable.egg_seaweed_soup,36,RecipeType.Soup,false);
        list[29]=new recipe(R.drawable.lamb_stew_with_carrots,144,RecipeType.Mutton,true);
        list[30]=new recipe(R.drawable.beef_brisket_with_tomatoes_in_casserole_beef,141,RecipeType.Beef,true);

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
