package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity {
    Button backbtn; // return to previous screen
    Button addbtn; // generates task and adds to database
    String taskName; // name of task
    String taskdescription; // short description of task details
    String taskclass; // name of subject task is for
    String taskdate; // task due date
    String taskReminder; // reminder message for task
    Class_Task storedClass; // class used to add task to subject
    //FBDatabase firebase = new FBDatabase(this); // database used to store classes
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Map<String, Object> user_tasks = new HashMap<>();
    //user_tasks.put("first", "Ada");
    //user_tasks.put("last", "Lovelace");
    //user_tasks.put("born", 1815);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        backbtn = (Button) findViewById(R.id.taskbackBtn);
        addbtn = (Button) findViewById(R.id.AddBtn);
        taskName = String.valueOf((EditText) findViewById(R.id.TaskName));
        taskdescription = String.valueOf((EditText) findViewById(R.id.TaskDescription));
        taskclass = String.valueOf((EditText) findViewById(R.id.taskClass));
        taskdate = String.valueOf((EditText) findViewById(R.id.TaskDate));
        taskReminder = String.valueOf((EditText) findViewById(R.id.Reminder));
        storedClass = loadclass();
        //FBDatabase fb = new FBDatabase(this);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTask();
            }
        });
    }

    private Class_Task loadclass() {
        final Class_Task[] loadedClass = new Class_Task[1];
        db.collection("user tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                                loadedClass[0] = (Class_Task) document.getData();

                            }
                        } else {
                            Log.w("", "Error getting documents.");
                        }
                    }
                });
        return loadedClass[0];
    }

    @SuppressLint("NotConstructor")
    private void addNewTask(){
        //Map<String, Object> user = new HashMap<>();
        if (taskclass == storedClass.getclassname()) {
            Task task1 = new Task(taskName, String.valueOf(taskdescription), String.valueOf(taskclass), String.valueOf(taskdate), String.valueOf(taskReminder));
            db.collection("user tasks")
                    .add(task1)
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
    }
}