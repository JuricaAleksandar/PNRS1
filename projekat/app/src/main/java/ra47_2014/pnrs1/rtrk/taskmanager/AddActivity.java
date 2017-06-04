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
import java.util.Random;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private long id;
    private Intent toMain;
    private Button addButton;
    private Button cancelButton;
    private Button greenButton;
    private Button yellowButton;
    private Button redButton;
    private EditText taskName;
    private EditText taskDesc;
    private TextView timePick;
    private TextView datePick;
    private CheckBox reminder;
    private DBHelper mDBHelper;

    protected Calendar chosenDateAndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        toMain = new Intent(this,MainActivity.class);
        mDBHelper = new DBHelper(this);

        addButton = (Button) findViewById(R.id.buttonAdd);
        cancelButton = (Button) findViewById(R.id.buttonCancel);
        greenButton = (Button) findViewById(R.id.buttonGreen);
        yellowButton = (Button) findViewById(R.id.buttonYellow);
        redButton = (Button) findViewById(R.id.buttonRed);
        taskName = (EditText) findViewById(R.id.editTaskName);
        taskDesc = (EditText) findViewById(R.id.editTaskDesc);
        timePick = (TextView) findViewById(R.id.textViewTime);
        datePick = (TextView) findViewById(R.id.textViewDate);
        reminder = (CheckBox) findViewById(R.id.checkBoxRemind);
        chosenDateAndTime=Calendar.getInstance();

        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        yellowButton.setOnClickListener(this);
        redButton.setOnClickListener(this);
        timePick.setOnClickListener(this);
        datePick.setOnClickListener(this);

        addButton.setText(getIntent().getIntExtra(MainActivity.sendButton1Code,0));
        cancelButton.setText(getIntent().getIntExtra(MainActivity.sendButton2Code,0));

        if(getIntent().getIntExtra(MainActivity.reqCode,0) == MainActivity.EDIT_TASK){
            id = getIntent().getLongExtra(MainActivity.idCode,0);
            Task task = mDBHelper.readTask(id);
            taskName.setText(task.getName());
            taskDesc.setText(task.getDescription());
            timePick.setText(task.getTime());
            datePick.setText(task.getDate());
            reminder.setChecked(task.isReminder()==1);

            switch (task.getPriority()){
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd:
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

                    Task task = new Task(id,taskName.getText().toString(),timePick.getText().toString(),
                            datePick.getText().toString(),taskDesc.getText().toString(),
                            priority,0,reminder.isChecked()?1:0,0);

                    toMain.putExtra(MainActivity.returnButtonCode, MainActivity.leftButtonCode);

                    int toastText;

                    if (getIntent().getIntExtra(MainActivity.reqCode,0)== MainActivity.EDIT_TASK){
                        mDBHelper.editTask(task);
                        toastText=R.string.changesSaved;
                    }else {
                        Random random = new Random();
                        do {
                            id = (long)(random.nextDouble()*10000000);
                            task.setID(id);
                        }while(mDBHelper.idExists(task));
                        mDBHelper.insert(task);
                        toastText = R.string.taskCreated;
                    }
                    if (getParent() == null) {
                        setResult(RESULT_OK,toMain);
                    } else {
                        getParent().setResult(RESULT_OK,toMain);
                    }
                    Toast toast = Toast.makeText(getApplicationContext(),toastText, Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
                break;
            case R.id.buttonCancel:
                toMain.putExtra(MainActivity.returnButtonCode,MainActivity.rightButtonCode);
                if (getIntent().getIntExtra(MainActivity.reqCode, 0) == MainActivity.EDIT_TASK){
                    mDBHelper.deleteTask(id);
                    Toast toast = Toast.makeText(getApplicationContext(),R.string.taskDeleted, Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (getParent() == null) {
                    setResult(RESULT_OK,toMain);
                } else {
                    getParent().setResult(RESULT_OK, toMain);
                }
                finish();
                break;
            case R.id.buttonRed:
                greenButton.setBackgroundResource(R.color.greenButton);
                redButton.setBackgroundResource(R.color.redButtonPressed);
                yellowButton.setBackgroundResource(R.color.yellowButton);
                greenButton.setEnabled(true);
                redButton.setEnabled(false);
                yellowButton.setEnabled(true);
                break;
            case R.id.buttonYellow:
                greenButton.setBackgroundResource(R.color.greenButton);
                redButton.setBackgroundResource(R.color.redButton);
                yellowButton.setBackgroundResource(R.color.yellowButtonPressed);
                greenButton.setEnabled(true);
                redButton.setEnabled(true);
                yellowButton.setEnabled(false);
                break;
            case R.id.buttonGreen:
                greenButton.setBackgroundResource(R.color.greenButtonPressed);
                redButton.setBackgroundResource(R.color.redButton);
                yellowButton.setBackgroundResource(R.color.yellowButton);
                greenButton.setEnabled(false);
                redButton.setEnabled(true);
                yellowButton.setEnabled(true);
                break;
            case R.id.textViewTime:
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
                break;
            case R.id.textViewDate:
                final Calendar currentDate = Calendar.getInstance();
                final DatePickerDialog datePickerDialog;
                datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        chosenDateAndTime.set(year,month,dayOfMonth,
                                chosenDateAndTime.get(Calendar.HOUR_OF_DAY),chosenDateAndTime.get(Calendar.MINUTE));
                        if (chosenDateAndTime.compareTo(currentDate)>=0)
                            datePick.setText(dayOfMonth + "." + (month + 1) + "." + year + ".");
                        else {
                            if(chosenDateAndTime.getTimeInMillis()-currentDate.getTimeInMillis()<-60000) {
                                Toast toast = Toast.makeText(getApplicationContext(), R.string.timeDateError, Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            chosenDateAndTime=Calendar.getInstance();
                            datePick.setText(chosenDateAndTime.get(Calendar.DAY_OF_MONTH) + "." +
                                    (chosenDateAndTime.get(Calendar.MONTH)+1) + "." +
                                    chosenDateAndTime.get(Calendar.YEAR) + ".");
                        }
                    }
                },chosenDateAndTime.get(Calendar.YEAR),chosenDateAndTime.get(Calendar.MONTH),
                        chosenDateAndTime.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle(R.string.selectDateTitle);
                datePickerDialog.show();
                break;
        }
    }
}
