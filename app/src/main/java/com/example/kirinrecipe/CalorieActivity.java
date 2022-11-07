package com.example.kirinrecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CalorieActivity extends AppCompatActivity {

    private EditText editTextView;
    private ProgressBar progressbar;
    private TextView msgTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        editTextView = (EditText) findViewById(R.id.edit_view);
        msgTv = (TextView) findViewById(R.id.msg_tv);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        progressbar.setMax(10000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onBtnClick(View view) {
        String prog = editTextView.getText().toString();
        if(TextUtils.isEmpty(prog)){
            Toast.makeText(CalorieActivity.this,"Please enter the calorie intake of the day",Toast.LENGTH_SHORT).show();
            return;
        }
        editTextView.setText("");
        int num = (int) (Double.parseDouble(prog)/100 * 100);
        progressbar.setProgress(num);
        msgTv.setText(prog+"/10000");
    }
}