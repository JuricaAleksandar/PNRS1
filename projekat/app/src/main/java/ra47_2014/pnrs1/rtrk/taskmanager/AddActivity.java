package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final Intent toMain = new Intent(this,MainActivity.class);
        final Button addButton = (Button) findViewById(R.id.buttonAdd);
        final Button cancelButton = (Button) findViewById(R.id.buttonCancel);
        final Button greenButton = (Button) findViewById(R.id.buttonGreen);
        final Button yellowButton = (Button) findViewById(R.id.buttonYellow);
        final Button redButton = (Button) findViewById(R.id.buttonRed);

        greenButton.setBackgroundColor(Color.rgb(30,154,30));
        redButton.setBackgroundColor(Color.rgb(204,0,0));
        yellowButton.setBackgroundColor(Color.rgb(255,194,0));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toMain);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toMain);
            }
        });
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenButton.setBackgroundColor(Color.rgb(170,255,170));
                redButton.setBackgroundColor(Color.rgb(204,0,0));
                yellowButton.setBackgroundColor(Color.rgb(255,194,0));
                greenButton.setEnabled(false);
                redButton.setEnabled(true);
                yellowButton.setEnabled(true);
            }
        });
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenButton.setBackgroundColor(Color.rgb(30,154,30));
                redButton.setBackgroundColor(Color.rgb(204,0,0));
                yellowButton.setBackgroundColor(Color.rgb(255,224,130));
                greenButton.setEnabled(true);
                redButton.setEnabled(true);
                yellowButton.setEnabled(false);
            }
        });
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenButton.setBackgroundColor(Color.rgb(30,154,30));
                redButton.setBackgroundColor(Color.rgb(255,170,170));
                yellowButton.setBackgroundColor(Color.rgb(255,194,0));
                greenButton.setEnabled(true);
                redButton.setEnabled(false);
                yellowButton.setEnabled(true);
            }
        });
    }
}
