package com.example.reminderapp;

//import static io.grpc.internal.SharedResourceHolder.holder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomeScreen extends AppCompatActivity {
    Button quickAddBtn;
    Button calendarbtn;
    Button classbtn;
    RecyclerView top3Tasks;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Query query = FirebaseFirestore.getInstance()
            .collection("user tasks")
            .orderBy("date")
            .limit(3);

    //RecyclerView.ViewHolder taskHolder;
    FirestoreRecyclerAdapter<Task, RecyclerView.ViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        quickAddBtn = (Button) findViewById(R.id.QuickAddBtn);
        top3Tasks = (RecyclerView) findViewById(R.id.top3Tasks);
        calendarbtn = (Button) findViewById(R.id.CalBtn);
        classbtn = (Button) findViewById(R.id.ClassBtn);

        quickAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchtoAddTask();
            }
        });
        calendarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchtoCalendar();
            }
        });
        classbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchtoClass();
            }
        });
        FirestoreRecyclerOptions<Task> options = new FirestoreRecyclerOptions.Builder<Task>()
                .setQuery(query, Task.class)
                .build();

        FirestoreRecyclerAdapter<Task, RecyclerView.ViewHolder> adapter = new FirestoreRecyclerAdapter<Task, RecyclerView.ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Task model) {

            }
            @Override
            public TaskHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the TaskHolder
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.activity_home_screen, group, false);

                return new TaskHolder(view);
            }
        };
        top3Tasks.setAdapter(adapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    // left over attempt to load recycler view with tasks
   // private void loadTop3Tasks() { // populates recycler view with tasks with earliest due date
     //   DocumentReference docRef = db.collection("user tasks").document("");
      //  docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
          //  @Override
        //    public void onSuccess(DocumentSnapshot documentSnapshot) {
            //    Task task = documentSnapshot.toObject(Task.class);
          //  }
       // });
   // }

    private void switchtoCalendar(){ // sends user to calendar screen
        Intent SwitchCalendar = new Intent(this, CalendarScreen.class);
        startActivity(SwitchCalendar);
    }
    private void switchtoClass(){ // sends user to class selection screen
        Intent SwitchClass = new Intent(this, ClassSelect.class);
        startActivity(SwitchClass);
    }
    private void switchtoAddTask(){ // sends user to task creation screen
        Intent SwitchAddTask = new Intent(this, AddTask.class);
        startActivity(SwitchAddTask);
    }
}