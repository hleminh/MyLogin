package com.example.hoang.mylogin.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.hoang.mylogin.R;
import com.example.hoang.mylogin.adapters.TaskAdapter;
import com.example.hoang.mylogin.models.TaskModel;
import com.example.hoang.mylogin.networks.GetTasksService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TasksActivity extends AppCompatActivity {
    @BindView(R.id.tb_tasks)
    Toolbar tbTasks;
    @BindView(R.id.rv_tasks)
    RecyclerView rvTasks;

    private TaskAdapter taskAdapter;
    private List<TaskModel> taskModelList;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ButterKnife.bind(this);
        token = "JWT " + getIntent().getStringExtra("token");
        System.out.println("Token: " + token);
        tbTasks.setTitle("Tasks list");
        tbTasks.setTitleTextColor(getResources().getColor(R.color.white));
        tbTasks.setBackgroundColor(getResources().getColor(R.color.bittersweet));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://a-task.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetTasksService getTasksService = retrofit.create(GetTasksService.class);
        getTasksService.getTasks(token).enqueue(new Callback<List<TaskModel>>() {
            @Override
            public void onResponse(Call<List<TaskModel>> call, Response<List<TaskModel>> response) {
                if (response.code() == 200) {
                    System.out.println("200");
                    taskModelList = response.body();
                    Toast.makeText(TasksActivity.this, "All tasks loaded.", Toast.LENGTH_SHORT).show();
                    taskAdapter = new TaskAdapter(taskModelList, getApplicationContext());
                    taskAdapter.notifyDataSetChanged();
                    rvTasks.setAdapter(taskAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    rvTasks.setLayoutManager(linearLayoutManager);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvTasks.getContext(),
                            linearLayoutManager.getOrientation());
                    rvTasks.addItemDecoration(dividerItemDecoration);
                } else if (response.code() == 401) {
                    System.out.println("401");
                    Toast.makeText(TasksActivity.this, "Invalid JWT Header", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("Wat");
                }
            }

            @Override
            public void onFailure(Call<List<TaskModel>> call, Throwable t) {
                System.out.println("No connection.");
                System.out.println(t.getMessage());
                Toast.makeText(TasksActivity.this, "No connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
