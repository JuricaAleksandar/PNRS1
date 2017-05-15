package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int EDIT_TASK = 0;
    public static int ADD_TASK = 1;
    public static String sendButton1Code = "B1";
    public static String sendButton2Code = "B2";
    public static String positionCode = "Position";
    public static String returnButtonCode = "Button";
    public static String leftButtonCode = "Left";
    public static String rightButtonCode = "Right";
    public static String taskCode = "Task";
    public static String reqCode = "requestCode";
    public static ArrayList<Task> tasks;
    private ListAdapter adapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_TASK && resultCode == RESULT_OK) {
            if (data.getStringExtra(returnButtonCode).equals(leftButtonCode)){
                Bundle bundle = data.getBundleExtra(taskCode);
                Task task = (Task) bundle.get(taskCode);
                adapter.addTask(task);
            }
        }
        else if(requestCode == EDIT_TASK && resultCode == RESULT_OK) {
            if (data.getStringExtra(returnButtonCode).equals(leftButtonCode)) {

                Bundle bundle = data.getBundleExtra(taskCode);
                Task task = (Task) bundle.get(taskCode);
                adapter.editTask(data.getIntExtra(positionCode, 0), task);


            } else if (data.getStringExtra(returnButtonCode).equals(rightButtonCode))
                adapter.removeTask(data.getIntExtra(positionCode, 0));
        }
        tasks = adapter.getTaskList();
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra(reqCode, requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter = new ListAdapter(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = (ListView) findViewById(R.id.lv);
        final Intent addIntent = new Intent(this,AddActivity.class);
        final Intent statisticsIntent = new Intent(this,StatisticActivity.class);
        final Button addB = (Button) findViewById(R.id.buttonAddTask);
        final Button statB = (Button) findViewById(R.id.buttonStatistics);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task item = (Task)adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(taskCode,item);
                addIntent.putExtra(taskCode,bundle);
                addIntent.putExtra(sendButton1Code,R.string.buttonSaveText);
                addIntent.putExtra(sendButton2Code,R.string.buttonDeleteText);
                addIntent.putExtra(positionCode,position);
                startActivityForResult(addIntent,EDIT_TASK);
                return true;
            }
        });
        addB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addIntent.putExtra(sendButton1Code,R.string.buttonAddText);
                addIntent.putExtra(sendButton2Code,R.string.buttonCancelText);
                startActivityForResult(addIntent,ADD_TASK);
            }
        });
        statB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(statisticsIntent);
            }
        });
    }
}
