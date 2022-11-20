package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DIYrecipes extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes);
        //The system progress bar has a relatively large limit and can only set 2 colors
        findViewProg(R.id.progressbar);
        setProg(350);

        //Customize the use of the progress bar, you can set 3 colors
        findViewText();

        //The number of textviews of type, the proportion of weight weight
        setWeight(1, 2);
        setWeight(2, 3);
        setWeight(3, 1);
    }
}