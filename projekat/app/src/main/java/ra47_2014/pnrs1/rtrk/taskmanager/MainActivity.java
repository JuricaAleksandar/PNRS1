package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private int EDIT_TASK=0;
    private int ADD_TASK=1;
    private ListAdapter adapter = new ListAdapter(MainActivity.this);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_TASK && resultCode == RESULT_OK) {
            Bundle bundle = data.getBundleExtra("Task");
            Task task = (Task)bundle.get("Task");
            adapter.addTask(task);
        }
        else if(requestCode == EDIT_TASK && resultCode == RESULT_OK) {
            if (data.getStringExtra("Button").equals("Left")) {

                Bundle bundle = data.getBundleExtra("Task");
                Task task = (Task) bundle.get("Task");
                adapter.editTask(data.getIntExtra("Position", 0), task);

            } else if (data.getStringExtra("Button").equals("Right"))
                adapter.removeTask(data.getIntExtra("Position", 0));
        }
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                bundle.putSerializable("Task",item);
                addIntent.putExtra("Task",bundle);
                addIntent.putExtra("B1",R.string.buttonSaveText);
                addIntent.putExtra("B2",R.string.buttonDeleteText);
                addIntent.putExtra("Position",position);
                startActivityForResult(addIntent,EDIT_TASK);
                return true;
            }
        });
        addB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addIntent.putExtra("B1",R.string.buttonAddText);
                addIntent.putExtra("B2",R.string.buttonCancelText);
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
