package com.example.android.body;



public class TextUtils {
    public static String getExtension(String string) {
        if (string.length() == 3) {
            return string;
        } else if (string.length() > 3) {
            return string.substring(string.length() - 3);
        } else {
            throw new IllegalArgumentException(" the Word has less than 3 characters");
        }
    }

}
