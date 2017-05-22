package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service {

    private CheckerThread mCheckerThread;
    private ServiceNotifier mServiceNotifier;

    @Override
    public void onCreate() {
        mServiceNotifier = new ServiceNotifier(this);
        mCheckerThread = new CheckerThread(this);
        mCheckerThread.start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mCheckerThread.exit();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mServiceNotifier;
    }
}
