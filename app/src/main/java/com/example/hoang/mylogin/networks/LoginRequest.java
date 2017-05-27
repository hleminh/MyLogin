package com.example.hoang.mylogin.networks;

/**
 * Created by Hoang on 5/27/2017.
 */

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
