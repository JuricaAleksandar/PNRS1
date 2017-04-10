package ra47_2014.pnrs1.rtrk.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        mTaskList=new ArrayList<Task>();
    }

    public void addTask(Task task){
        mTaskList.add(task);
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
            holder.priority = (LinearLayout) view.findViewById(R.id.lvPriority);
            holder.name = (TextView) view.findViewById(R.id.lvTaskName);
            view.setTag(holder);
        }

        Task task = (Task) getItem(position);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.priority.setBackgroundResource(task.mPriority);
        holder.name.setText(task.mName);

        return view;
    }

    private class ViewHolder {
        public LinearLayout priority = null;
        public TextView name = null;
    }
}
