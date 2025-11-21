package com.brooksource.saydemo.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateStorage {
    public static int timestampToMonth(Timestamp t) {
        return timestampToMonth(t.toInstant());
    }
    public static int timestampToMonth(Instant i) {
        ZoneId zone = ZoneId.of("America/New_York");
        int yearValue = i.atZone(zone).getYear();
        int monthValue = i.atZone(zone).getMonth().getValue() - 1;
        return monthValue + 12 * yearValue;
    }
    public static String monthValueToString(int monthValue) {
        int year = monthValue / 12;
        int month = monthValue % 12;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM yyyy");
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        return cal.toZonedDateTime().format(dtf).toString();
    }

    public static String generationMessage() {
        StringBuilder returnHTML = new StringBuilder("<hr><i>Generated on ");
        returnHTML.append(DateStorage.monthValueToString(
                DateStorage.timestampToMonth(Instant.now())));
        returnHTML.append("</i>");
        return returnHTML.toString();
    }
}
