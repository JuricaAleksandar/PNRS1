package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service {

    private CheckerThread mCheckerThread;
    //private NotificationThread mNThread;

    @Override
    public void onCreate() {
        //mNThread = new NotificationThread();
        mCheckerThread = new CheckerThread(this);
        //mNThread.start();
        mCheckerThread.start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        //mNThread.exit();
        mCheckerThread.exit();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
