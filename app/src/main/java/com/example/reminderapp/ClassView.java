package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ClassView extends AppCompatActivity {
    Button backbtn; // returns to previous screen
    Button addnewbtn; // adds new task
    Button datefilterbtn; // filters calendar by date
    CalendarView classCalendar; // calendar to display subject tasks
   // FBDatabase FB; // database to load tasks from
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view);
        backbtn = (Button) findViewById(R.id.classBckBtn);
        addnewbtn = (Button) findViewById(R.id.classAddBtn);
        datefilterbtn = (Button) findViewById(R.id.classDtFilter);
        classCalendar = (CalendarView) findViewById(R.id.ClassCalendar);

       // sets dates for class calendar
        classCalendar.setDate(loadDate());
        classCalendar.setMaxDate(1/24);
        classCalendar.setMinDate(1/23);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addnewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchaddClass();
            }
        });
        datefilterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDate();
            }
        });
        classCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToDetails();
            }
        });
    }

    private long loadDate() {
        final Task[] dateTask = new Task[1];
        db.collection("user tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                                dateTask[0] = (Task) document.getData();
                                //return Long.parseLong(dateTask[0].getDate());

                            }
                        } else {
                            Log.w("", "Error getting documents.");
                        }
                        //return null;
                    }
                });
        return Long.parseLong(dateTask[0].getDate());
    }

    private void switchToDetails() {
        Intent SwitchTaskDetails = new Intent(this, TaskDetails.class);
        startActivity(SwitchTaskDetails);
    }
    private void switchaddClass(){
        Intent SwitchaddClass = new Intent(this, AddClass.class);
        startActivity(SwitchaddClass);
    }
    private void filterDate(){
        Task dateTask = loadTask();
        Long taskDate = Long.valueOf(dateTask.getDate());
        classCalendar.setDate(taskDate);
    }

    private Task loadTask() {
        final Task[] Task = new Task[1];
        db.collection("user tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                                Task[0] = (Task) document.getData();
                            }
                        } else {
                            Log.w("", "Error getting documents.");
                        }
                    }
                });
        return Task[0];
    }
}