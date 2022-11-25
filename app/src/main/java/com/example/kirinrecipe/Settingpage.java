package com.example.kirinrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Settingpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingpage);

        TextView Signout = (TextView) findViewById(R.id.Signout_text);

        Signout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signOut();
                    }
                }
        );

    }

    public void GotoAbout(View view) {
        Intent intent=new Intent(Settingpage.this,About.class);
        startActivity(intent);
    }

    public void GotoPKwithus(View view) {
        Intent intent=new Intent(Settingpage.this,PKwithus.class);
        startActivity(intent);
    }

    public void GoBack(View view) {
        Intent intent=new Intent(Settingpage.this,HomePage.class);
        startActivity(intent);
    }
    public void Gotobasicinformation(View view) {
        Intent intent=new Intent(Settingpage.this,Basicinformation.class);
        startActivity(intent);
    }
    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(Settingpage.this, Login.class);
                        startActivity(intent);
                    }
                });
        // [END auth_fui_signout]
    }
}