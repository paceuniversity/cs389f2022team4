package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Dislike extends AppCompatActivity {
    private int imageSize, marginSE, marginTB;
    private int count = 0;
    private LinearLayout DislikeLayout;
    TextView noDislike;
    private ArrayList<recipe> DislikeRecipeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dislike);
        DislikeLayout = (LinearLayout) super.findViewById(R.id.DislikeLayout);
        noDislike= (TextView) super.findViewById(R.id.NoDislike);
        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);
        DislikeRecipeList = BaseActivity.MyrecipeList.GetAllSpecific(BaseActivity.MyrecipeList.translate(Splash.Myuser.getDislike()), true);
        if (BaseActivity.MyrecipeList.GetAllSpecific(BaseActivity.MyrecipeList.translate(Splash.Myuser.getDislike()), true) != null){
            changeLayout();
        }else {
            noDislike.setText("You don't have any history recipes yet!");
        }

    }

    public ImageView getImage(recipe r){
        ImageView Image = new ImageView(Dislike.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize/2, imageSize/2);
        i1.setMarginStart(marginSE*2);
        Image.setLayoutParams(i1);
        Image.setImageResource(r.ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);

        return Image;
    }

    public LinearLayout getLayout(TextView Num, ImageView Delete, ImageView Image1){
        LinearLayout DIYL_1 = new LinearLayout(Dislike.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,0);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.setGravity(Gravity.CENTER);
        DIYL_1.addView(Num);
        DIYL_1.addView(Image1);
        DIYL_1.addView(Delete);

        return DIYL_1;
    }

    public ImageView getRemoveImage(){
        ImageView Image = new ImageView(Dislike.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize/2, imageSize/2);
        i1.setMarginStart(marginSE);
        i1.setMarginEnd(marginSE);
        Image.setLayoutParams(i1);
        Image.setImageResource(R.drawable.remove_icon);

        return Image;
    }

    public void changeLayout() {
        if (BaseActivity.MyrecipeList.GetAllSpecific(BaseActivity.MyrecipeList.translate(Splash.Myuser.getDislike()), true) != null && DislikeRecipeList != null) {
            for (int i = 0; i < DislikeRecipeList.size(); i++) {
                TextView DislikeNum = new TextView(Dislike.this);
                LinearLayout.LayoutParams TextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                TextParams.setMarginStart(marginTB);
                DislikeNum.setLayoutParams(TextParams);
                DislikeNum.setText(String.valueOf(i + 1));
                DislikeNum.setTextSize(60);
                DislikeNum.setTypeface(Typeface.SERIF);
                DislikeNum.setGravity(Gravity.CENTER);
                DislikeNum.setTextColor(getResources().getColor(R.color.Themecolor5));
                ImageView DeleteButton = getRemoveImage();
                LinearLayout L1 = getLayout(DislikeNum, DeleteButton, getImage(DislikeRecipeList.get(i)));
                int finalI = i;
                L1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DislikeRecipeList.remove(finalI);
                        DislikeLayout.removeAllViews();
                        BaseActivity.MyrecipeList.GetAllSpecific(BaseActivity.MyrecipeList.translate(Splash.Myuser.getDislike()), true).remove(finalI);
                        changeLayout();
                    }
                });

                DislikeLayout.addView(L1);


            }
        }
    }
}