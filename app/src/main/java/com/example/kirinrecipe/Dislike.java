package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
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
    int j=1;
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
        boolean flag1= false;
        for(IfLike r : Splash.Myuser.LikeRecipe){
            if(r==IfLike.Dislike){
                flag1=true;
                break;
            }
        }
        //Log.d("create","haha");
        if (flag1){
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

        //if (BaseActivity.MyrecipeList.GetAllSpecific(BaseActivity.MyrecipeList.translate(Splash.Myuser.getDislike()), true) != null && DislikeRecipeList != null) {
            for (int i = 0; i < Splash.Myuser.LikeRecipe.length; i++) {
                if(Splash.Myuser.LikeRecipe[i]==IfLike.Dislike){
                    TextView DislikeNum = new TextView(Dislike.this);
                    LinearLayout.LayoutParams TextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    TextParams.setMarginStart(marginTB);
                    DislikeNum.setLayoutParams(TextParams);
                    DislikeNum.setText(String.valueOf(j++));
                    DislikeNum.setTextSize(60);
                    DislikeNum.setTypeface(Typeface.SERIF);
                    DislikeNum.setGravity(Gravity.CENTER);
                    DislikeNum.setTextColor(getResources().getColor(R.color.Themecolor5));
                    ImageView DeleteButton = getRemoveImage();
                    LinearLayout L1 = getLayout(DislikeNum, DeleteButton, getImage(BaseActivity.MyrecipeList.list[i]));
                    int finalI = i;
                    L1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DislikeLayout.removeAllViews();
                            BaseActivity.MyrecipeList.list[finalI].iflike=IfLike.Normal;
                            Splash.Myuser.LikeRecipe[finalI]=IfLike.Normal;
                            Splash.Myuser.updateInfo();
                            if(--j==1){
                                LinearLayout.LayoutParams TextParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                noDislike=new TextView(Dislike.this);
                                noDislike.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                                noDislike.setGravity(Gravity.CENTER);
                                noDislike.setTextColor(getResources().getColor(R.color.RecipesText));
                                noDislike.setText("You don't have any history recipes yet!");
                                noDislike.setLayoutParams(TextParams);
                                noDislike.setTypeface(Typeface.SERIF);
                                LinearLayout DIYL_1 = new LinearLayout(Dislike.this);
                                TextParams = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);

                                DIYL_1.setLayoutParams(TextParams);
                                DIYL_1.setOrientation(LinearLayout.VERTICAL);
                                DIYL_1.setGravity(Gravity.CENTER_HORIZONTAL);
                                DIYL_1.addView(noDislike);
                                DislikeLayout.addView(DIYL_1);
                            }
                            else {
                                j=1;
                                changeLayout();
                            }

                            //Intent intent=new Intent(Dislike.this,Dislike.class);
                            //startActivity(intent);
                        }
                    });
                    DislikeLayout.addView(L1);
                }




        }
    }
}