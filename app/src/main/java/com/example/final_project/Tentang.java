package com.example.final_project;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Tentang extends AppCompatActivity {
    ImageView imageView = (ImageView).findViewById(R.id.imageView);
    setContentView(R.layout.tentang);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang);
    }
}
