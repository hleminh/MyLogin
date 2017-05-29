package com.example.hoang.mylogin.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.mylogin.R;
import com.example.hoang.mylogin.models.TaskModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hoang on 5/29/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<TaskModel> taskModelList;
    private Context context;

    public TaskAdapter(List<TaskModel> taskModelList, Context context) {
        this.taskModelList = taskModelList;
        this.context = context;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_list_tasks, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.setData(taskModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskModelList.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_payment)
        TextView tvPayment;
        @BindView(R.id.iv_check)
        CheckBox ivCheck;
        @BindView(R.id.iv_color)
        ImageView ivColor;
        @BindView(R.id.tv_color)
        TextView tvColor;
        @BindView(R.id.tv_local_id)
        TextView tvLocalId;

        public TaskHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(TaskModel taskModel) {
            tvName.setText(taskModel.getName());
            tvDate.setText(taskModel.getDue_date());
            tvPayment.setText(taskModel.getPayment_per_hour() + "");
            tvId.setText(taskModel.getId());
            if (taskModel.isDone()) {
                ivCheck.setChecked(true);
            } else ivCheck.setChecked(false);
            ivColor.setBackgroundColor(Color.parseColor(taskModel.getColor()));
            tvColor.setText(taskModel.getColor());
            tvLocalId.setText(taskModel.getLocal_id());
        }
    }
}
