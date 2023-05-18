package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpScreen extends AppCompatActivity {
    Button btn; // generates user's account
    EditText username; // chosen username of user
    EditText password; // user's chosen password
    EditText institution; // learning institution user is a student of eg latrobe
    EditText phoneNum; // user's phone number for sending reminders
    EditText emailAddress; // user's email address
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        btn = (Button) findViewById(R.id.accountBtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        phoneNum = (EditText) findViewById(R.id.phoneNum);
        institution = (EditText) findViewById(R.id.institution);
        emailAddress = (EditText) findViewById(R.id.emailAddress);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }
    void createAccount(){

    }
}