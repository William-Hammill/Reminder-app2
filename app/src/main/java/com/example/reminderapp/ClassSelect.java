package com.example.reminderapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClassSelect extends AppCompatActivity {
    Button calendarbtn;
    Button newclassbtn;
    Button class1btn;
    Button class2btn;
    Button class3btn;
    Button class4btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_select);
        calendarbtn = (Button) findViewById(R.id.CalendarBtn); // switches to all class calendar AKA main calendar
        newclassbtn = (Button) findViewById(R.id.createClassBtn); // sends user to class creation page
        class1btn = (Button) findViewById(R.id.Class1Btn); // should switch to first class view screen
        class2btn = (Button) findViewById(R.id.Class2Btn); // button for second class in database (if one exists
        class3btn = (Button) findViewById(R.id.Class3Btn); // button for second class in database (if one exists
        class4btn = (Button) findViewById(R.id.Class4Btn); // button for second class in database (if one exists

        calendarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            } // switches back to previous screen
        });
        newclassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchaddClass();
            }
        });
        class1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchClassView();
            }
        });
        class2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchClassView();
            }
        });
        class3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchClassView();
            }
        });
        class4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchClassView();
            }
        });
    }
    private void switchaddClass(){
        Intent SwitchaddClass = new Intent(this, AddClass.class);
        startActivity(SwitchaddClass);
    }
    private void switchClassView(){
        Intent SwitchClassview = new Intent(this, ClassView.class);
        startActivity(SwitchClassview);
    }
}