package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.RemoteException;

/**
 * Created by student on 18.5.2017.
 */

public class ServiceNotifier extends AidlInterface.Stub{

    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;
    private Context mContext;

    ServiceNotifier(Context context){
        mContext = context;
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(mContext)
                .setContentTitle("Task alert")
                .setSmallIcon(R.drawable.reminder)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.ic_launcher ));

    }

    @Override
    public void notifyEdit() throws RemoteException {
       mBuilder.setContentText("Task edited!");
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void notifyAdd() throws RemoteException {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setContentText("Task added!");
        mNotificationManager.notify(1, mBuilder.build());
    }

    @Override
    public void notifyDelete() throws RemoteException {
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.setContentText("Task deleted!");
        mNotificationManager.notify(1, mBuilder.build());
    }
}