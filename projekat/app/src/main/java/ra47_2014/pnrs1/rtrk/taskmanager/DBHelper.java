package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by student on 29.5.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    public DBHelper(Context context) {
        super(context, MainActivity.databaseName, null, MainActivity.dbVersion);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = mContext.openOrCreateDatabase(MainActivity.databaseName, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + MainActivity.tableName
                + " (" + MainActivity.COLUMN1 + "text,"
                + " (" + MainActivity.COLUMN2 + "text,"
                + " (" + MainActivity.COLUMN3 + "text,"
                + " (" + MainActivity.COLUMN4 + "text,"
                + " (" + MainActivity.COLUMN5 + "int,"
                + " (" + MainActivity.COLUMN6 + "int,"
                + " (" + MainActivity.COLUMN7 + "int);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }
}
