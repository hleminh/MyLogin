package com.example.hoang.mylogin.networks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hoang on 5/27/2017.
 */

public interface LoginService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
