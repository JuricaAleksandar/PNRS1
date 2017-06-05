package ra47_2014.pnrs1.rtrk.taskmanager;

/**
 * Created by aleksandar on 5.6.17..
 */

public class CalculateStatistics {

    public native float calculate(float done,float total);

    static{
        System.loadLibrary("statistics");
    }
}
