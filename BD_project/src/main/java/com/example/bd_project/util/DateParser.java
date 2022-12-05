package com.example.bd_project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    private DateParser(){}

    public static Date readDate() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.parse(str);
    }

    public static String getString(Date date) {
        SimpleDateFormat a = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        return a.format(date).toUpperCase();
    }
}
