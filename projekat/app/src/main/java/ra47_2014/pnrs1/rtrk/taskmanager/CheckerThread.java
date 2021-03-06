package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;

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
    private DBHelper mDBHelper;
    private Uri notificationSound;

    CheckerThread(Context context){
        super();
        mContext = context;
        notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        format = new SimpleDateFormat("dd.MM.yyyy.HH:mm");
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mDBHelper = new DBHelper(mContext);
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
            String msg = "Tasks to be finished in 15 minutes: ";
            boolean notiHasItems=false;
            boolean notify = false;
            for (Task t:mDBHelper.readTasks()) {
                Calendar current = Calendar.getInstance();
                Calendar taskTime = Calendar.getInstance();
                String date = t.getDate()+t.getTime();
                try {
                    taskTime.setTime(format.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (current.get(Calendar.YEAR)==taskTime.get(Calendar.YEAR) &&
                        current.get(Calendar.MONTH)==taskTime.get(Calendar.MONTH) &&
                        current.get(Calendar.DAY_OF_MONTH)==taskTime.get(Calendar.DAY_OF_MONTH) &&
                        t.isReminder()==1 &&
                        t.isDone()==0
                        ){
                    if ( taskTime.get(Calendar.HOUR_OF_DAY) == current.get(Calendar.HOUR_OF_DAY) ) {
                        if(taskTime.get(Calendar.MINUTE)-current.get(Calendar.MINUTE)<=15 && taskTime.get(Calendar.MINUTE)-current.get(Calendar.MINUTE)>=0) {
                            if (notiHasItems)
                                msg += " , " + t.getName();
                            else
                                msg += t.getName();
                            notiHasItems = true;
                        }
                    }else if (taskTime.get(Calendar.HOUR_OF_DAY) - current.get(Calendar.HOUR_OF_DAY) == 1) {
                        if(taskTime.get(Calendar.MINUTE)+60-current.get(Calendar.MINUTE)<=15 && taskTime.get(Calendar.MINUTE)+60-current.get(Calendar.MINUTE)>=0){
                            if (notiHasItems)
                                msg += " , " + t.getName();
                            else
                                msg += t.getName();
                            notiHasItems = true;
                        }
                    }
                    if(t.ismReminded() == 0) {
                        t.setmReminded();
                        mDBHelper.editTask(t);
                        notify = true;
                    }
                }
            }
            if(notiHasItems && notify) {
                mBuilder = new Notification.Builder(mContext)
                        .setContentTitle(mContext.getString(R.string.notificationTitle))
                        .setSmallIcon(R.drawable.reminder)
                        .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.ic_launcher ))
                        .setSound(notificationSound)
                        .setContentText(msg)
                        .setVibrate(new long[]{500, 500});
                mNotificationManager.notify(0, mBuilder.build());
            }
            try {
                sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
