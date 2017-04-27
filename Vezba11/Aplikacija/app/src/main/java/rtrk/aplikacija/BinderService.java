package rtrk.aplikacija;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BinderService extends Service {

    Klasa klasa = new Klasa();

    public BinderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return klasa;
    }
}
