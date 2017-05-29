package com.example.hoang.mylogin.networks;

import com.example.hoang.mylogin.models.TaskModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Hoang on 5/29/2017.
 */

public interface GetTasksService {
    @GET("task")
    Call<List<TaskModel>> getTasks(@Header("Authorization") String token);
}
