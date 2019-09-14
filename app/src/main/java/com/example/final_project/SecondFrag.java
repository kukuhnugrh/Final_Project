package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondFrag extends AppCompatActivity{
    Button button;
    Button SecondFragmen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmen_dua);
        //keAbout();
        logOut();
        fragmenSatu();
    }

    //    protected void keAbout() {
//        button = findViewById(R.id.about);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent about = new Intent(HomeApps.this, Tentang.class);
//                startActivity(about);
//            }
//
//        });
//    }
    protected void logOut(){
        button = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent main = new Intent(SecondFrag.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    protected void fragmenSatu(){
        SecondFragmen = findViewById(R.id.frag1);
        SecondFragmen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mainA = new Intent(SecondFrag.this, HomeApps.class);
                startActivity(mainA);
            }
        });
    }
}
