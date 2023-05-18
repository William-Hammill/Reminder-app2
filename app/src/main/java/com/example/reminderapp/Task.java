package com.example.reminderapp;

import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

public class Task{
    // this is the Class used to create Task objects which are then stored in the database via fireStore
    String _taskname; // name of task
    String _description; // description of task such as task details
    String _date; // task due date
    Class_Task _class; // subject/class task is part of
    String _reminder; // reminder message for task

    public Task() {}

    public Task(String name, String description, Class_Task Class, String Date, String Reminder ) {
        this._taskname = name;
        this._class = Class;
        this._description = description;
        this._date = Date;
        this._reminder = Reminder;

    }
    public Task(String name, String phoneNumber) {
        this._taskname = name;
    }

    public Task(String taskName, String taskdescription, String taskclass, String taskdate, String taskReminder) {
    }

    public String getName() {

        return this._taskname;
    }
    public void setName(String name) {
        this._taskname = name;
    }
    public String getDescription() {
        return this._description;
    }
    public void setDescription(String description) {
        this._description = description;
    }
    public String getDate() {
        return this._date;
    }
    public void setDate(String date) {
        this._date = date;
    }
    public String getReminder() {
        return this._reminder;
    }
    public void setReminder(String Reminder) {
        this._reminder = Reminder;
    }
    public void setClass(Class_Task Class){
        this._class = Class;
    }
    public Class_Task getclass(){
        return this._class;
    }
}
