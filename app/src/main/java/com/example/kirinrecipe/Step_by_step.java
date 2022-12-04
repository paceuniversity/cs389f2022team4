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

        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);

        Recipes = (LinearLayout) findViewById(R.id.Recipes_layout);
        Steps = (LinearLayout) findViewById(R.id.Steps_layout);
        for (int i = 0; i < LinkRecipeList.length; i++){
            if (i != 0 && LinkRecipeList[i]!=null){
                Recipes.addView(getImage(LinkRecipeList[i]));
            }
             else if (LinkRecipeList[i]!=null){
                ImageView Subdish2 = getImage(LinkRecipeList[i]);
                LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams((int)(imageSize*0.7), (int)(imageSize*0.7));
                i1.setMargins(0,marginTB/2,0,marginTB/2);
                //i1.setMarginEnd(marginSE/2);
                Subdish2.setLayoutParams(i1);
                Recipes.addView(Subdish2);
            }
        }

    }

    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();
    }

    public ImageView getImage(recipe r){
        ImageView Image = new ImageView(Step_by_step.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams((int)(imageSize*0.7), (int)(imageSize*0.7));
        i1.setMargins(marginSE/2,marginTB/2,0,marginTB/2);
        Image.setLayoutParams(i1);
        Image.setImageResource(r.ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);

        return Image;
    }
}