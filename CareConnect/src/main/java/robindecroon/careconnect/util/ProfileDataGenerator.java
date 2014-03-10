package robindecroon.careconnect.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by robindecroon on 08/03/14.
 */
public class ProfileDataGenerator {

    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static GregorianCalendar mGregorianCalendar = new GregorianCalendar();

    public static String getRandomDate() {
        int year = randBetween(2011, 2013);
        mGregorianCalendar.set(mGregorianCalendar.YEAR, year);
        int dayOfYear = randBetween(1, mGregorianCalendar.getActualMaximum(mGregorianCalendar.DAY_OF_YEAR));
        mGregorianCalendar.set(mGregorianCalendar.DAY_OF_YEAR, dayOfYear);

        Date date = mGregorianCalendar.getTime();
        mGregorianCalendar.add(mGregorianCalendar.DAY_OF_YEAR, -5);

        return mSimpleDateFormat.format(date);
    }

    public static String getRandomINSZ(String birthday) {

        String[] dates = birthday.split("/");


        int year = Integer.parseInt(dates[2].substring(2));
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[0]);

        int number = randBetween(1,999);

        String total = "" + year + month + day + number;
        int totalInt = Integer.parseInt(total);

        int mod = totalInt % 97;

        int lastNumber = 97 - mod;

        return year + "." + month + "." + day + "-" + number + "." +lastNumber;

    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }


}
