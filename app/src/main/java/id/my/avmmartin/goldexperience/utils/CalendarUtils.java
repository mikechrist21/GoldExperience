package id.my.avmmartin.goldexperience.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarUtils {
    public static String toDateFormat(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_DATE, Locale.US);
        return sdf.format(calendar.getTime());
    }

    public static String toTimeFormat(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_TIME, Locale.US);
        return sdf.format(calendar.getTime());
    }

    public static String getFormattedDate(Calendar date, Calendar time) {
        return toDateFormat(date) + " " + toTimeFormat(time);
    }

    public static Calendar getEndOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar;
    }

    // constructor

    private CalendarUtils() {
        // none
    }
}
