package com.onetech.mobilereader.uitls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thienlm on 8/16/2015.
 */
public class ValidationUtils {
    public static boolean validateEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
