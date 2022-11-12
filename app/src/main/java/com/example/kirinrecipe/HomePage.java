package com.example.kirinrecipe;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ///系统进度条 限制比较大 只能设置2个颜色
        findViewProg(R.id.progressbar);
        setProg(350);

        //自定义进度条使用方式 可以设置3个颜色
        findViewText();
        //type 第几个textview ，weight 权重 占的比例
        setWeight(1,2);
        setWeight(2,3);
        setWeight(3,1);
    }

    public void GotoSetting(View view) {

        Intent intent=new Intent(HomePage.this,Settingpage.class);
        startActivity(intent);
    }
}