package com.example.final_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class HomeApps extends AppCompatActivity {
    Button button;
    Button firstFragmen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //keAbout();
        logOut();
        fragmenDua();
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
                Intent main = new Intent(HomeApps.this, MainActivity.class);
                startActivity(main);
            }
        });
    }

    protected void fragmenDua(){
        firstFragmen = findViewById(R.id.frag2);
        firstFragmen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent mainA = new Intent(HomeApps.this, SecondFrag.class);
                startActivity(mainA);
            }
        });
    }

}
