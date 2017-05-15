package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.Notification;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by student on 15.5.2017.
 */

public class CheckerThread extends Thread {

    private boolean mRun;
    private long PERIOD = 5000;
    private SimpleDateFormat format;
    private Context mContext;

    CheckerThread(Context context){
        super();
        mContext=context;
        format = new SimpleDateFormat("hh:mm");
    }

    @Override
    public synchronized void start() {
        mRun = true;
        super.start();
    }

    public synchronized void exit() {
        mRun = false;
    }

    @Override
    public void run() {
        while(mRun){
            int i;
            int len = MainActivity.tasks.size();
            if (len!=0) {
                Task[] mTasks = (Task[]) MainActivity.tasks.toArray();
                for (i = 0; i < len; i++) {
                    if (mTasks[i].mDate.equals("Today")) {
                        Calendar current = Calendar.getInstance();
                        Calendar taskTime = Calendar.getInstance();
                        try {
                            taskTime.setTime(format.parse(mTasks[i].mTime));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (taskTime.getTimeInMillis() - current.getTimeInMillis() < 900000) {
                            Notification noti = new Notification.Builder(mContext)
                                    .setContentTitle("Task reminder")
                                    .setContentText(mTasks[i].mName + " should be finished in less then 15 minutes!")
                                    .setSmallIcon(R.drawable.reminder)
                                    .build();
                        }
                    }
                }
            }
            try {
                sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
