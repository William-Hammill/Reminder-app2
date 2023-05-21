package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.civil.CivilCalendar;
import com.aminography.primedatepicker.picker.PrimeDatePicker;
import com.aminography.primedatepicker.picker.callback.SingleDayPickCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

//import android.widget.TextView;
//this is comment from choiru
public class CalendarScreen extends AppCompatActivity {
    Button backbtn;
    CalendarView calendar; // displays all tasks on calendar
    Button addNewBtn; // sends uset to new task screen
    Button classfilterbtn; // filters calendar by class
    Button datefilterbtn; // filters calendar by closest due date
    Button detailsBtn; // backup method to switch to details screen incase calendar doesn't work
    FBDatabase FB; // database storing classes
    FirebaseFirestore db = FirebaseFirestore.getInstance(); // database storing tasks
    PrimeCalendar today = new CivilCalendar();
    PrimeDatePicker dateSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_screen);
        backbtn = (Button) findViewById(R.id.calendarbackbtn);
        addNewBtn = (Button) findViewById(R.id.addnewBtn);
        classfilterbtn = (Button) findViewById(R.id.classfilter);
        datefilterbtn = (Button) findViewById(R.id.dateFilter);
        calendar = (CalendarView) findViewById(R.id.mainCalendar);
        detailsBtn = (Button) findViewById(R.id.DetailsBtn);
        dateSelector = PrimeDatePicker.Companion.dialogWith(today)
                .pickSingleDay(callback)
                .build();
        dateSelector.show(getSupportFragmentManager(),"t");

       // sets dates for calendar,   temporary until multi date display is finished
        calendar.setDate(loadDate());
        calendar.setMaxDate(1/2024);
        calendar.setMinDate(1/2023);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToAddTask();
            }
        });
        classfilterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classFilter();
            }
        });
        datefilterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateFilter();
            }
        });
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

            }
        });
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToDetails();
            }
        });
        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToDetails();
            }
        });
        SingleDayPickCallback callback = new SingleDayPickCallback() {
            @Override
            public void onSingleDayPicked(PrimeCalendar singleDay) {

            }
        };
    }

    private void switchToDetails() {
        Intent SwitchTaskDetails = new Intent(this, TaskDetails.class);
        startActivity(SwitchTaskDetails);
    }

    private void switchToAddTask(){
        Intent SwitchAddTask = new Intent(this, AddTask.class);
        startActivity(SwitchAddTask);
    }

    private void dateFilter(){
        Task dateTask = loadTask();
        Long taskDate = Long.valueOf(dateTask.getDate());
        calendar.setDate(taskDate);
    }
    private Task loadTask() {
        final Task[] Task = new Task[1];
        db.collection("user tasks")// loads firebase collection
                .get() // gets contents of database
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                                Task[0] = (Task) document.getData(); // generates task from database contents
                            }
                        } else {
                            Log.w("", "Error getting documents.");
                        }
                    }
                });
        return Task[0]; // returns newly generated and filled class from onComplete method
    }

    private void classFilter(){ // changes calendar date based on class
        FB.getClass();
        Task classTask = loadTask();
        Long classDate = Long.valueOf(classTask.getDate());
        calendar.setDate(classDate);
    }
    private long loadDate() { // loads dates to populate calendar
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
                               // return Long.parseLong(dateTask[0].getDate());

                            }
                        } else {
                            Log.w("", "Error getting documents.");
                        }
                       // return Long.parseLong(dateTask[0].getDate());
                    }
                });
        return Long.parseLong(dateTask[0].getDate());
    }
    SingleDayPickCallback callback = new SingleDayPickCallback() {
        @Override
        public void onSingleDayPicked(PrimeCalendar singleDay) {

        }
    };

}