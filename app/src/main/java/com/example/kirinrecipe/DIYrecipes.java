package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.UnicodeSetSpanner;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes);
        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);

        LinearLayout DIYL_1 = new LinearLayout(DIYrecipes.this);
        ImageView Image1 = new ImageView(DIYrecipes.this);
        ImageView Image2 = new ImageView(DIYrecipes.this);
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
        CreateProgress();
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



    public LinearLayout getLayout(ImageView image1, ImageView image2){
        LinearLayout DIYL_1 = new LinearLayout(DIYrecipes.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(image1);
        DIYL_1.addView(image2);

        return DIYL_1;
    }

    public LinearLayout getLayout(ImageView image1){

        LinearLayout DIYL_1 = new LinearLayout(DIYrecipes.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(image1);

        return DIYL_1;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Type = recpTypeSpanner.getSelectedItem().toString();
        if (Type.equals("Choose your main recipe type here")) {
            Log.d("", "selectItem1" + Type);
            Type = Splash.Myuser.getFavorite();
            if (MyrecipeList.GetAllSpecific(MyrecipeList.translate(Type)) != null) {
                recipeSTypeList = MyrecipeList.GetAllSpecific(MyrecipeList.translate(Type));
                while (recipeSTypeList.size() > 0) {
                    if (recipeSTypeList.size() >= 2) {
                        ImageView I2 = getImage(recipeSTypeList.get(1));
                        I2.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(DIYrecipes.this,diyrecipes_2.class);
                                ImageList[0] = recipeSTypeList.get(1).ImageId;
                                startActivity(intent);
                            }
                        });
                        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize, imageSize);
                        i1.setMarginStart(marginSE);
                        i1.setMarginEnd(marginSE);
                        I2.setLayoutParams(i1);
                        LinearLayout L1 = getLayout(getImage(recipeSTypeList.get(0)), I2);
                        recipeSTypeList.remove(0);
                        recipeSTypeList.remove(0);
                        DIYL.addView(L1);
                    } else {
                        LinearLayout L1 = getLayout(getImage(recipeSTypeList.get(0)));
                        DIYL.addView(L1);
                        break;
                    }
                }
            }
        } else {
            Log.d("", "selectItem2"+ Type);
            DIYL.removeAllViews();
            if (MyrecipeList.GetAllSpecific(MyrecipeList.translate(Type)) != null) {
                recipeSTypeList = MyrecipeList.GetAllSpecific(MyrecipeList.translate(Type));
                while (recipeSTypeList.size() > 0) {
                    if (recipeSTypeList.size() >= 2) {
                        LinearLayout L1 = getLayout(getImage(recipeSTypeList.get(0)), getImage(recipeSTypeList.get(1)));
                        recipeSTypeList.remove(0);
                        recipeSTypeList.remove(0);
                        DIYL.addView(L1);
                    } else {
                        LinearLayout L1 = getLayout(getImage(recipeSTypeList.get(0)));
                        DIYL.addView(L1);
                        break;
                    }
                }
            }
        }

        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}