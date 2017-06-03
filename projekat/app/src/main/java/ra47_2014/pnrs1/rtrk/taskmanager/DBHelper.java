package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by student on 29.5.2017.
 */

public class DBHelper extends SQLiteOpenHelper implements Serializable {

    public static String DATABASE_NAME = "BZA.db";
    public static int DB_VERSION = 1;
    public static String TABLE_NAME = "Tasks";
    public static String COLUMN_ID = "ID";
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESC = "Description";
    public static String COLUMN_TIME = "Time";
    public static String COLUMN_DATE = "Date";
    public static String COLUMN_PR = "Priority";
    public static String COLUMN_DONE = "Done";
    public static String COLUMN_REMINDER = "Reminder";
    public static String COLUMN_REMINDED = "Reminded";

    private Context mContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db = mContext.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESC + " TEXT, "
                + COLUMN_TIME + " TEXT, "
                + COLUMN_DATE + " TEXT, "
                + COLUMN_PR + " INTEGER, "
                + COLUMN_DONE + " INTEGER, "
                + COLUMN_REMINDER + " INTEGER, "
                + COLUMN_REMINDED + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert(Task task){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,task.getID());
        values.put(COLUMN_NAME,task.getName());
        values.put(COLUMN_DESC,task.getDescription());
        values.put(COLUMN_TIME,task.getTime());
        values.put(COLUMN_DATE,task.getDate());
        values.put(COLUMN_PR,task.getPriority());
        values.put(COLUMN_DONE,task.isDone());
        values.put(COLUMN_REMINDER,task.isReminder());
        values.put(COLUMN_REMINDED,task.ismReminded());

        db.insert(TABLE_NAME,null,values);
        close();
    }

    public ArrayList<Task> readTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM "+TABLE_NAME,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String desc = cursor.getString(cursor.getColumnIndex(COLUMN_DESC));
            String time = cursor.getString(cursor.getColumnIndex(COLUMN_TIME));
            String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
            int priority = cursor.getInt(cursor.getColumnIndex(COLUMN_PR));
            int done = cursor.getInt(cursor.getColumnIndex(COLUMN_DONE));
            int reminder = cursor.getInt(cursor.getColumnIndex(COLUMN_REMINDER));
            int reminded = cursor.getInt(cursor.getColumnIndex(COLUMN_REMINDED));
            Task task = new Task(id,name,time,date,desc,priority,done,reminder,reminded);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    public boolean idExists(Task task){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT " + COLUMN_ID +" FROM "+TABLE_NAME,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            if(id==task.getID())
                return true;
            cursor.moveToNext();
        }
        cursor.close();
        return false;
    }

    public void deleteTask(long taskID){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[] {Long.toString(taskID)});
    }

    public void editTask(Task task){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,task.getID());
        values.put(COLUMN_NAME,task.getName());
        values.put(COLUMN_DESC,task.getDescription());
        values.put(COLUMN_TIME,task.getTime());
        values.put(COLUMN_DATE,task.getDate());
        values.put(COLUMN_PR,task.getPriority());
        values.put(COLUMN_DONE,task.isDone());
        values.put(COLUMN_REMINDER,task.isReminder());
        values.put(COLUMN_REMINDED,task.ismReminded());

        db.update(TABLE_NAME,values,"ID=?",new String[] {Long.toString(task.getID())});
        close();
    }
}
