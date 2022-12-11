package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MoreInfo extends BaseActivity {
    private TextView moreinfo;
    private ImageView Heart;
    private boolean IfLikeRecipe=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        moreinfo = (TextView) super.findViewById(R.id.Recipe_MoreInfo);
        moreinfo.setText(recipeInfo.FullTextId);
        Heart=findViewById(R.id.LikeButton);
        if(MyrecipeList.IfLikeRecipe(recipeInfo.ImageId)){
            Heart.setColorFilter(getResources().getColor(R.color.red));
            IfLikeRecipe=true;
        }
    }

    public void Like(View view) {
        if(!IfLikeRecipe){
            MyrecipeList.SetLikeRecipe(recipeInfo.ImageId);
            Heart.setColorFilter(getResources().getColor(R.color.red));
            IfLikeRecipe=true;
        }
        else{
            IfLikeRecipe=false;
            MyrecipeList.SetLikeRecipe(recipeInfo.ImageId);
            Heart.setColorFilter(getResources().getColor(R.color.Themecolor5));
        }
        //Heart.setBackgroundColor();
    }

}