package ra47_2014.pnrs1.rtrk.taskmanager;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by student on 10.4.2017.
 */

public class Task implements Serializable{
    private String mName;
    private String mTime;
    private String mDate;
    private String mDescription;
    private int mPriority;
    private int mReminder;
    private int mDone;
    private int mReminded;


    public int ismReminded() {
        return mReminded;
    }

    public void setmReminded() {
        this.mReminded = 1;
    }

    public Task(String name, String time, String date, String description, int priority, int reminder) {
        mName = name;
        mTime = time;
        mPriority = priority;
        mDate = date;
        mReminder = reminder;
        mDescription = description;
        mDone = 0;
        mReminded = 0;

    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int isDone() {
        return mDone;
    }

    public void setDone(int mDone) {
        this.mDone = mDone;
    }

    public int isReminder() {
        return mReminder;
    }

    public void setReminder() {
        this.mReminder = 1;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int mPriority) {
        this.mPriority = mPriority;
    }
}
