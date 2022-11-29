package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes2);
        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);
        MainRecipe = (ImageView) findViewById(R.id.MainImage);
        MainRecipe.setImageResource(LinkRecipeList[0].ImageId);


        /*LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize, imageSize);

        i1.setMarginStart(marginSE);
        ImageView Image = new ImageView(diyrecipes_2.this);
        Image.setLayoutParams(i1);
        Image.setImageResource(LinkRecipeList[0].ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);

        /*LinearLayout DIYL_1 = new LinearLayout(diyrecipes_2.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,marginTB);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(Image);*/
        //DIYLinkLayout.addView(Image);
    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        addAllRecomandRecipe();
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

        return Image;
    }
    public void addAllRecomandRecipe(){
        DIYLinkLayout =  super.findViewById(R.id.DIYLayout2);
        ArrayList<recipe> recommendList = MyrecipeList.GetSortedRecommendSubDish(MyrecipeList.FindRecipeByID(LinkRecipeList[0].ImageId));
        for(recipe subdish : recommendList){
            //Log.d("", "subdish"+subdish.Pivot+" oil"+subdish.oil+"calorie"+subdish.perCalorie);
            DIYLinkLayout.addView(getImage(subdish));
        }

    }

    public void CreateSideLayout(){

    }

}