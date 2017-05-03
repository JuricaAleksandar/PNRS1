package ra47_2014.pnrs1.rtrk.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class StatisticActivity extends AppCompatActivity {

    Button mButton;
    RadioButton rbLow;
    RadioButton rbMed;
    RadioButton rbHigh;
    StatisticsView pieChart;
    StatisticActivity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        thisActivity = this;

        mButton = (Button) findViewById(R.id.buttonBack);
        pieChart = (StatisticsView) findViewById(R.id.pieChart);
        final RadioButton rbLow = (RadioButton) findViewById(R.id.rbLow);
        final RadioButton rbMed = (RadioButton) findViewById(R.id.rbMedium);
        final RadioButton rbHigh = (RadioButton) findViewById(R.id.rbHigh);

        rbLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbLow.setClickable(false);
                rbMed.setClickable(true);
                rbHigh.setClickable(true);
                pieChart.init();
                pieChart.setPercentage(13.f);
                pieChart.setColor(R.color.greenButton);
                pieChart.setBgdColor(R.color.greenButtonPressed);
                pieChart.invalidate();
            }
        });

        rbMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbLow.setClickable(true);
                rbMed.setClickable(false);
                rbHigh.setClickable(true);
                rbMed.toggle();
                pieChart.init();
                pieChart.setPercentage(73.f);
                pieChart.invalidate();
                pieChart.setColor(R.color.yellowButton);
                pieChart.setBgdColor(R.color.yellowButtonPressed);
                pieChart.invalidate();
            }
        });

        rbHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbLow.setClickable(true);
                rbMed.setClickable(true);
                rbHigh.setClickable(false);
                pieChart.init();
                pieChart.setPercentage(49.f);
                pieChart.setColor(R.color.redButton);
                pieChart.setBgdColor(R.color.redButtonPressed);
                pieChart.invalidate();
            }
        });
        rbLow.setChecked(true);
        rbLow.callOnClick();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisActivity.finish();
            }
        });
    }
}
