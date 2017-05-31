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
                + " (" + MainActivity.COLUMN_NAME + "TEXT,"
                + " (" + MainActivity.COLUMN_DESC + "TEXT,"
                + " (" + MainActivity.COLUMN_TIME + "TEXT,"
                + " (" + MainActivity.COLUMN_DATE + "TEXT,"
                + " (" + MainActivity.COLUMN_PR + "INTEGER,"
                + " (" + MainActivity.COLUMN_DONE + "INTEGER,"
                + " (" + MainActivity.COLUMN_REMINDER + "INTEGER,"
                + " (" + MainActivity.COLUMN_REMINDED + "INTEGER);");
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
