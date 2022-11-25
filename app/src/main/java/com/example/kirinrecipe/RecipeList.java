package com.example.kirinrecipe;

public class RecipeList {
    recipe[] list = new recipe[10];
    RecipeList(){
        list[0]=new recipe(R.drawable.scalded_prawns,150);
        list[1]=new recipe(R.drawable.char_siu,200);
    }
    public int GetImageId(int index){
        return list[index].ImageId;
    }
    public recipe GetRandomRecipe(){
        return (Math.random()<0.5)?list[0]:list[1];
    }
}
class recipe{
    int ImageId;
    int perCalorie;
    recipe(int ImageId, int perCalorie){
        this.ImageId=ImageId;
        this.perCalorie = perCalorie;
    }
}

