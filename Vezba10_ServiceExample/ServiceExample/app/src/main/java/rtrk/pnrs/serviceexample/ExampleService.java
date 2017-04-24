package rtrk.pnrs.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ExampleService extends Service {

    private static final String LOG_TAG = "ExampleService";
    private static final long PERIOD = 1000L;

    private ThreadExample mThread;
    private RunnableExample mRunnable;


    @Override
    public void onCreate() {
        super.onCreate();
        mThread = new ThreadExample();
        mThread.start();

        mRunnable = new RunnableExample();
        mRunnable.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThread.exit();
        mRunnable.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class ThreadExample extends Thread {
        private boolean mRun = true;

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
            while(mRun) {
                Log.d(LOG_TAG, "Hello from thread");
                try {
                    Thread.sleep(PERIOD); //milliseconds
                } catch (InterruptedException e) {
                    // interrupted finish thread
                    break;
                }
            }
        }
    }

    private class RunnableExample implements Runnable {
        private Handler mHandler;
        private boolean mRun = false;

        public RunnableExample() {
            mHandler = new Handler(getMainLooper());
        }

        public void start() {
            mRun = true;
            mHandler.postDelayed(this, PERIOD);
        }

        public void stop() {
            mRun = false;
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            if (!mRun) {
                return;
            }

            Log.d(LOG_TAG, "Hello from Runnable");
            mHandler.postDelayed(this, PERIOD);
        }
    }
}
