package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TaskDetails extends AppCompatActivity {
    Button backbtn;
    Button editbtn;
    String taskName;
    String taskdescription;
    String taskClass;
    String taskdate;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        backbtn = (Button) findViewById(R.id.tskBackBtn);
        editbtn = (Button) findViewById(R.id.editBtn);
        taskName = String.valueOf((TextView) findViewById(R.id.displayName));
        taskdescription = String.valueOf((TextView) findViewById(R.id.displayDesc));
        taskClass = String.valueOf((TextView) findViewById(R.id.displayClass));
        taskdate = String.valueOf((TextView) findViewById(R.id.displayDate));
        loadTask();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            } // sends user to previous screen
        });
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToAddTask();
            }
        });

    }
    private void switchToAddTask(){ // switches to task creation screen
        Intent SwitchAddTask = new Intent(this, AddTask.class);
        startActivity(SwitchAddTask);
    }
    void loadTask(){
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
                                taskName = Task[0].getName();
                                taskdescription = Task[0].getDescription();
                                taskClass = String.valueOf(Task[0].getClass());
                                taskdate = Task[0].getDate();
                            }
                        } else {
                            Log.w("", "Error getting documents.");
                        }
                    }
                });
    }
}