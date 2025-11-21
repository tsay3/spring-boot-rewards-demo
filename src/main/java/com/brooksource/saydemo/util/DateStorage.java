package com.brooksource.saydemo.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
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
        LocalDate date = LocalDate.of(year, month, 1);
        return new SimpleDateFormat("MMM yyyy").format(date);
    }

    public static String generationMessage() {
        StringBuilder returnHTML = new StringBuilder("<hr><i>Generated on ");
        returnHTML.append(DateStorage.monthValueToString(
                DateStorage.timestampToMonth(Instant.now())));
        returnHTML.append("</i>");
        return returnHTML.toString();
    }
}
