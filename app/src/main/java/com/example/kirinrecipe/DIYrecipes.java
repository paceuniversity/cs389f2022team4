package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.UnicodeSetSpanner;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.telephony.ims.ImsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DIYrecipes extends BaseActivity implements AdapterView.OnItemSelectedListener{
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private Spinner recpTypeSpanner;
    private ScrollView DIY;
    private LinearLayout DIYL;
    private String Type;
    private ArrayList<recipe> recipeSTypeList;
    private int imageSize, marginSE, marginTB;
    public int count,count2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes);

        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);

        /*LinearLayout DIYL_1 = new LinearLayout(DIYrecipes.this);
        ImageView Image1 = new ImageView(DIYrecipes.this);
        ImageView Image2 = new ImageView(DIYrecipes.this);*/
        DIY = (ScrollView) super.findViewById(R.id.DIYScroll);
        DIYL = (LinearLayout) super.findViewById(R.id.DIYLayout);
        recipeSTypeList = new ArrayList<>();
        recpTypeSpanner = (Spinner) super.findViewById(R.id.recipe_type);
        recpTypeSpanner.setOnItemSelectedListener(this);
        Log.d("", "selectItem0"+ Type);

    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        recipeInfo = new recipe();
        CreateProgress();
        count2 = 0;
        LinkRecipeList = new recipe[3];
        if(Type!=null){
            DIYL.removeAllViews();
            changeLayout();
        }
        super.onStart();
    }

    public ImageView getImage(recipe r){
        ImageView Image = new ImageView(DIYrecipes.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize, imageSize);
        i1.setMarginStart(marginSE);
        Image.setLayoutParams(i1);
        Image.setImageResource(r.ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);

        return Image;
    }

    public ImageView getInfo(){
        ImageView Image = new ImageView(DIYrecipes.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams((int)(imageSize*0.3), (int)(imageSize*0.3));
        i1.setMarginStart(marginSE);
        Image.setLayoutParams(i1);
        Image.setImageResource(R.drawable.info_icon);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        return Image;
    }



    public LinearLayout getLayout(FrameLayout frame1, FrameLayout frame2){
        LinearLayout DIYL_1 = new LinearLayout(DIYrecipes.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,0);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(frame1);
        DIYL_1.addView(frame2);

        return DIYL_1;
    }

    public FrameLayout getFrameLayout(ImageView image1, ImageView image2){
        FrameLayout DIYL_1 = new FrameLayout(DIYrecipes.this);

        FrameLayout.LayoutParams l1 = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,0);
        DIYL_1.addView(image1);
        DIYL_1.addView(image2);

        return DIYL_1;
    }

    public LinearLayout getLayout(FrameLayout frame1){

        LinearLayout DIYL_1 = new LinearLayout(DIYrecipes.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,0);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(frame1);

        return DIYL_1;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Type = recpTypeSpanner.getSelectedItem().toString();
        if (Type.equals("Choose your main recipe type here") ) {
            if (count != 0){
                DIYL.removeAllViews();
            }
            //Log.d("", "selectItem1" + Type);
            Type = Splash.Myuser.getFavorite();
            count2 = 0;
            changeLayout();
        } else {
            //Log.d("", "selectItem2"+ Type);
            DIYL.removeAllViews();
            count++;
            count2 = 0;
            changeLayout();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void changeLayout(){
        if (MyrecipeList.GetAllSpecific(MyrecipeList.translate(Type),true) != null) {
            recipeSTypeList = MyrecipeList.GetAllSpecific(MyrecipeList.translate(Type),true);
            while (recipeSTypeList.size() > 0) {
                if (recipeSTypeList.size() >= 2) {
                    ImageView Image1 = getImage(recipeSTypeList.get(0));
                    Image1.setId(recipeSTypeList.get(0).ImageId);
                    Image1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            GotoLinking(Image1);
                        }
                    });

                    ImageView info1 = getInfo();
                    info1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            //recipe r1 = recipeSTypeList.get(0);
                            recipeInfo = MyrecipeList.FindRecipeByID(Image1.getId());
                            Log.d("recipeInfo", "recipeInfo"+ recipeInfo.type);
                            Intent intent=new Intent(DIYrecipes.this,MoreInfo.class);
                            startActivity(intent);
                        }
                    });
                    FrameLayout Frame1 = getFrameLayout(Image1,info1);
                    recipeSTypeList.remove(0);

                    ImageView Image2 = getImage(recipeSTypeList.get(0));
                    Image2.setId(recipeSTypeList.get(0).ImageId);
                    LinearLayout.LayoutParams ImageParams2 = new LinearLayout.LayoutParams(imageSize, imageSize);
                    ImageParams2.setMarginStart(marginSE);
                    ImageParams2.setMarginEnd(marginSE);
                    Image2.setLayoutParams(ImageParams2);
                    Image2.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            GotoLinking(Image2);
                        }
                    });
                    ImageView info2 = getInfo();
                    LinearLayout.LayoutParams InfoParams2 = new LinearLayout.LayoutParams((int)(imageSize*0.3), (int)(imageSize*0.3));
                    InfoParams2.setMarginStart(marginSE);
                    InfoParams2.setMarginEnd(marginSE);
                    info2.setLayoutParams(InfoParams2);
                    info2.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            recipeInfo = MyrecipeList.FindRecipeByID(Image2.getId());
                            Intent intent=new Intent(DIYrecipes.this,MoreInfo.class);
                            startActivity(intent);
                        }
                    });
                    FrameLayout Frame2 = getFrameLayout(Image2,info2);

                    recipeSTypeList.remove(0);
                    LinearLayout L1 = getLayout(Frame1, Frame2);
                    if (count2 == 0){
                        L1.setPadding(0,0,0,0);
                        count2++;
                    }
                    DIYL.addView(L1);
                } else {
                    ImageView Image1 = getImage(recipeSTypeList.get(0));
                    Image1.setId(recipeSTypeList.get(0).ImageId);
                    Image1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            GotoLinking(Image1);
                        }
                    });
                    ImageView info1 = getInfo();
                    info1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            //recipe r1 = recipeSTypeList.get(0);
                            recipeInfo = MyrecipeList.FindRecipeByID(Image1.getId());
                            Intent intent=new Intent(DIYrecipes.this,MoreInfo.class);
                            startActivity(intent);
                        }
                    });
                    FrameLayout Frame1 = getFrameLayout(Image1,info1);
                    LinearLayout L1 = getLayout(Frame1);
                    DIYL.addView(L1);
                    break;
                }
            }
        }
    }

    public void GotoLinking(ImageView I2){
        LinkRecipeList[0] = MyrecipeList.FindRecipeByID(I2.getId());
        //Log.d("","getrecipe"+ MyrecipeList.FindRecipeByDrawable(I2.getDrawable()).type);
        MyrecipeList.GetSortedRecommendSubDish(MyrecipeList.FindRecipeByID(I2.getId()));
        ModifyTempCalorie(MyrecipeList.FindRecipeByID(I2.getId()).GetRecipeCalorie(MaxCalorie));
        Intent intent=new Intent(DIYrecipes.this,diyrecipes_2.class);
        startActivity(intent);
    }

}