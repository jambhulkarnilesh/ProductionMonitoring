package com.iot.pmonitor.utils;

import com.iot.pmonitor.exception.PMException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;

@Slf4j
public class DateTimeUtils {
    DateTimeUtils() {
    }

    public static String getFirstDayOfCurrentMonth() {
        LocalDate now = LocalDate.now();
        return now.withDayOfMonth(1).toString();
    }

    public static Instant convertStringToInstant(String dateTime) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormatter.parse(dateTime).toInstant();
        } catch (ParseException ex) {
            log.error("Inside DateTimeUtils >> convertStringToInstant()");
            throw new PMException("DateTimeUtils", false, ex.getMessage());
        }
    }

}
