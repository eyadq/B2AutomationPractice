package app.vercel.practice.utilities;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class DateUtil {

    public static void main(String[] args) {
        System.out.println(getRandomDate());

        System.out.println(Arrays.toString(ArrayUtil.getIntArray(LocalDate.now().getYear()-99, LocalDate.now().getYear())));
    }
    public static LocalDate getRandomDate(){


        Random random = new Random();
        int[] yearRange = ArrayUtil.getIntArray(LocalDate.now().getYear()-99, LocalDate.now().getYear());
        int year = yearRange[random.nextInt(yearRange.length)];
        Month month = Month.values()[random.nextInt(11) + 1];
        int day = new Random().nextInt(month.length(isLeapYear())-1) +1;
        return LocalDate.of(year, month, day);
    }

    public static boolean isLeapYear() {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, LocalDate.now().getYear());
            return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
        }
}
