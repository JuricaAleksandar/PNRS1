package rtrk.aplikacija;
import android.os.RemoteException;


/**
 * Created by student on 27.4.2017.
 */

public class Klasa extends IMyAidlInterface.Stub{

    private int polje = 0;

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public int getValue(){
        return polje;
    }

    @Override
    public void setValue(int br){
        polje = br;
    }
}
