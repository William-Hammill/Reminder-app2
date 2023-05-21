package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpScreen extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button loginbtn;
    FirebaseAuth mAuth;
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
        username = (EditText) findViewById(R.id.username);
        phoneNum = (EditText) findViewById(R.id.phoneNum);
        institution = (EditText) findViewById(R.id.institution);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.emailAddress);
        editTextPassword = findViewById(R.id.password);
        loginbtn = findViewById(R.id.login_btn);

        // Preets code
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress, password; // declaring two String variables. They hold the user input
                emailAddress = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(emailAddress)) { // if the email is empty a toast will appear.
                    Toast.makeText(SignUpScreen.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpScreen.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailAddress, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {//This method is called to authenticate the user using Firebase Auth
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpScreen.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignUpScreen.this, HomeScreen.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign up fails, display a message to the user
                                    Toast.makeText(SignUpScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}