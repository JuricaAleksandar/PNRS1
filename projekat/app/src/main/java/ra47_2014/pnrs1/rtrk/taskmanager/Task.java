package ra47_2014.pnrs1.rtrk.taskmanager;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by student on 10.4.2017.
 */

public class Task implements Serializable{
    public String mName;
    public String mTime;
    public String mDate;
    public String mDescription;
    public int mPriority;
    public boolean mReminder;

    public Task(String name,String time, String date,String description,int priority,boolean reminder) {
        mName = name;
        mTime = time;
        mPriority = priority;
        mDate = date;
        mReminder = reminder;
        mDescription = description;
    }
}
