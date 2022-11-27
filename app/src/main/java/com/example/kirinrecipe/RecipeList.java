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
    }
    public int GetImageId(int index){
        return list[index].ImageId;
    }
    public recipe GetRandomRecipe(){
        int random = (int)(Math.random()*3);
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
