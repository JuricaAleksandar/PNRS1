package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements ServiceConnection,
        View.OnClickListener,ListView.OnItemLongClickListener{

    public static int EDIT_TASK = 0;
    public static int ADD_TASK = 1;
    public static String idCode = "Id";
    public static String sendButton1Code = "B1";
    public static String sendButton2Code = "B2";
    public static String returnButtonCode = "Button";
    public static String leftButtonCode = "Left";
    public static String rightButtonCode = "Right";
    public static String reqCode = "requestCode";

    private ListView listView;
    private Intent addEditIntent;
    private Intent statisticsIntent;
    private Button addB;
    private Button statB;
    private DBHelper dbHelper;
    private AidlInterface mBinderInterface;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBHelper(this);
        adapter = new ListAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv);
        addB = (Button) findViewById(R.id.buttonAddTask);
        statB = (Button) findViewById(R.id.buttonStatistics);

        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);
        addB.setOnClickListener(this);
        statB.setOnClickListener(this);

        addEditIntent = new Intent(this,AddActivity.class);
        statisticsIntent = new Intent(this,StatisticActivity.class);

        Intent serviceIntent = new Intent(this, NotificationService.class);
        bindService(serviceIntent, this , BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateList(dbHelper.readTasks());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAddTask:
                addEditIntent.putExtra(sendButton1Code,R.string.buttonAddText);
                addEditIntent.putExtra(sendButton2Code,R.string.buttonCancelText);
                startActivityForResult(addEditIntent,ADD_TASK);
                break;
            case R.id.buttonStatistics:
                startActivity(statisticsIntent);
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        long taskID = ((Task)adapter.getItem(position)).getID();
        addEditIntent.putExtra(idCode,taskID);
        addEditIntent.putExtra(sendButton1Code,R.string.buttonSaveText);
        addEditIntent.putExtra(sendButton2Code,R.string.buttonDeleteText);
        startActivityForResult(addEditIntent,EDIT_TASK);
        return true;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra(reqCode, requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_TASK && resultCode == RESULT_OK) {
            if (data.getStringExtra(returnButtonCode).equals(leftButtonCode)){
                try {
                    mBinderInterface.notifyAdd();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode == EDIT_TASK && resultCode == RESULT_OK) {
            if (data.getStringExtra(returnButtonCode).equals(leftButtonCode)) {
                try {
                    mBinderInterface.notifyEdit();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            } else if (data.getStringExtra(returnButtonCode).equals(rightButtonCode)) {
                try {
                    mBinderInterface.notifyDelete();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mBinderInterface = AidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
