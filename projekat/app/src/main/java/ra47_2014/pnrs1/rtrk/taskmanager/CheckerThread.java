package ra47_2014.pnrs1.rtrk.taskmanager;

/**
 * Created by student on 15.5.2017.
 */

public class CheckerThread extends Thread {

    private boolean mRun;
    private long PERIOD = 30000;

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
            try {
                sleep(PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
