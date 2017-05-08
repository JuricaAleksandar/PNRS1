import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import rtrk.baze.MainActivity;

/**
 * Created by student on 8.5.2017.
 */

public class StudentDBHelper extends SQLiteOpenHelper {

    public StudentDBHelper(Context context) {
        super(context, "Baza.db", null, MainActivity.dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Students(FirstName text,LastName text,IndexNumber text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertStudent(Student s){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",s.getFirstName());
        contentValues.put("LastName",s.getLastName());
        contentValues.put("IndexNumber",s.getIndexNumber());
        db.insert("Students",null,contentValues);
        db.close();
    }

    public ArrayList<Student> readStudents(){
        ArrayList<Student> students = new ArrayList<Student>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Students",null,null,null,null,null,null,null);
        for(cursor.moveToFirst();cursor.isAfterLast();cursor.moveToNext()){
            students.add(createStudent(cursor));
        }
        return students;
    }

    public Student createStudent(Cursor cursor){
        String firstName;
        String lastName;
        String indexNumber;
        firstName = cursor.getString(cursor.getColumnIndex("FirstName"));
        lastName = cursor.getString(cursor.getColumnIndex("LastName"));
        indexNumber = cursor.getString(cursor.getColumnIndex("IndexNumber"));
        return new Student(firstName,lastName,indexNumber);
    }
}
