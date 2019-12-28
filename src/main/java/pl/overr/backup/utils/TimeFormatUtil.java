package pl.overr.backup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat();

    public static String timeFormater(long time){
        Date date = new Date(time);
        return sdf.format(date);
    }
}
