package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

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
        final EditText taskName = (EditText) findViewById(R.id.editTaskName);
        final EditText taskDesc = (EditText) findViewById(R.id.editTaskDesc);
        final TextView timePick = (TextView) findViewById(R.id.textViewTime);
        final TextView datePick = (TextView) findViewById(R.id.textViewDate);

        addButton.setOnClickListener(new View.OnClickListener() {
            String tmp1 = "Pick task time (click here)";
            String tmp2 = "Pick task date (click here)";
            @Override
            public void onClick(View v) {
                if(taskName.getText().toString().isEmpty())
                    taskName.requestFocus();
                else if(taskDesc.getText().toString().isEmpty())
                    taskDesc.requestFocus();
                else if(timePick.getText().toString().equals(tmp1))
                    timePick.callOnClick();
                else if(datePick.getText().toString().equals(tmp2))
                    datePick.callOnClick();
                else if(redButton.isEnabled() && yellowButton.isEnabled() && greenButton.isEnabled())
                    greenButton.callOnClick();
                else
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
                greenButton.setBackgroundResource(R.color.greenButtonPressed);
                redButton.setBackgroundResource(R.color.redButton);
                yellowButton.setBackgroundResource(R.color.yellowButton);
                greenButton.setEnabled(false);
                redButton.setEnabled(true);
                yellowButton.setEnabled(true);
            }
        });
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenButton.setBackgroundResource(R.color.greenButton);
                redButton.setBackgroundResource(R.color.redButton);
                yellowButton.setBackgroundResource(R.color.yellowButtonPressed);
                greenButton.setEnabled(true);
                redButton.setEnabled(true);
                yellowButton.setEnabled(false);
            }
        });
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenButton.setBackgroundResource(R.color.greenButton);
                redButton.setBackgroundResource(R.color.redButtonPressed);
                yellowButton.setBackgroundResource(R.color.yellowButton);
                greenButton.setEnabled(true);
                redButton.setEnabled(false);
                yellowButton.setEnabled(true);
            }
        });

        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar chosenTime = Calendar.getInstance();
                final Calendar currentTime = Calendar.getInstance();
                final int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
                final int currentMinute = currentTime.get(Calendar.MINUTE);
                final TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        chosenTime.set(chosenTime.get(Calendar.YEAR),chosenTime.get(Calendar.MONTH),chosenTime.get(Calendar.DAY_OF_MONTH),hourOfDay,minute);
                        if (chosenTime.compareTo(currentTime)>=0) {
                            if (hourOfDay < 10) {
                                if (minute < 10)
                                    timePick.setText("0" + hourOfDay + ":0" + minute);
                                else
                                    timePick.setText("0" + hourOfDay + ":" + minute);
                            } else {
                                if (minute < 10)
                                    timePick.setText(hourOfDay + ":0" + minute);
                                else
                                    timePick.setText(hourOfDay + ":" + minute);
                            }
                        }
                        else{
                            if (currentHour < 10) {
                                if (currentMinute < 10)
                                    timePick.setText("0" + currentHour + ":0" + currentMinute);
                                else
                                    timePick.setText("0" + currentHour + ":" + currentMinute);
                            } else {
                                if (minute < 10)
                                    timePick.setText(currentHour + ":0" + currentMinute);
                                else
                                    timePick.setText(currentHour + ":" + currentMinute);
                            }
                        }
                    }
                },currentHour,currentMinute,true);
                timePickerDialog.setTitle("Select task time:");
                timePickerDialog.show();
            }
        });

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                final Calendar chosenDate = Calendar.getInstance();
                final int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
                final int currentMonth = currentDate.get(Calendar.MONTH);
                final int currentYear = currentDate.get(Calendar.YEAR);
                final DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        chosenDate.set(year,month,dayOfMonth);
                        if (chosenDate.compareTo(currentDate)>=0)
                            datePick.setText(dayOfMonth + "." + (month + 1) + "." + year + ".");
                        else
                            datePick.setText(currentDay + "." + (currentMonth + 1) + "." + currentYear + ".");
                    }
                },currentYear,currentMonth,currentDay);
                datePickerDialog.setTitle("Select task date: ");
                datePickerDialog.show();
            }
        });
    }
}
