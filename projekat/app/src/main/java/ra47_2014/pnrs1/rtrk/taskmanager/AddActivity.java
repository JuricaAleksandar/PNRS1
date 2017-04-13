package ra47_2014.pnrs1.rtrk.taskmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    protected Calendar chosenDateAndTime;
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
        final CheckBox reminder = (CheckBox) findViewById(R.id.checkBoxRemind);
        chosenDateAndTime=Calendar.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskName.getText().toString().isEmpty())
                    taskName.requestFocus();
                else if(taskDesc.getText().toString().isEmpty())
                    taskDesc.requestFocus();
                else if(timePick.getText().toString().equals(getString(R.string.textViewTimeText)))
                    timePick.callOnClick();
                else if(datePick.getText().toString().equals(getString(R.string.textViewDateText)))
                    datePick.callOnClick();
                else if(redButton.isEnabled() && yellowButton.isEnabled() && greenButton.isEnabled())
                    greenButton.callOnClick();
                else{

                    int priority;

                    if(!greenButton.isEnabled())
                        priority = R.color.greenButton;
                    else if(!yellowButton.isEnabled())
                        priority = R.color.yellowButton;
                    else
                        priority = R.color.redButton;

                    Bundle bundle = new Bundle();
                    Task task = new Task(taskName.getText().toString(),timePick.getText().toString(),
                            datePick.getText().toString(),taskDesc.getText().toString(),
                            priority,reminder.isChecked());
                    bundle.putSerializable("Task",task);
                    toMain.putExtra("Task",bundle);
                    toMain.putExtra(MainActivity.returnButtonCode, "Left");
                    int toastText;
                    if (getIntent().getIntExtra("requestCode",0)== MainActivity.EDIT_TASK){
                        toMain.putExtra("Position", getIntent().getIntExtra("Position", 0));
                        toastText=R.string.changesSaved;
                    }else
                        toastText=R.string.taskCreated;
                    if (getParent() == null) {
                        setResult(RESULT_OK,toMain);
                    } else {
                        getParent().setResult(RESULT_OK,toMain);
                    }
                    Toast toast = Toast.makeText(getApplicationContext(),toastText, Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain.putExtra(MainActivity.returnButtonCode, "Right");
                if (getIntent().getIntExtra("requestCode", 0) == MainActivity.EDIT_TASK){
                    toMain.putExtra("Position", getIntent().getIntExtra("Position", 0));
                    Toast toast = Toast.makeText(getApplicationContext(),R.string.taskDeleted, Toast.LENGTH_SHORT);
                    toast.show();

                }
                if (getParent() == null) {
                    setResult(RESULT_OK,toMain);
                } else {
                    getParent().setResult(RESULT_OK, toMain);
                }
                finish();
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
                final Calendar currentTime = Calendar.getInstance();
                final TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        chosenDateAndTime.set(chosenDateAndTime.get(Calendar.YEAR),
                                chosenDateAndTime.get(Calendar.MONTH),
                                chosenDateAndTime.get(Calendar.DAY_OF_MONTH),hourOfDay,minute);
                        if (chosenDateAndTime.compareTo(currentTime)>=0) {
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
                            int currentHour=currentTime.get(Calendar.HOUR_OF_DAY);
                            int currentMinute=currentTime.get(Calendar.MINUTE);
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
                            if(chosenDateAndTime.getTimeInMillis()-currentTime.getTimeInMillis()<-60000){
                                Toast toast = Toast.makeText(getApplicationContext(),R.string.timeDateError, Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            chosenDateAndTime=Calendar.getInstance();
                        }
                    }
                },chosenDateAndTime.get(Calendar.HOUR_OF_DAY),chosenDateAndTime.get(Calendar.MINUTE),true);
                timePickerDialog.setTitle(R.string.selectTimeTitle);
                timePickerDialog.show();
            }
        });

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                final DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        chosenDateAndTime.set(year,month,dayOfMonth,
                                chosenDateAndTime.get(Calendar.HOUR_OF_DAY),chosenDateAndTime.get(Calendar.MINUTE));
                        if (chosenDateAndTime.compareTo(currentDate)>=0) {
                            if (chosenDateAndTime.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)) {
                                if (chosenDateAndTime.get(Calendar.DAY_OF_YEAR) == currentDate.get(Calendar.DAY_OF_YEAR))
                                    datePick.setText(R.string.today);
                                else if (chosenDateAndTime.get(Calendar.DAY_OF_YEAR) - currentDate.get(Calendar.DAY_OF_YEAR) == 1)
                                    datePick.setText(R.string.tomorrow);
                                else if (chosenDateAndTime.get(Calendar.DAY_OF_YEAR) - currentDate.get(Calendar.DAY_OF_YEAR) >= 2 &&
                                        chosenDateAndTime.get(Calendar.DAY_OF_YEAR) - currentDate.get(Calendar.DAY_OF_YEAR) < 7) {
                                    switch (chosenDateAndTime.get(Calendar.DAY_OF_WEEK)) {
                                        case Calendar.MONDAY:
                                            datePick.setText(R.string.monday);
                                            break;
                                        case Calendar.TUESDAY:
                                            datePick.setText(R.string.tuesday);
                                            break;
                                        case Calendar.WEDNESDAY:
                                            datePick.setText(R.string.wednesday);
                                            break;
                                        case Calendar.THURSDAY:
                                            datePick.setText(R.string.thursday);
                                            break;
                                        case Calendar.FRIDAY:
                                            datePick.setText(R.string.friday);
                                            break;
                                        case Calendar.SATURDAY:
                                            datePick.setText(R.string.saturday);
                                            break;
                                        case Calendar.SUNDAY:
                                            datePick.setText(R.string.sunday);
                                            break;

                                    }
                                } else
                                    datePick.setText(dayOfMonth + "." + (month + 1) + "." + year + ".");
                            }
                        }
                        else {
                            /*datePick.setText(currentDate.get(Calendar.DAY_OF_MONTH) + "."
                                    + (currentDate.get(Calendar.MONTH) + 1)
                                    + "." + currentDate.get(Calendar.YEAR) + ".");*/
                            datePick.setText(R.string.today);
                            if(chosenDateAndTime.getTimeInMillis()-currentDate.getTimeInMillis()<-60000) {
                                Toast toast = Toast.makeText(getApplicationContext(), R.string.timeDateError, Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            chosenDateAndTime=Calendar.getInstance();
                        }
                    }
                },chosenDateAndTime.get(Calendar.YEAR),chosenDateAndTime.get(Calendar.MONTH),
                        chosenDateAndTime.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle(R.string.selectDateTitle);
                datePickerDialog.show();
            }
        });

        addButton.setText(getIntent().getIntExtra("B1",0));
        cancelButton.setText(getIntent().getIntExtra("B2",0));
        if(getIntent().getIntExtra("requestCode",0) == MainActivity.EDIT_TASK){
            Bundle bundle = getIntent().getBundleExtra("Task");
            Task task = (Task)bundle.get("Task");
            taskName.setText(task.mName);
            taskDesc.setText(task.mDescription);
            timePick.setText(task.mTime);
            datePick.setText(task.mDate);
            reminder.setChecked(task.mReminder);

            switch (task.mPriority){
                case R.color.redButton:
                    redButton.callOnClick();
                    break;
                case R.color.yellowButton:
                    yellowButton.callOnClick();
                    break;
                default:
                    greenButton.callOnClick();
                    break;
            }
        }
    }
}
