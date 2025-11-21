package com.brooksource.saydemo.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class DateStorage {
    public static int timestampToMonth(Timestamp t) {
        return timestampToMonth(t.toInstant());
    }
    public static int timestampToMonth(Instant i) {
        ZoneId zone = ZoneId.of("America/New_York");
        int yearValue = i.atZone(zone).getYear() - 1900;
        int monthValue = i.atZone(zone).getMonth().getValue() - 1;
        return monthValue + 12 * yearValue;
    }
    public static String monthValueToString(int monthValue) {
        int year = monthValue / 12;
        int month = monthValue % 12;
        Date date = new Date(year, month, 1);
        return new SimpleDateFormat("MMM yyyy").format(date);
    }
}
