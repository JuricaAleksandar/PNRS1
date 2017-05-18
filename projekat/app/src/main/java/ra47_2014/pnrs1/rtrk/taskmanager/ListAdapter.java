package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Context;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by student on 10.4.2017.
 */

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Task> mTaskList;

    public ListAdapter(Context context){
        mContext=context;
        mTaskList=new ArrayList<>();
    }

    public void addTask(Task task){
        mTaskList.add(task);
        notifyDataSetChanged();
    }
    public void removeTask(int position){
        mTaskList.remove(position);
        notifyDataSetChanged();
    }

    public void editTask(int position,Task task){
        mTaskList.remove(position);
        mTaskList.add(position,task);
        notifyDataSetChanged();
    }

    public ArrayList<Task> getTaskList(){
        return mTaskList;
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

        Task task = (Task) getItem(position);
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.priority.setBackgroundResource(task.getPriority());
        holder.name.setText(task.getName());
        holder.date.setText(task.getDate());
        holder.time.setText(task.getTime());
        holder.done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.done.isChecked())
                    holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                else
                    holder.name.setPaintFlags(holder.name.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });
        holder.reminder.setImageResource(R.drawable.reminder);
        if(task.isReminder())
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
}
