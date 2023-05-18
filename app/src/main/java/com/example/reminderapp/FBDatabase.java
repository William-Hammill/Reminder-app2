package com.example.reminderapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;

import com.firebase.ui.firestore.FirestoreArray;

//import com.google.firestore.v1.Cursor;


public class FBDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskManager";
    private static final String TABLE_Tasks = "Tasks";
    private static final String KEY_ID = "id"; private
    static final String KEY_NAME = "name";
    //private static final String KEY_PH_NO = "description";
    //final String KEY_Class = "class";
   // final String KEY_date = "date";
   // final String KEY_reminder = "reminder";

    public FBDatabase(Context context) {
        //super();
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase fb) {
        String CREATE_Class_TABLE = "CREATE TABLE " + TABLE_Tasks + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, KEY_NAME)";
        fb.execSQL(CREATE_Class_TABLE);
    }
   // @Override
    public void onUpgrade(SQLiteDatabase fb, int oldVersion, int newVersion) {
        // Drop older table if existed
       fb.execSQL("DROP TABLE IF EXISTS " + TABLE_Tasks);

        // Create table again
        onCreate(fb);
    }

    // Adding new task
    void addClass(Class_Task Class) {
        SQLiteDatabase fb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Class.getclassname()); // Contact Name
       // values.put(KEY_PH_NO, task1.getDescription()); // Contact Phone
        //values.put(KEY_Class, String.valueOf(task1.getClass())); // Contact Phone
        //values.put(KEY_date, task1.getDate()); // Contact Phone
        //values.put(KEY_reminder, task1.getDescription()); // Contact Phone

        // Inserting Row
       //   fb.insert(TABLE_Tasks, null, values);
        //  fb.close(); // Closing database connection
    }

    public Class_Task getClass(String classname){
        SQLiteDatabase fb = this.getReadableDatabase();

        Cursor cursor = fb.query(TABLE_Tasks, new String[] {
                        KEY_ID, KEY_NAME}, KEY_ID + "=?",
                new String[] { String.valueOf(classname) }, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Class_Task Class = new Class_Task(cursor.getString(0));
        return Class;
    }


    // Getting single task
    public Class_Task getTask(String name) {
        SQLiteDatabase fb = this.getReadableDatabase();

        Cursor cursor = fb.query(TABLE_Tasks, new String[] {
                KEY_ID, KEY_NAME,}, KEY_ID + "=?",
                              new String[] { String.valueOf(name) }, null, null, null, null);
                                if (cursor != null) cursor.moveToFirst();
                               Class_Task Class = new Class_Task(cursor.getString(0));
                                 return Class;
                               }


// Getting All Contacts
//public List<Tasks> getAllTasks() {
 //       List<Student> taskList = new ArrayList<Task>();
        // Select All Query
    //    String selectQuery = "SELECT * FROM " + TABLE_Tasks;

  //      FBdatabase fb = this.getWritableDatabase();
   //     Cursor cursor = db.rawQuery(selectQuery, null);
    //    // looping through all rows and adding to list
    //    if (cursor.moveToFirst()) { do {
    //    Task task = new Student();
     //   task.setID(cursor.getString(0));
     //   task.setDescription(cursor.getString(1));
    //    task.setDate(cursor.getString(2));
        // Adding task to list
   //     TaskList.add(task); }
   //     while (cursor.moveToNext());
    //    }

        // return task list
    //    return TaskList;
    //    }

// Updating single Task
public int updateTask(Class_Task Class) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Class.getclassname());

        // updating row
       return db.update(TABLE_Tasks, values, KEY_ID + " = ?",
       new String[] { String.valueOf(Class.getclassname()) });
   }


// Deleting single Class
public void deleteStudent(Class_Task Class) {
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_Tasks, KEY_ID + " = ?",
               new String[] { String.valueOf(Class.getclassname()) });
       db.close();
       }
}
//public int getStudentsCount() {
//    String countQuery = "SELECT * FROM " + TABLE_Tasks;
   // SQLiteDatabase db = this.getReadableDatabase();
  //  Cursor cursor = db.rawQuery(countQuery, null);
   // cursor.close();

    // return count
  //  return cursor.getCount();
//}
//}





//}
