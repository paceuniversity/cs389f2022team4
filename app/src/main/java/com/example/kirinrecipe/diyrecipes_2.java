package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class diyrecipes_2 extends BaseActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private ImageView MainRecipe;
    private int imageSize, marginSE, marginTB;
    private LinearLayout DIYLinkLayout;
    private LinearLayout DIYMainDishLayout;
    private static int stage=0;
    private static boolean ChangePageByButton=false;
    private ImageView Confirm;
    private boolean ReturnfromSteps = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes2);
        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);
        MainRecipe = (ImageView) findViewById(R.id.MainImage);
        MainRecipe.setPadding(10,10,10,10);
        MainRecipe.setImageResource(LinkRecipeList[0].ImageId);

        Confirm = (ImageView)  findViewById(R.id.Confirmbutton);

        Confirm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(stage>=1) ReturnfromSteps=true;
                int totalCalorie = 0;
                for (int i = 0; i < LinkRecipeList.length; i++){
                    if (LinkRecipeList[i]!=null){
                        totalCalorie += LinkRecipeList[i].GetRecipeCalorie(GetMaxCalorie());
                    }
                }
                TempCalorie=0;
                AnimateTempCalorie=0;
                progressbar.setSecondaryProgress(0);
                AddCalorie(totalCalorie);
                Intent intent=new Intent(diyrecipes_2.this,Step_by_step.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        if(ReturnfromSteps){
            ReturnfromSteps=false;
            stage=0;
            Log.d("","hihihihi");
            Intent intent = new Intent(diyrecipes_2.this, HomePage.class);
            startActivity(intent);

        }
        else{
            CreateProgress();
            //ReturnfromSteps = true;
            if(stage==1){
                //DIYMainDishLayout.addView(MainRecipe);
                if(ChangePageByButton){
                    DIYMainDishLayout=findViewById(R.id.MainDishLayout);
                    Log.d("","stage11111");
                    ImageView SubImage = new ImageView(diyrecipes_2.this);

                    LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams((int)(imageSize*0.8), (int)(imageSize*0.8));
                    //i1.setMarginStart(marginSE);
                    i1.setMargins(0,marginTB,0,0);
                    SubImage.setLayoutParams(i1);
                    SubImage.setImageResource(LinkRecipeList[1].ImageId);
                    SubImage.setBackgroundResource(R.drawable.image_border);
                    SubImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    SubImage.setPadding(10,10,10,10);
                    DIYMainDishLayout.addView(SubImage);
                    ChangePageByButton=false;
                }
                else {
                    stage=0;
                    LinkRecipeList[1]=null;
                    ModifyTempCalorie( LinkRecipeList[0].GetRecipeCalorie(MaxCalorie));
                }

            }
            else if(stage==2){
                if(ChangePageByButton) {
                    DIYMainDishLayout=findViewById(R.id.MainDishLayout);
                    Log.d("","stage2222");
                    ImageView SubImage = new ImageView(diyrecipes_2.this);

                    LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams((int)(imageSize*0.8), (int)(imageSize*0.8));
                    //i1.setMarginStart(marginSE);
                    i1.setMargins(0,marginTB,0,0);
                    SubImage.setLayoutParams(i1);
                    SubImage.setImageResource(LinkRecipeList[1].ImageId);
                    SubImage.setBackgroundResource(R.drawable.image_border);
                    SubImage.setScaleType(ImageView.ScaleType.FIT_XY);
                    SubImage.setPadding(10,10,10,10);
                    DIYMainDishLayout.addView(SubImage);

                    ImageView SubImage2 = new ImageView(diyrecipes_2.this);
                    //i1.setMarginStart(marginSE);
                    i1.setMargins(0,marginTB,0,0);
                    SubImage2.setLayoutParams(i1);
                    SubImage2.setImageResource(LinkRecipeList[2].ImageId);
                    SubImage2.setBackgroundResource(R.drawable.image_border);
                    SubImage2.setScaleType(ImageView.ScaleType.FIT_XY);
                    SubImage2.setPadding(10,10,10,10);
                    DIYMainDishLayout.addView(SubImage2);
                    ChangePageByButton=false;
                }
                else {
                    stage=1;
                    LinkRecipeList[2]=null;
                    ModifyTempCalorie( LinkRecipeList[0].GetRecipeCalorie(MaxCalorie)+ LinkRecipeList[1].GetRecipeCalorie(MaxCalorie));
                }

            }

            addAllRecomandRecipe();
        }
        super.onStart();
    }

    public ImageView getImage(recipe r){
        ImageView Image = new ImageView(diyrecipes_2.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize, imageSize);
        //i1.setMarginStart(marginSE);
        i1.setMargins(0,marginTB,0,0);
        Image.setLayoutParams(i1);
        Image.setImageResource(r.ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);
        Image.setId(r.ImageId);
        Image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SelectSubDish(Image);
            }
        });
        return Image;
    }
    public void addAllRecomandRecipe(){
        DIYLinkLayout =  super.findViewById(R.id.DIYLayout2);
        DIYLinkLayout.removeAllViews();
        ArrayList<recipe> recommendList = MyrecipeList.GetSortedRecommendSubDish(MyrecipeList.FindRecipeByID(LinkRecipeList[0].ImageId));
        //recommendList = MyrecipeList.GetSortedRecommendSubDish(MyrecipeList.FindRecipeByID(LinkRecipeList[0].ImageId),Splash.Myuser.getDislike());
        for(recipe subdish : recommendList){
            //Log.d("", "subdish"+subdish.Pivot+" oil"+subdish.oil+"calorie"+subdish.perCalorie);
            if(subdish.type!=Splash.Myuser.getDislikeRecipeType()){
                boolean AlreadyHaveRecipe=false;
                for(recipe id : LinkRecipeList){
                    if(id!=null&& subdish.ImageId==id.ImageId){
                        AlreadyHaveRecipe=true;
                    }
                }
                if(!AlreadyHaveRecipe)DIYLinkLayout.addView(getImage(subdish));

            }

        }
    }
    public void SelectSubDish(ImageView Image){
        ChangePageByButton=true;
        if(stage==0){
            stage=1;
            LinkRecipeList[1]=MyrecipeList.FindRecipeByID(Image.getId());
            ModifyTempCalorie( LinkRecipeList[0].GetRecipeCalorie(MaxCalorie)+ LinkRecipeList[1].GetRecipeCalorie(MaxCalorie));
            Intent intent=new Intent(diyrecipes_2.this,diyrecipes_2.class);
            startActivity(intent);
        }
        else if(stage==1){
            stage=2;
            LinkRecipeList[2]=MyrecipeList.FindRecipeByID(Image.getId());
            ModifyTempCalorie( LinkRecipeList[0].GetRecipeCalorie(MaxCalorie)+ LinkRecipeList[1].GetRecipeCalorie(MaxCalorie)+LinkRecipeList[2].GetRecipeCalorie(MaxCalorie));
            Intent intent=new Intent(diyrecipes_2.this,diyrecipes_2.class);
            startActivity(intent);
        }
        else ChangePageByButton=false;
    }


    public void CreateSideLayout(){

    }

}