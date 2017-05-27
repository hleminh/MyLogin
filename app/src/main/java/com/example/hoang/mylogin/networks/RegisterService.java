package com.example.hoang.mylogin.networks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Hoang on 5/27/2017.
 */

public interface RegisterService {
    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
