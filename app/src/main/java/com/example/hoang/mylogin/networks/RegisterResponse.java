package com.example.hoang.mylogin.networks;

/**
 * Created by Hoang on 5/27/2017.
 */

public class RegisterResponse {
    private String message;
    private int code;
    private String token;
    private String access_token;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public String getToken() {
        return token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
