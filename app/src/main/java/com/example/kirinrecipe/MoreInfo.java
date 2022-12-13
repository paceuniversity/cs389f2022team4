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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        moreinfo = (TextView) super.findViewById(R.id.Recipe_MoreInfo);
        moreinfo.setText(recipeInfo.FullTextId);
        Heart=findViewById(R.id.LikeButton);
        if(MyrecipeList.IfLikeRecipe(recipeInfo.ImageId)==IfLike.Like){
            Heart.setColorFilter(getResources().getColor(R.color.red));
        }
    }

    public void Like(View view) {
        if(MyrecipeList.IfLikeRecipe(recipeInfo.ImageId)==IfLike.Normal){
            MyrecipeList.SetLikeOrDislikeRecipe(recipeInfo.ImageId,IfLike.Like);
            Heart.setColorFilter(getResources().getColor(R.color.red));
        }
        else if(MyrecipeList.IfLikeRecipe(recipeInfo.ImageId)==IfLike.Like){
            MyrecipeList.SetLikeOrDislikeRecipe(recipeInfo.ImageId,IfLike.Normal);
            Heart.setColorFilter(getResources().getColor(R.color.Themecolor5));
        }
    }

    public void DisLike(View view) {
        MyrecipeList.SetLikeOrDislikeRecipe(recipeInfo.ImageId,IfLike.Dislike);
        Heart.setColorFilter(getResources().getColor(R.color.Themecolor5));
    }
}