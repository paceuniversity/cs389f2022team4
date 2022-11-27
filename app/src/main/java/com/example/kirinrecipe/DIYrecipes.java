package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
        recipe r1 = MyrecipeList.GetRandomRecipe();

        DIY = (ScrollView) super.findViewById(R.id.DIYScroll);
        DIYL = (LinearLayout) super.findViewById(R.id.DIYLayout);
        LeftImage = findViewById(R.id.RandomImageLeft);
        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL.addView(DIYL_1);

        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(150, 150);
        i1.setMarginStart(30);
        Image1.setLayoutParams(i1);
        Image1.setImageResource(r1.ImageId);
        Image1.setScaleType(ImageView.ScaleType.FIT_XY);
        Image1.setAdjustViewBounds(Boolean.TRUE);
        DIYL_1.addView(Image1);



        /*recpTypeSpanner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {*/


            /*}
        });*/

    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();



    }

}