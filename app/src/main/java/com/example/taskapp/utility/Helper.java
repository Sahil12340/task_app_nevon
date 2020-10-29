package com.example.taskapp.utility;

public class Helper {
    public static Integer convertToInt(String value) {
        try {
            if (value == null) return 0;
            return Integer.valueOf(value);

        } catch (NumberFormatException ex) {
            return 0;

        }
    }

}
