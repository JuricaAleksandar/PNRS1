package ra47_2014.pnrs1.rtrk.taskmanager;

/**
 * Created by student on 10.4.2017.
 */

public class Task {
    public String mName;
    public String mTime;
    public String mDate;
    public int mPriority;
    public boolean mReminder;

    public Task(String name,String time, String date,int priority,boolean reminder) {
        mName = name;
        mTime = time;
        mPriority = priority;
        mDate = date;
        mReminder = reminder;
    }
}
