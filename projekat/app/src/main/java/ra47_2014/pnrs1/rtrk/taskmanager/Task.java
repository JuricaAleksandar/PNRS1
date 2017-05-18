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
    private boolean mReminder;
    private boolean mDone;


    public Task(String name,String time, String date,String description,int priority,boolean reminder) {
        mName = name;
        mTime = time;
        mPriority = priority;
        mDate = date;
        mReminder = reminder;
        mDescription = description;
        mDone = false;
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

    public boolean isDone() {
        return mDone;
    }

    public void setDone(boolean mDone) {
        this.mDone = mDone;
    }

    public boolean isReminder() {
        return mReminder;
    }

    public void setReminder(boolean mReminder) {
        this.mReminder = mReminder;
    }

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int mPriority) {
        this.mPriority = mPriority;
    }
}
