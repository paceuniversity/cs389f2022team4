package com.example.kirinrecipe;
enum RecipeType
{
    Seafood,
    Pork,
    Beef,
    Mutton,
    Vegetables,
    Poultry,
}
public class RecipeList {
    recipe[] list = new recipe[10];
    RecipeList(){
        list[0]=new recipe(R.drawable.scalded_prawns,150,RecipeType.Seafood);
        list[1]=new recipe(R.drawable.char_siu,200,RecipeType.Pork);
        list[2]=new recipe(R.drawable.braised_pork_balls_in_gravy,300,RecipeType.Pork);
        list[3]=new recipe(R.drawable.stir_fried_pork_with_scallions,143,RecipeType.Pork);
        list[4]=new recipe(R.drawable.shredded_pork_in_beijing_sauce,162,RecipeType.Pork);
        list[5]=new recipe(R.drawable.grilled_eggplant,65,RecipeType.Vegetables);
        list[6]=new recipe(R.drawable.fish_head_with_minced_pepper,113,RecipeType.Vegetables);
        list[7]=new recipe(R.drawable.granny_smith,89,RecipeType.Vegetables);
        list[8]=new recipe(R.drawable.roast_duck,269,RecipeType.Poultry);
        list[9]=new recipe(R.drawable.braised_pork_ball_in_brown_sauce,261,RecipeType.Pork);

    }
    public int GetImageId(int index){
        return list[index].ImageId;
    }
    public recipe GetRandomRecipe(){
        int random = (int)(Math.random()*10);
        return list[random];
    }
}
class recipe{
    int ImageId;
    int perCalorie;
    RecipeType type;

    recipe(int ImageId, int perCalorie,RecipeType type){
        this.ImageId=ImageId;
        this.perCalorie = perCalorie;
        this.type=type;
    }
}
