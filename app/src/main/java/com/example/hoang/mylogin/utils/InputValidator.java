package com.example.hoang.mylogin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hoang on 5/27/2017.
 */

public class InputValidator {
    public static boolean passwordCharValidation(String passwordEd) {
        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.]).*$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordEd);
        if ((!passwordEd.matches(".*\\d.*") || !matcher.matches()) && passwordEd.length() >= 8) {
            return true;
        }
        return false;
    }

    public static boolean usernameCharValidation(String usernameEd) {
        String USERNAME_PATTERN = "^(?=.*[A-Z])(?=.*[@_.]).*$";
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(usernameEd);
        if ((!usernameEd.matches(".*\\d.*") || !matcher.matches()) && usernameEd.length() >= 8) {
            return true;
        }
        return false;
    }
}
