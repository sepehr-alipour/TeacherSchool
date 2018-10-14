package com.salsal.school.teacher.utils;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class Utils {

    public static String convertBirthdayToString(long timeStamp) {
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.setTimeInMillis(timeStamp*1000);
        return persianCalendar.getPersianShortDate();
    }
}
