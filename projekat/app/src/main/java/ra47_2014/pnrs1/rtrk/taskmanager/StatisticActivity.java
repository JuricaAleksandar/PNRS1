package ra47_2014.pnrs1.rtrk.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatisticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatisticsView statisticsView = new StatisticsView(getApplicationContext());
        setContentView(statisticsView);
    }
}
