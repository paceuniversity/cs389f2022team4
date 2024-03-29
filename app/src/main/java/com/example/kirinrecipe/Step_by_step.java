package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Step_by_step extends BaseActivity {
    private int imageSize, marginSE, marginTB;
    private LinearLayout Recipes,Steps;
    private ImageView BackToHome;
    private TextView RecipesSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_by_step);

        RecipesSteps = (TextView) findViewById(R.id.RecipeSteps);

        RecipesSteps.setText(LinkRecipeList[0].FullTextId);
        CharSequence s = RecipesSteps.getText();

        RecipesSteps.setText(LinkRecipeList[0].GetRealRecipe(RecipesSteps.getText(),MaxCalorie));

        /*CharSequence ss="";
        for (int i = 0;i<s.length();i++){
            ss+=s.charAt(i)+"";
            if(s.charAt(i)=='\n'){
                Log.d("check",ss+"");
                ss="";
            }
        }*/
        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);

        Recipes = (LinearLayout) findViewById(R.id.Recipes_layout);
        Steps = (LinearLayout) findViewById(R.id.Steps_layout);
        BackToHome = (ImageView) findViewById(R.id.HomeButton);
        BackToHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Step_by_step.this,HomePage.class);
                HomePage.count = 1;
                startActivity(intent);
            }
        });

        for (int i = 0; i < LinkRecipeList.length; i++){
            if (i != 0 && LinkRecipeList[i]!=null){
                recipe dish = LinkRecipeList[i];
                ImageView Dishes = getImage(dish);
                Recipes.addView(Dishes);
                Dishes.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        RecipesSteps.setText(dish.FullTextId);
                        RecipesSteps.setText(dish.GetRealRecipe(RecipesSteps.getText(),MaxCalorie));
                    }
                });
            }
             else if (LinkRecipeList[i]!=null){
                recipe dish = LinkRecipeList[i];
                ImageView Dishes = getImage(dish);
                LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams((int)(imageSize*0.7), (int)(imageSize*0.7));
                i1.setMargins(0,marginTB/2,0,marginTB/2);
                //i1.setMarginEnd(marginSE/2);
                Dishes.setLayoutParams(i1);
                Recipes.addView(Dishes);
                Dishes.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        RecipesSteps.setText(dish.FullTextId);
                        RecipesSteps.setText(dish.GetRealRecipe(RecipesSteps.getText(),MaxCalorie));
                    }
                });
            }
        }
        LinkRecipeList = new recipe[3];

    }

    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();
    }

    public ImageView getImage(recipe r){
        ImageView Image = new ImageView(Step_by_step.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams((int)(imageSize*0.7), (int)(imageSize*0.7));
        i1.setMargins(marginSE,marginTB/2,0,marginTB/2);
        Image.setLayoutParams(i1);
        Image.setImageResource(r.ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);

        return Image;
    }
}