package com.example.hoang.mylogin.networks;

/**
 * Created by Hoang on 5/27/2017.
 */

public class LoginResponse {
    private String description;
    private String error;
    private int status_code;
    private String access_token;

    public String getDescription() {
        return description;
    }

    public String getError() {
        return error;
    }

    public int getStatus_code() {
        return status_code;
    }

    public String getAccess_token() {
        return access_token;
    }
}
