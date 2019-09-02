package com.example.final_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private TextView signup;
    private EditText usernameEditText;
    private EditText passwordEditText;
    Validation valid = new Validation();
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String uname = String.valueOf(usernameEditText.getText());
                String password = String.valueOf(passwordEditText.getText());
                if(valid.isValid(uname, password)){
                    setContentView(R.layout.success);
                }
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
}