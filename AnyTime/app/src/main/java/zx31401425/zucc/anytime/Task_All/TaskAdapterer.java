package zx31401425.zucc.anytime.Task_All;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import zx31401425.zucc.anytime.R;

/**
 * Created by 赵轩 on 2018/3/22.
 */

public class TaskAdapterer extends ArrayAdapter<Task>{

    private int resourceId;

    public TaskAdapterer(Context context, int textViewResourceId,
                        List<Task> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View converView,ViewGroup parent){
        Task task = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(converView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.task_id = (TextView) view.findViewById(R.id.task_id);
            viewHolder.task_PublisherUname = (TextView) view.findViewById(R.id.task_PublisherUname);
            //viewHolder.task_ReceiveUname = (TextView) view.findViewById(R.id.task_ReceiveUname);
            viewHolder.task_TaskDetails = (TextView) view.findViewById(R.id.task_TaskDetails);
            //viewHolder.task_TaskState = (TextView) view.findViewById(R.id.task_TaskState);
            view.setTag(viewHolder);
        }else {
            view = converView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.task_id.setText(task.getTaskId());
        //viewHolder.task_TaskState.setText(task.getTaskState());
       // viewHolder.task_ReceiveUname.setText(task.getReceiveUname());
        viewHolder.task_PublisherUname.setText(task.getPublisherUname());
        viewHolder.task_TaskDetails.setText(task.getTaskDetails());
        return  view;
    }

    class ViewHolder {

        TextView task_id;

        TextView task_PublisherUname;

        TextView task_TaskDetails;

        //TextView task_ReceiveUname;

        //TextView task_TaskState;
    }
}
