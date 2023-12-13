package com.loop.test.utilities;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class DateUtil {

    static Random random;

    static {
        random = new Random();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getRandomDate()));
        System.out.println(Arrays.toString(getTodaysDate()));
    }

    public static String[] getTodaysDate(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy, MMMM, d");
        String date = sdf.format(new Date());
        return date.split(", ");

    }
    public static String[] getRandomDate(){
        int year = getRandomYear();
        String month = getRandomMonth();
        int day = getRandomDay(month);
        return new String[]{String.valueOf(year), month, String.valueOf(day)};
    }
    public static int getRandomYear(){
        int[] yearRange = getIntArray(1924, 2023);
        return yearRange[random.nextInt(yearRange.length)];
    }

    public static String[] getMonths(){
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August","September", "October", "November", "December" };
    }

    public static int getMonths(String month){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August","September", "October", "November", "December" };
        int monthInInt = 0;
        for (int i = 0; i < months.length; i++) {
            if(month.equals(months[i])){
                monthInInt = i;
            }
        }
        return monthInInt;
    }

    public static String getRandomMonth(){
        String[] months = getMonths();
        return months[random.nextInt(11)];
    }

    public static int getRandomMonthInteger(){
        return random.nextInt(11);
    }

    public static int getNumberDaysInMonth(String month){
        int days = 0;
        //System.out.println("month = " + month);
        switch (month){
            case "February":
                days = 28;
                break;
            case "April": case "June": case "September": case "November":
                days = 30;
                break;
            default:
                days = 31;
        }
        if(isLeap()){
            return days++;
        }
        return days;
    }

    public static int getRandomDay(String month){
        return random.nextInt(getNumberDaysInMonth(month));
    }

    public static int getNumberDaysInMonth(int month){
        int days = 0;
        switch (month){
            case 1:
                days = 28;
                break;
            case 3: case 5: case 8: case 10:
                days = 30;
                break;
            default:
                days = 31;
        }
        if(isLeap()){
            return days++;
        }
        return days;
    }

    public static int getRandomDay(int month){
        return random.nextInt(getNumberDaysInMonth(month));
    }

    public static int[] getIntArray(int start, int end){
        int length = end - start + 1;
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = end - i;
        }
        return arr;
    }

    public static boolean isLeap(){
        String[] today = DateUtil.getTodaysDate();
        String[] leapArray = DateUtil.getTodaysDate();
        leapArray[1] = "February";
        leapArray[2] = "29";
        boolean isLeap = true;
        for (int i = 0; i < leapArray.length; i++) {
            if(!leapArray[i].equals(today[i])){
                isLeap = false;
            }
        }
        return isLeap;
    }
}
