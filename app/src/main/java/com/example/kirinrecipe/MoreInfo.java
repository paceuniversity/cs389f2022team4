package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MoreInfo extends BaseActivity {
    private TextView moreinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        moreinfo = (TextView) super.findViewById(R.id.Recipe_MoreInfo);
        moreinfo.setText(recipeInfo.FullTextId);
    }
}