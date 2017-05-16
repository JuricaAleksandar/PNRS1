package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
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
    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;

    CheckerThread(Context context){
        super();
        mContext=context;
        format = new SimpleDateFormat("hh:mm");
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(mContext)
                .setContentTitle("Task reminder")
                .setSmallIcon(R.drawable.reminder);
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
            String msg = "Task to be finished in 15 minutes: ";
            boolean notiHasItems=false;
            for (Task t:MainActivity.tasks) {
                if (t.mDate.equals("Today") && t.mReminder) {
                    Calendar current = Calendar.getInstance();
                    Calendar taskTime = Calendar.getInstance();
                    try {
                        taskTime.setTime(format.parse(t.mTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if ( taskTime.get(Calendar.HOUR_OF_DAY) == current.get(Calendar.HOUR_OF_DAY) ) {
                        if(taskTime.get(Calendar.MINUTE)-current.get(Calendar.MINUTE)<=15 && taskTime.get(Calendar.MINUTE)-current.get(Calendar.MINUTE)>=0) {
                            if (notiHasItems)
                                msg += " , " + t.mName;
                            else
                                msg += t.mName;
                            notiHasItems = true;
                        }
                    }else if (taskTime.get(Calendar.HOUR_OF_DAY) - current.get(Calendar.HOUR_OF_DAY) == 1) {
                        if(taskTime.get(Calendar.MINUTE)+60-current.get(Calendar.MINUTE)<=15 && taskTime.get(Calendar.MINUTE)+60-current.get(Calendar.MINUTE)>=0){
                            if (notiHasItems)
                                msg += " , " + t.mName;
                            else
                                msg += t.mName;
                            notiHasItems = true;
                        }
                    }
                }
            }
            if(notiHasItems) {
                mBuilder.setContentText(msg);
                mNotificationManager.notify(0, mBuilder.build());
            }else{
                mNotificationManager.cancel(0);
            }
            try {
                sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
