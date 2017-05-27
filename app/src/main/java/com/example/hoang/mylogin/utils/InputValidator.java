package com.example.hoang.mylogin.utils;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hoang on 5/27/2017.
 */

public class InputValidator {
    private static final String PASSWORD_PATTERN = "^(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,10})$";
    private static final String USERNAME_PATTERN = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,20})$";

    public static boolean isValidPassword(String passwordEd) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordEd);
        return matcher.matches();
    }

    public static boolean isValidUsername(String usernameEd) {
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(usernameEd);
        return matcher.matches();
    }

    public static boolean isValidEmail(String emailEd) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(emailEd);
        return matcher.matches();
    }
}
