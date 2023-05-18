package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddClass extends AppCompatActivity {
    Button backbtn; // return to previous screen
    Button addbtn; // adds class to database
    Button saveBtn; // saves class changes for later addition to database
    EditText className; // name of class
    FBDatabase fb; // database for storing classes and tasks
    Class_Task Class;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FBDatabase Fb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        backbtn = (Button) findViewById(R.id.backBtn);
        addbtn = (Button) findViewById(R.id.addBtn);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        className = (EditText) findViewById(R.id.Classname);

        fb = new FBDatabase(this);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClass();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClass();
            }
        });
    }
    private void addClass(){
        Class = new Class_Task(String.valueOf(className));
        fb.getClass(String.valueOf(className));
        db.collection("user tasks")
                .add(Class)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "Error adding document", e);
                    }
                });
    }
    private void saveClass(){
        Class = new Class_Task(String.valueOf(className));
        Fb.addClass(Class);
    }
}