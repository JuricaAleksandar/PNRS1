package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by student on 10.4.2017.
 */

public class ListAdapter extends BaseAdapter {

    private DBHelper mDBHelper;
    private Context mContext;
    private ArrayList<Task> mTaskList;
    private SimpleDateFormat format;

    public ListAdapter(Context context){
        mDBHelper = new DBHelper(context);
        mContext=context;
        mTaskList=new ArrayList<>();
        format = new SimpleDateFormat("dd.MM.yyyy.HH:mm");
    }

    public void updateList(ArrayList<Task> tasks){
        mTaskList = new ArrayList<>(tasks);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int position) {
        Object ob = null;
        try {
            ob = mTaskList.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return ob;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.task_element, null);
            ViewHolder holder = new ViewHolder();
            holder.priority = (LinearLayout) view.findViewById(R.id.lvTaskPriority);
            holder.time = (TextView) view.findViewById(R.id.lvTaskTime);
            holder.name = (TextView) view.findViewById(R.id.lvTaskName);
            holder.date = (TextView) view.findViewById(R.id.lvTaskDate);
            holder.done = (CheckBox) view.findViewById(R.id.lvTaskDone);
            holder.reminder = (ImageView) view.findViewById(R.id.lvTastReminder);
            view.setTag(holder);
        }
        final Task task = (Task) getItem(position);
        final ViewHolder holder = (ViewHolder) view.getTag();

        String dateString = returnDateString(task);

        holder.priority.setBackgroundResource(task.getPriority());
        holder.name.setText(task.getName());
        holder.date.setText(dateString);
        holder.time.setText(task.getTime());
        holder.done.setChecked(task.isDone()==1);
        holder.reminder.setImageResource(R.drawable.reminder);

        if(task.isDone()==1)
            holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else
            holder.name.setPaintFlags(holder.name.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.done.isChecked()) {
                    task.setDone(1);
                    mDBHelper.editTask(task);
                    updateList(mDBHelper.readTasks());
                } else{
                    task.setDone(0);
                    mDBHelper.editTask(task);
                    updateList(mDBHelper.readTasks());
                }
            }
        });

        if(task.isReminder()==1)
            holder.reminder.setVisibility(View.VISIBLE);
        else
            holder.reminder.setVisibility(View.INVISIBLE);

        return view;
    }

    private class ViewHolder {
        public LinearLayout priority = null;
        public TextView name = null;
        public TextView date = null;
        public TextView time = null;
        public CheckBox done = null;
        public ImageView reminder = null;
    }

    private boolean isLeapYear(int year){
        if((year%4 == 0 && year%100!=0)|| year%400==0)
            return true;
        else
            return false;
    }

    private String returnDateString(Task task){

        Resources res = mContext.getResources();
        Calendar currentDate = Calendar.getInstance();
        Calendar taskDate = Calendar.getInstance();

        String date = task.getDate()+task.getTime();
        try {
            taskDate.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int currentYear = currentDate.get(Calendar.YEAR);
        int currentDOY = currentDate.get(Calendar.DAY_OF_YEAR);
        int taskYear = taskDate.get(Calendar.YEAR);
        int taskDOY = taskDate.get(Calendar.DAY_OF_YEAR);
        int taskDOW = taskDate.get(Calendar.DAY_OF_WEEK);
        int taskDOM = taskDate.get(Calendar.DAY_OF_MONTH);
        int taskMonth = taskDate.get(Calendar.MONTH);

        if(currentYear == taskYear){
            if(currentDOY == taskDOY) {
                return res.getString(R.string.today);
            }
            else if(taskDOY - currentDOY == 1) {
                return res.getString(R.string.tomorrow);
            }
            else if((taskDOY - currentDOY) <= 7) {
                switch (taskDOW) {
                    case Calendar.MONDAY:
                        return res.getString(R.string.monday);
                    case Calendar.TUESDAY:
                        return res.getString(R.string.tuesday);
                    case Calendar.WEDNESDAY:
                        return res.getString(R.string.wednesday);
                    case Calendar.THURSDAY:
                        return res.getString(R.string.thursday);
                    case Calendar.FRIDAY:
                        return res.getString(R.string.friday);
                    case Calendar.SATURDAY:
                        return res.getString(R.string.saturday);
                    case Calendar.SUNDAY:
                        return res.getString(R.string.sunday);
                }
            }else {
                return (taskDOM + "." + (taskMonth + 1) + "." + taskYear + ".");
            }
        }else if(taskYear - currentYear == 1){
            if(isLeapYear(currentYear)) {
                if (taskDOY + 366 - currentDOY == 1) {
                    return res.getString(R.string.tomorrow);
                }
                else if ((taskDOY + 366 - currentDOY) <= 7) {
                    switch (taskDOW) {
                        case Calendar.MONDAY:
                            return res.getString(R.string.monday);
                        case Calendar.TUESDAY:
                            return res.getString(R.string.tuesday);
                        case Calendar.WEDNESDAY:
                            return res.getString(R.string.wednesday);
                        case Calendar.THURSDAY:
                            return res.getString(R.string.thursday);
                        case Calendar.FRIDAY:
                            return res.getString(R.string.friday);
                        case Calendar.SATURDAY:
                            return res.getString(R.string.saturday);
                        case Calendar.SUNDAY:
                            return res.getString(R.string.sunday);
                    }
                }else {
                    return (taskDOM + "." + (taskMonth + 1) + "." + taskYear + ".");
                }
            }else{
                if (taskDOY + 365 - currentDOY == 1) {
                    return res.getString(R.string.tomorrow);
                }
                else if ((taskDOY + 365 - currentDOY) <= 7) {
                    switch (taskDOW) {
                        case Calendar.MONDAY:
                            return res.getString(R.string.monday);
                        case Calendar.TUESDAY:
                            return res.getString(R.string.tuesday);
                        case Calendar.WEDNESDAY:
                            return res.getString(R.string.wednesday);
                        case Calendar.THURSDAY:
                            return res.getString(R.string.thursday);
                        case Calendar.FRIDAY:
                            return res.getString(R.string.friday);
                        case Calendar.SATURDAY:
                            return res.getString(R.string.saturday);
                        case Calendar.SUNDAY:
                            return res.getString(R.string.sunday);
                    }
                }else {
                    return (taskDOM + "." + (taskMonth + 1) + "." + taskYear + ".");
                }
            }
        }else
            return (taskDOM + "." + (taskMonth + 1) + "." + taskYear + ".");
        return (taskDOM + "." + (taskMonth + 1) + "." + taskYear + ".");
    }
}
