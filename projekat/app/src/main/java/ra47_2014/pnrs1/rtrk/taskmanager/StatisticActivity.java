package ra47_2014.pnrs1.rtrk.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class StatisticActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rbLow;
    private RadioButton rbMed;
    private RadioButton rbHigh;
    private StatisticsView pieChart;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        mDBHelper = new DBHelper(this);
        pieChart = (StatisticsView) findViewById(R.id.pieChart);
        rbLow = (RadioButton) findViewById(R.id.rbLow);
        rbMed = (RadioButton) findViewById(R.id.rbMedium);
        rbHigh = (RadioButton) findViewById(R.id.rbHigh);
        rbLow.setOnClickListener(this);
        rbMed.setOnClickListener(this);
        rbHigh.setOnClickListener(this);
        rbLow.setChecked(true);
        rbLow.callOnClick();
    }

    @Override
    public void onClick(View v) {
        ArrayList<Task> tasks = mDBHelper.readTasks();
        float done = 0;
        float total = 0;
        switch(v.getId()){
            case R.id.rbHigh:
                for(Task t:tasks)
                    if(t.getPriority()==R.color.redButton){
                        total++;
                        if(t.isDone()==1)
                            done++;
                    }
                rbLow.setClickable(true);
                rbMed.setClickable(true);
                rbHigh.setClickable(false);
                pieChart.init();
                if(total==0)
                    pieChart.setPercentage(0.f);
                else
                    pieChart.setPercentage((done/total)*100);
                pieChart.setColor(R.color.redButton);
                pieChart.setBgdColor(R.color.redButtonPressed);
                pieChart.invalidate();
                break;
            case R.id.rbMedium:
                for(Task t:tasks)
                    if(t.getPriority()==R.color.yellowButton){
                        total++;
                        if(t.isDone()==1)
                            done++;
                    }
                rbLow.setClickable(true);
                rbMed.setClickable(false);
                rbHigh.setClickable(true);
                pieChart.init();
                if(total==0)
                    pieChart.setPercentage(0.f);
                else
                    pieChart.setPercentage((done/total)*100);
                pieChart.setColor(R.color.yellowButton);
                pieChart.setBgdColor(R.color.yellowButtonPressed);
                pieChart.invalidate();
                break;
            case R.id.rbLow:
                for(Task t:tasks)
                    if(t.getPriority()==R.color.greenButton){
                        total++;
                        if(t.isDone()==1)
                            done++;
                    }
                rbLow.setClickable(false);
                rbMed.setClickable(true);
                rbHigh.setClickable(true);
                pieChart.init();
                if(total==0)
                    pieChart.setPercentage(0.f);
                else
                    pieChart.setPercentage((done/total)*100);
                pieChart.setColor(R.color.greenButton);
                pieChart.setBgdColor(R.color.greenButtonPressed);
                pieChart.invalidate();
                break;
        }
    }
}
