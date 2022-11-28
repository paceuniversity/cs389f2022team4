package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

public class DIYrecipes extends BaseActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private Spinner recpTypeSpanner;
    private ScrollView DIY;
    private LinearLayout DIYL;
    private ImageView LeftImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes);
        recpTypeSpanner = (Spinner) super.findViewById(R.id.recipe_type);
        String RecipeType = recpTypeSpanner.getSelectedItem().toString();
        LinearLayout DIYL_1 = new LinearLayout(DIYrecipes.this);
        ImageView Image1 = new ImageView(DIYrecipes.this);
        ImageView Image2 = new ImageView(DIYrecipes.this);
        DIY = (ScrollView) super.findViewById(R.id.DIYScroll);


        int imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        int marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        int marginTB = (int) getResources().getDimension(R.dimen.margin_tb);
        DIYL = (LinearLayout) super.findViewById(R.id.DIYLayout);
        LeftImage = findViewById(R.id.RandomImageLeft);



       /* recpTypeSpanner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {*/
                recipe r1 = MyrecipeList.GetRandomRecipe();
                recipe r2 = MyrecipeList.GetRandomRecipe();
                LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
                l1.setMargins(0,marginTB,0,0);
                DIYL_1.setLayoutParams(l1);
                DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
                DIYL.addView(DIYL_1);

                LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize, imageSize);
                i1.setMarginStart(marginSE);
                Image1.setLayoutParams(i1);
                Image1.setImageResource(r1.ImageId);
                Image1.setBackgroundResource(R.drawable.image_border);
                Image1.setScaleType(ImageView.ScaleType.FIT_XY);
                Image1.setPadding(10,10,10,10);
                DIYL_1.addView(Image1);

                LinearLayout.LayoutParams i2 = new LinearLayout.LayoutParams(imageSize, imageSize);
                i2.setMarginStart(marginSE);
                i2.setMarginEnd(marginSE);
                Image2.setLayoutParams(i2);
                Image2.setImageResource(r2.ImageId);
                Image2.setBackgroundResource(R.drawable.image_border);
                Image2.setScaleType(ImageView.ScaleType.FIT_XY);
                Image2.setPadding(10,10,10,10);
                DIYL_1.addView(Image2);

           /* }
        });*/

    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();



    }

}