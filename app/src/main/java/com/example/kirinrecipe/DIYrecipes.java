package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

public class DIYrecipes extends BaseActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    private Spinner recpTypeSpanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyrecipes);
        recpTypeSpanner = (Spinner) super.findViewById(R.id.recipe_type);
        String RecipeType = recpTypeSpanner.getSelectedItem().toString();

    }

    @Override
    protected void onStart(){
        //The system progress bar has a relatively large limit and can only set 2 colors
        CreateProgress();
        super.onStart();
    }

}