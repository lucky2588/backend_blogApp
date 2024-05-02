package com.demo.softdreams.shared.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat DATE_TIME_FORMAT_EMAIL = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final SimpleDateFormat DATE_TIME_WITH_SECOND_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat DATE_TIME_FORMAT_BLACK_NUT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssXXX");


    public static Date getSimpleDateFormat(String date) throws ParseException {
        return date == null ? null : new Date(DATE_TIME_FORMAT.parse(date).getTime());
    }

    public static Date getDate(Long date) throws ParseException {
        return date == null ? null : new Date(date);
    }

    public static String formatDateEmail(Date date) {
        return DATE_TIME_FORMAT_EMAIL.format(date);
    }
    public static String formatDateTimeWithSecond(Date date) {
        return DATE_TIME_WITH_SECOND_FORMAT.format(date);
    }

    public static String formatDateTimeBlackNut(Date date) {
        DATE_TIME_FORMAT_BLACK_NUT.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return DATE_TIME_FORMAT_BLACK_NUT.format(date);
    }

    public static String getFormatddMMyyyyHHmm(Date date) throws ParseException {
        String pattern = "dd/MM/yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(DateUtil.getDate(date.getTime()));
    }

    public static String getFormatddMMyyyy(Long date) throws ParseException {
        String pattern = "ddMMyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(DateUtil.getDate(date));
    }

    public static String getDateFormat(String pattern,Date date) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(DateUtil.getDate(date.getTime()));
    }

    public static String getISO8601StringForDate(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(tz);
        return dateFormat.format(date);
    }
}
