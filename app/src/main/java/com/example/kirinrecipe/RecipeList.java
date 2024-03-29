package com.example.kirinrecipe;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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
enum IfLike
{
    Normal,
    Like,
    Dislike
}
public class RecipeList {
    recipe[] list = new recipe[55];
    boolean[] LikeRecipe=new boolean[55];
    RecipeList(){
        list[0]=new recipe(R.drawable.scalded_prawns,150,RecipeType.Seafood,false,0);
        list[0].FullTextId= R.string.scalded_prawns;
        list[1]=new recipe(R.drawable.char_siu,206,RecipeType.Pork,true,8);
        list[1].FullTextId= R.string.char_siu;
        list[2]=new recipe(R.drawable.braised_pork_balls_in_gravy,261,RecipeType.Pork,true,7);
        list[2].FullTextId= R.string.braised_pork_balls_in_gravy;
        list[3]=new recipe(R.drawable.stir_fried_pork_with_scallions,143,RecipeType.Pork,true,5);
        list[3].FullTextId=R.string.stir_fried_pork_with_scallions;
        list[4]=new recipe(R.drawable.sauted_meat_shreds_with_soy_bean_paste,162,RecipeType.Pork,true,6);
        list[4].FullTextId=R.string.sauted_meat_shreds_with_soy_bean_paste;
        list[5]=new recipe(R.drawable.grilled_eggplant,65,RecipeType.Vegetables,false,9);
        list[5].FullTextId=R.string.grilled_eggplant;
        list[6]=new recipe(R.drawable.fish_head_with_minced_pepper,113,RecipeType.Seafood,true,8);
        list[6].FullTextId=R.string.fish_head_with_minced_pepper;
        list[7]=new recipe(R.drawable.granny_smith,89,RecipeType.Vegetables,false,2);
        list[7].FullTextId= R.string.granny_smith;
        list[8]=new recipe(R.drawable.roast_duck,269,RecipeType.Poultry,true,7);
        list[8].FullTextId=R.string.roast_duck;
        list[9]=new recipe(R.drawable.lamb_soup,62,RecipeType.Soup,false,3);
        list[9].FullTextId=R.string.lamb_soup;
        list[10]=new recipe(R.drawable.cumin_lamb,142,RecipeType.Mutton,true,6);
        list[10].FullTextId=R.string.cumin_lamb;

        list[11]=new recipe(R.drawable.chinese_yam_in_hot_toffee,321,RecipeType.Vegetables,false,6);
        list[11].FullTextId=R.string.chinese_yam_in_hot_toffee;

        list[12]=new recipe(R.drawable.creamy_chinese_cabbage_in_soup,97,RecipeType.Vegetables,true,0);
        list[12].FullTextId=R.string.creamy_chinese_cabbage_in_soup;

        list[13]=new recipe(R.drawable.braised_eggplant,69,RecipeType.Vegetables,true,6);
        list[13].FullTextId=R.string.braised_eggplant;

        list[14]=new recipe(R.drawable.braised_beef_brisket,232,RecipeType.Beef,true,7);
        list[14].FullTextId=R.string.braised_beef_brisket;

        list[15]=new recipe(R.drawable.wenchang_coconut_chicken,144,RecipeType.Poultry,false,2);
        list[15].FullTextId=R.string.wenchang_coconut_chicken;

        list[16]=new recipe(R.drawable.dongpo_pork,463,RecipeType.Pork,true,10);
        list[16].FullTextId=R.string.dongpo_pork;

        list[17]=new recipe(R.drawable.stir_fried_pork_with_green_peppers,101,RecipeType.Pork,false,5);
        list[17].FullTextId=R.string.stir_fried_pork_with_scallions;

        list[18]=new recipe(R.drawable.poached_young_chinese_cabbage,11,RecipeType.Vegetables,false,0);
        list[18].FullTextId=R.string.poached_young_chinese_cabbage;

        list[19]=new recipe(R.drawable.steamed_yellow_croaker,209,RecipeType.Seafood,true,4);
        list[19].FullTextId=R.string.steamed_yellow_croaker;

        list[20]=new recipe(R.drawable.hakka_stuffed_tofu,89,RecipeType.Vegetables,true,5);
        list[20].FullTextId=R.string.hakka_stuffed_tofu;

        list[21]=new recipe(R.drawable.sweet_and_sour_pork_tenderloin,267,RecipeType.Pork,false,5);
        list[21].FullTextId=R.string.sweet_and_sour_pork_tenderloin;

        list[22]=new recipe(R.drawable.ma_po_tofu,119,RecipeType.Vegetables,true,4);
        list[22].FullTextId=R.string.ma_po_tofu;

        list[23]=new recipe(R.drawable.double_cooked_pork,315,RecipeType.Pork,true,8);
        list[23].FullTextId=R.string.double_cooked_pork;

        list[24]=new recipe(R.drawable.kung_pao_chicken,268,RecipeType.Poultry,true,4);
        list[24].FullTextId=R.string.kung_pao_chicken;

        list[25]=new recipe(R.drawable.sliced_beef_and_ox_tongue_in_chilli_sauce,138,RecipeType.Beef,false,8);
        list[25].FullTextId=R.string.sliced_beef_and_ox_tongue_in_chilli_sauce;

        list[26]=new recipe(R.drawable.west_lake_fried_fish_in_vinegar,122,RecipeType.Seafood,true,6);
        list[26].FullTextId=R.string.west_lake_fried_fish_in_vinegar;

        list[27]=new recipe(R.drawable.braised_chicken_with_scallion,145,RecipeType.Poultry,true,7);
        list[27].FullTextId=R.string.braised_chicken_with_scallion;

        list[28]=new recipe(R.drawable.egg_seaweed_soup,36,RecipeType.Soup,false,0);
        list[28].FullTextId=R.string.egg_seaweed_soup;

        list[29]=new recipe(R.drawable.lamb_stew_with_carrots,144,RecipeType.Mutton,true,6);
        list[29].FullTextId=R.string.lamb_stew_with_carrots;

        list[30]=new recipe(R.drawable.beef_brisket_with_tomatoes_in_casserole_beef,141,RecipeType.Beef,true,4);
        list[30].FullTextId=R.string.beef_brisket_with_tomatoes_in_casserole_beef;

        list[31]=new recipe(R.drawable.pineapple_with_shrimp_balls,128,RecipeType.Seafood,true,3);
        list[31].FullTextId=R.string.pineapple_with_shrimp_balls;

        list[32]=new recipe(R.drawable.steamed_egg_with_shrimp,152,RecipeType.Seafood,false,2);
        list[32].FullTextId=R.string.steamed_egg_with_shrimp;

        list[33]=new recipe(R.drawable.roast_beef_with_potatoes,265,RecipeType.Beef,true,6);
        list[33].FullTextId=R.string.roast_beef_with_potatoes;

        list[34]=new recipe(R.drawable.cumin_beef,134,RecipeType.Beef,true,8);
        list[34].FullTextId=R.string.cumin_beef;

        list[35]=new recipe(R.drawable.stir_fried_beef_river_beef,148,RecipeType.Beef,true,8);
        list[35].FullTextId=R.string.stir_fried_beef_river_beef;

        list[36]=new recipe(R.drawable.moroccan_lamb_stew,133,RecipeType.Mutton,true,7);
        list[36].FullTextId=R.string.moroccan_lamb_stew;

        list[37]=new recipe(R.drawable.lamb_dumplings ,167,RecipeType.Mutton,true,4);
        list[37].FullTextId=R.string.lamb_dumplings;

        list[38]=new recipe(R.drawable.lamb_stew_with_white_radish,71,RecipeType.Mutton,true,3);
        list[38].FullTextId=R.string.lamb_stew_with_white_radish;

        list[39]=new recipe(R.drawable.braised_lamb,156,RecipeType.Mutton,true,9);
        list[39].FullTextId=R.string.braised_lamb;

        list[40]=new recipe(R.drawable.mushrooms_mixes_in_the_hot_pot,41,RecipeType.Vegetables,true,2);
        list[40].FullTextId=R.string.mushrooms_mixes_in_the_hot_pot;

        list[41]=new recipe(R.drawable.stewed_chicken_with_mushrooms,89,RecipeType.Poultry,true,3);
        list[41].FullTextId=R.string.stewed_chicken_with_mushrooms;

        list[42]=new recipe(R.drawable.braised_chicken,120,RecipeType.Poultry,true,6);
        list[42].FullTextId=R.string.braised_chicken;

        list[43]=new recipe(R.drawable.pickle_chicken_legs,191,RecipeType.Poultry,false,2);
        list[43].FullTextId=R.string.pickle_chicken_legs;

        list[44]=new recipe(R.drawable.three_cups_chicken,175,RecipeType.Poultry,true,7);
        list[44].FullTextId=R.string.three_cups_chicken;

        list[45]=new recipe(R.drawable.coke_chicken_wings,134,RecipeType.Poultry,true,7);
        list[45].FullTextId=R.string.coke_chicken_wings;

        list[46]=new recipe(R.drawable.lettuce_with_oyster_sauce,30,RecipeType.Vegetables,false,2);
        list[46].FullTextId=R.string.lettuce_with_oyster_sauce;

        list[47]=new recipe(R.drawable.steamed_chinese_cabbage_in_supreme_soup,20,RecipeType.Vegetables,false,1);
        list[47].FullTextId=R.string.steamed_chinese_cabbage_in_supreme_soup;

        list[48]=new recipe(R.drawable.stir_fried_pea_shoots_with_garlic,24,RecipeType.Vegetables,false,1);
        list[48].FullTextId=R.string.stir_fried_pea_shoots_with_garlic;

        list[49]=new recipe(R.drawable.vinegar_pepper_shredded_potatoes,120,RecipeType.Vegetables,true,1);
        list[49].FullTextId=R.string.vinegar_pepper_shredded_potatoes;

        list[50]=new recipe(R.drawable.sponge_gourd_with_eggs,99,RecipeType.Vegetables,true,3);
        list[50].FullTextId=R.string.sponge_gourd_with_eggs;

        list[51]=new recipe(R.drawable.grilled_fresh_squid,92,RecipeType.Seafood,true,6);
        list[51].FullTextId=R.string.grilled_fresh_squid;

        list[52]=new recipe(R.drawable.braised_sea_cucumber_with_scallion,112,RecipeType.Seafood,true,6);
        list[52].FullTextId=R.string.braised_sea_cucumber_with_scallion;

        list[53]=new recipe(R.drawable.steamed_scallop_with_garlic_and_vermicelli,127,RecipeType.Seafood,true,4);
        list[53].FullTextId=R.string.steamed_scallop_with_garlic_and_vermicelli;

        list[54]=new recipe(R.drawable.buddha_jumps_over_the_wall,175,RecipeType.Seafood,true,7);
        list[54].FullTextId=R.string.buddha_jumps_over_the_wall;

        for(int i=0;i<55;i++){
            list[i].iflike=Splash.Myuser.LikeRecipe[i];
        }

    }
    public ArrayList GetAllSpecific(RecipeType R,boolean maindish){

        int Amount = 0;
        ArrayList <recipe> result = new ArrayList<>();
        for(recipe r : list){
            if(r.iflike!=IfLike.Dislike){
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
        }
        int i=0;
        for(int j=0;j<result.size();j++){
            if(result.get(j).iflike==IfLike.Like){
                recipe temp=result.get(j);
                result.set(j,result.get(i));
                result.set(i++,temp);
            }
        }
        if(Amount>0)return result;
        return null;
    }
    public ArrayList GetAllSubDish(){
        ArrayList <recipe> result = new ArrayList<>();
        RecipeList cloneRecipe = new RecipeList();
        for(recipe r : cloneRecipe.list){
            if(!r.MainDish)result.add(r);
        }
        return result;
    }
    public ArrayList GetAllSubDish(recipe except){
        ArrayList <recipe> result = new ArrayList<>();
        RecipeList cloneRecipe = new RecipeList();
        for(recipe r : cloneRecipe.list){
            if(!r.MainDish&&r.iflike!=IfLike.Dislike&&r.ImageId!= except.ImageId)result.add(r);
        }
        return result;
    }
    public ArrayList GetAllRecommendSubDish(recipe maindish){
        ArrayList <recipe> result = GetAllSubDish();
        for(recipe subdish: result){
            subdish.Pivot+=AddPivotToRecipeBaseOnOil(maindish,subdish);
            if(subdish.iflike==IfLike.Like)subdish.Pivot+=1000;
            //Log.d("", "percalorie"+subdish.perCalorie+"type"+subdish.type+"SubDishPivot"+ subdish.Pivot);
            if(subdish.type!=maindish.type&& (subdish.type==RecipeType.Vegetables|maindish.type==RecipeType.Vegetables)){
                //have one and only one vegetables
                //Log.d("", "percalorie"+subdish.perCalorie+"type"+subdish.type+"SubDishPivot"+ subdish.Pivot);
                subdish.Pivot*=(1.5f+ 0.3f*(1f-2*Math.random()));
                //Log.d("","pivot*="+(1.5f+ 0.3f*(1f-2*Math.random())));//-1 - 1
                //Log.d("", "percalorie"+subdish.perCalorie+"type"+subdish.type+"SubDishPivot"+ subdish.Pivot);

            }
        }
        for(recipe r : result){
            //Log.d("", "RecipeNoSort"+ r.Pivot);
        }
        return result;
    }
    public ArrayList GetAllRecommendSubDish(recipe maindish,recipe except){
        ArrayList <recipe> result = GetAllSubDish(except);
        for(recipe subdish: result){
            subdish.Pivot+=AddPivotToRecipeBaseOnOil(maindish,subdish);
            //Log.d("", "percalorie"+subdish.perCalorie+"type"+subdish.type+"SubDishPivot"+ subdish.Pivot);
            if(subdish.type!=maindish.type&& subdish.iflike!=IfLike.Dislike&&(subdish.type==RecipeType.Vegetables|maindish.type==RecipeType.Vegetables)){
                //have one and only one vegetables
                //Log.d("", "percalorie"+subdish.perCalorie+"type"+subdish.type+"SubDishPivot"+ subdish.Pivot);
                subdish.Pivot*=(1.5f+ 0.3f*(1f-2*Math.random()));
                //Log.d("","pivot*="+(1.5f+ 0.3f*(1f-2*Math.random())));//-1 - 1
                //Log.d("", "percalorie"+subdish.perCalorie+"type"+subdish.type+"SubDishPivot"+ subdish.Pivot);

            }
        }
        for(recipe r : result){
            //Log.d("", "RecipeNoSort"+ r.Pivot);
        }
        return result;
    }
    public ArrayList GetSortedRecommendSubDish(recipe maindish){
        Log.d("", "Startsort");
        ArrayList <recipe> Temp = GetAllRecommendSubDish(maindish);
        for(int i =0;i<Temp.size();i++){
            for(int j=0;j<Temp.size()-i-1;j++){
                if(Temp.get(j).Pivot<Temp.get(j+1).Pivot){
                    recipe r = Temp.get(j);
                    Temp.set(j,Temp.get(j+1));
                    Temp.set(j+1,r);
                }
            }
        }
        return Temp;
    }
    public ArrayList GetSortedRecommendSubDish(recipe maindish,recipe except){
        Log.d("", "Startsort");
        ArrayList <recipe> Temp = GetAllRecommendSubDish(maindish,except);
        for(int i =0;i<Temp.size();i++){
            for(int j=0;j<Temp.size()-i-1;j++){
                if(Temp.get(j).Pivot<Temp.get(j+1).Pivot){
                    recipe r = Temp.get(j);
                    Temp.set(j,Temp.get(j+1));
                    Temp.set(j+1,r);
                }
            }
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

    public void SetLikeOrDislikeRecipe(int ImageId,IfLike ifLike){
        for(int i=0;i<55;i++){
            if(list[i].ImageId==ImageId){
                list[i].iflike=ifLike;
                Splash.Myuser.LikeRecipe[i]=ifLike;
                Splash.Myuser.updateInfo();
            }
        }
    }

    public IfLike IfLikeRecipe(int ImageId){
        for(int i=0;i<55;i++){
            if(list[i].ImageId==ImageId){
                return list[i].iflike;
            }
        }
        return IfLike.Normal;
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
    int FullTextId;
    public IfLike iflike=IfLike.Normal;
    recipe(){}

    public int getPivot(){return (int)Pivot;}

    recipe(int ImageId, int perCalorie,RecipeType type,boolean mainDish, int oil){
        this.ImageId=ImageId;
        this.perCalorie = perCalorie;
        this.type=type;
        this.MainDish= mainDish;
        this.oil = oil;
    }
    int GetRecipeCalorie(int MaxCalorie){
        double PerDishMaxCalorie=0;
        double result=0;
        double RecipeNeedCalorie=0;
        int maxamount=0;
        //Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>10&&Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<14
        if(true){
            PerDishMaxCalorie=MaxCalorie*0.5f;
        }
        else{
            PerDishMaxCalorie=MaxCalorie*0.4f;
        }

        if(MainDish){
            if(type==RecipeType.Vegetables){
                maxamount=3;
                RecipeNeedCalorie = PerDishMaxCalorie*0.4f;
            }
            else {
                maxamount=5;
                RecipeNeedCalorie = PerDishMaxCalorie*0.7f;
            }
        }
        else{
            if(type==RecipeType.Soup){
                maxamount=5;
                RecipeNeedCalorie = PerDishMaxCalorie*0.1f;
            }
            else if (type==RecipeType.Vegetables){
                maxamount=2;
                RecipeNeedCalorie = PerDishMaxCalorie*0.15f;
            }
            else{
                maxamount=3;
                RecipeNeedCalorie = PerDishMaxCalorie*0.35f;
            }
        }

        int amount=0;
        while(result<RecipeNeedCalorie-perCalorie){
            result+=perCalorie;
            amount++;
            if(amount>=maxamount)break;
        }
        if(result==0)result+=perCalorie;
        return (int) result;
    }
    int GetRecipeAmount(int MaxCalorie){
        return GetRecipeCalorie(MaxCalorie)/perCalorie;
    }

    public CharSequence GetRealRecipe(CharSequence text, int MaxCalorie){
        CharSequence result="";
        CharSequence amount="";
        boolean PerWasSwitch=false;
        boolean IfSteps=false;
        for (int i = 0;i<text.length();i++){
            if(!PerWasSwitch&&text.charAt(i)=='P'){
                if(i+1<text.length()&&text.charAt(i+1)=='e')
                    if(i+2<text.length()&&text.charAt(i+2)=='r'){
                        result+="Total:";
                        i+=2;
                        PerWasSwitch=true;
                        continue;
                    }
            }
            else {
                if(!IfSteps){
                    if(text.charAt(i)=='S'){
                        //if(i+5<text.length()) Log.d("checkStep",""+text.subSequence(i+1,i+6));
                        if(i+5<text.length()&&text.subSequence(i+1,i+6).equals("teps:")){
                            result+="Steps:";
                            i+=5;
                            IfSteps=true;
                            continue;
                        }
                    }
                    else if(text.charAt(i)<='9'&&text.charAt(i)>='0'){
                        amount+=text.charAt(i)+"";
                        while(++i<text.length()){
                            if(text.charAt(i)<='9'&&text.charAt(i)>='0'){
                                amount+=text.charAt(i)+"";
                            }
                            else{
                                break;
                            }
                        }
                        i--;
                        int TempAmount=Integer.valueOf(amount.toString());
                        //Log.d("eggp",TempAmount+"haha "+amount);
                        TempAmount*=GetRecipeAmount(MaxCalorie);
                        result+=String.valueOf(TempAmount);
                        amount="";
                        continue;
                    }
                }
            }
            result+=text.charAt(i)+"";
        }
        return result;
    }
}
