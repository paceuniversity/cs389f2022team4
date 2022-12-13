package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryRecipe extends AppCompatActivity {
    private int imageSize, marginSE, marginTB;
    private int count = 0;
    private LinearLayout History;
    TextView noHistory;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://kirin-recipe-database-default-rtdb.firebaseio.com");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_recipe);
        DatabaseReference myRef = db.getReference();
        History = (LinearLayout) super.findViewById(R.id.HistoryLayout);
        noHistory = (TextView) super.findViewById(R.id.NoHistory);
        imageSize = (int) getResources().getDimension(R.dimen.about_image_size);
        marginSE = (int) getResources().getDimension(R.dimen.margin_se);
        marginTB = (int) getResources().getDimension(R.dimen.margin_tb);
        if (Splash.Myuser.HistoryRecipe.isEmpty()){
            noHistory.setText("You don't have any history recipes yet!");
        }else {
            changeLayout();
        }
    }

    public ImageView getImage(recipe r){
        ImageView Image = new ImageView(HistoryRecipe.this);
        LinearLayout.LayoutParams i1 = new LinearLayout.LayoutParams(imageSize/2, imageSize/2);
        i1.setMarginStart(marginTB/2);
        Image.setLayoutParams(i1);
        Image.setImageResource(r.ImageId);
        Image.setBackgroundResource(R.drawable.image_border);
        Image.setScaleType(ImageView.ScaleType.FIT_XY);
        Image.setPadding(10,10,10,10);

        return Image;
    }
    public LinearLayout getLayout3(ImageView Image1, ImageView Image2, ImageView Image3){
        LinearLayout DIYL_1 = new LinearLayout(HistoryRecipe.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,0);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(Image1);
        DIYL_1.addView(Image2);
        DIYL_1.addView(Image3);

        return DIYL_1;
    }

    public LinearLayout getLayout2(ImageView Image1, ImageView Image2){
        LinearLayout DIYL_1 = new LinearLayout(HistoryRecipe.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,0);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(Image1);
        DIYL_1.addView(Image2);

        return DIYL_1;
    }

    public LinearLayout getLayout(ImageView Image1){
        LinearLayout DIYL_1 = new LinearLayout(HistoryRecipe.this);

        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        //l1.setMargins(0,marginTB,0,0);
        DIYL_1.setLayoutParams(l1);
        DIYL_1.setPadding(0,marginTB,0,0);
        DIYL_1.setOrientation(LinearLayout.HORIZONTAL);
        DIYL_1.addView(Image1);

        return DIYL_1;
    }

    public void changeLayout(){
        if (Splash.Myuser.HistoryRecipe != null) {
            for (int i = 0; i < Splash.Myuser.HistoryRecipe.size(); i++) {
                ImageView [] HistoryImage = new ImageView[Splash.Myuser.HistoryRecipe.get(i).length];
                /*TextView HistoryNum = new TextView(HistoryRecipe.this);
                LinearLayout.LayoutParams TextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                TextParams.setMarginStart(marginTB);
                HistoryNum.setLayoutParams(TextParams);
                HistoryNum.setText(String.valueOf(i+1));
                HistoryNum.setTextSize(60);
                HistoryNum.setTypeface(Typeface.SERIF);
                HistoryNum.setGravity(Gravity.CENTER);
                HistoryNum.setTextColor(getResources().getColor(R.color.Themecolor5));*/
                for (int j = 0; j < Splash.Myuser.HistoryRecipe.get(i).length; j++) {
                    if (Splash.Myuser.HistoryRecipe.get(i)[j] != null){
                        HistoryImage[j] = getImage(Splash.Myuser.HistoryRecipe.get(i)[j]);
                        HistoryImage[j].setId(Splash.Myuser.HistoryRecipe.get(i)[j].ImageId);
                        count++;
                        if (count == 3){
                            LinearLayout.LayoutParams ImageParams = new LinearLayout.LayoutParams(imageSize/2, imageSize/2);
                            ImageParams.setMarginStart(marginTB/2);
                            ImageParams.setMarginEnd(marginTB/2);
                            HistoryImage[j].setLayoutParams(ImageParams);
                        }
                    }

                }
                if (Splash.Myuser.HistoryRecipe.get(i)[0] != null && Splash.Myuser.HistoryRecipe.get(i)[1] != null && Splash.Myuser.HistoryRecipe.get(i)[2] != null ){
                    LinearLayout L1 = getLayout3(HistoryImage[0], HistoryImage[1], HistoryImage[2]);
                    int finalI = i;
                    L1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            BaseActivity.LinkRecipeList = Splash.Myuser.HistoryRecipe.get(finalI);
                            Intent intent=new Intent(HistoryRecipe.this,Step_by_step.class);
                            startActivity(intent);
                        }
                    });
                    History.addView(L1);
                }else if (Splash.Myuser.HistoryRecipe.get(i)[0] != null && Splash.Myuser.HistoryRecipe.get(i)[1] != null && Splash.Myuser.HistoryRecipe.get(i)[2] == null ){
                    LinearLayout L1 = getLayout2(HistoryImage[0], HistoryImage[1]);
                    int finalI = i;
                    L1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            BaseActivity.LinkRecipeList = Splash.Myuser.HistoryRecipe.get(finalI);
                            Intent intent=new Intent(HistoryRecipe.this,Step_by_step.class);
                            startActivity(intent);
                        }
                    });
                    History.addView(L1);
                }
                else if (Splash.Myuser.HistoryRecipe.get(i)[0] != null && Splash.Myuser.HistoryRecipe.get(i)[1] == null && Splash.Myuser.HistoryRecipe.get(i)[2] == null ){
                    LinearLayout L1 = getLayout(HistoryImage[0]);
                    int finalI = i;
                    L1.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            BaseActivity.LinkRecipeList = Splash.Myuser.HistoryRecipe.get(finalI);
                            Intent intent=new Intent(HistoryRecipe.this,Step_by_step.class);
                            startActivity(intent);
                        }
                    });
                    History.addView(L1);
                }

            }
        }
    }
}