package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Step_by_step extends BaseActivity {
    private int imageSize, marginSE, marginTB;
    private LinearLayout Recipes,Steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_by_step);

        imageSize = (int) getResources().getDimension(R.dimen.about_image_size)/3;
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);

        Recipes = (LinearLayout) findViewById(R.id.Recipes_layout);
        Steps = (LinearLayout) findViewById(R.id.Steps_layout);
        for (int i = 0; i < LinkRecipeList.length; i++){
            Recipes.addView(getImage(LinkRecipeList[i]));
        }

    }

    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();
    }

    public ImageView getImage(recipe r){
        ImageView Image = new ImageView(Step_by_step.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize, imageSize);
        //i1.setMarginStart(marginSE);
        Image.setLayoutParams(i1);
        Image.setImageResource(r.ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);

        return Image;
    }
}