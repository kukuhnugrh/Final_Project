package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeApps extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        keAbout();
        logOut();
    }

    protected void keAbout() {
        button = findViewById(R.id.about);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(HomeApps.this, About.class);
                startActivity(about);
            }

        });
    }
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
}
