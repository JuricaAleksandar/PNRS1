package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListAdapter adapter = new ListAdapter(MainActivity.this);
        final ListView listView = (ListView) findViewById(R.id.lv);
        final Intent addIntent = new Intent(this,AddActivity.class);
        final Intent statisticsIntent = new Intent(this,StatisticActivity.class);
        final Button addB = (Button) findViewById(R.id.buttonAddTask);
        final Button statB = (Button) findViewById(R.id.buttonStatistics);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                addIntent.putExtra("B1",R.string.buttonSaveText);
                addIntent.putExtra("B2",R.string.buttonDeleteText);
                startActivity(addIntent);
                return true;
            }
        });
        adapter.addTask(new Task("Zadatak 1", "10:30", "25.4.2017.", R.color.yellowButton, true));
        adapter.addTask(new Task("Zadatak 2", "11:30", "25.4.2017.", R.color.greenButton, false));
        addB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addIntent.putExtra("B1",R.string.buttonAddText);
                addIntent.putExtra("B2",R.string.buttonCancelText);
                startActivity(addIntent);
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
