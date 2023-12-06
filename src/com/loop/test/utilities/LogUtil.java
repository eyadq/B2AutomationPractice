package com.loop.test.utilities;

import java.util.Arrays;

public class LogUtil {

    public static void main(String[] args) {
        //HelperMethods.logPrintMatch("loop image link", "https://loopcamp.vercel.app/img/logo.svg", VercelConstants.IMAGE_URL_LOGO_LOOP);
    }

    public static void logPrintMatch(String actual, String expected, String message) {
        String output = "";
        if(expected.equals(actual)){
            output = "TEST PASSED => The actual " + message + ": \"" + actual + "\" matches the expected " + message + ": \"" + expected + "\"";
            System.out.println(output);
        } else {
            output = "TEST FAILED => The actual " + message + ": \"" + actual + "\" does NOT match the expected " + message + ": \"" + expected + "\"";
            System.err.println(output);
        }
    }

    public static void logPrintMatch(String[] actual, String[] expected, String message){
        String output = "";
        if(Arrays.equals(expected, actual)){
            output = "TEST PASSED => The actual " + message + " matches the expected " + message + ":";
            System.out.println(output);
            for (int i = 0; i < actual.length; i++) {
                System.out.println("\t" + actual[i]);
            }
        } else {
            output = "TEST FAILED => The actual " + message +  " does NOT match the expected " + message + ":";
            System.err.println(output);
            for (int i = 0; i < actual.length; i++) {
                System.out.println("\tActual value: " + actual[i] + " does NOT match expected value: " + expected[i]);
            }
        }
    }

    public static void logPrintContains(String actual, String expected, String message){
        String output = "";
        if(expected.contains(actual)){
            output = "TEST PASSED => The actual " + message + ": \"" + actual + "\" is contained in the expected " + message + ": \"" + expected + "\"";
            System.out.println(output);
        } else {
            output = "TEST FAILED => The actual " + message + ": \"" + actual + "\" is NOT contained in the expected " + message + ": \"" + expected + "\"";
            System.err.println(output);
        }
    }

    public static void logPrintMatch(int actual, int expected, String message) {
        logPrintMatch("" + actual, "" + expected, message);
    }

    public static void logPrintMatch(boolean actual, boolean expected, String message) {
        logPrintMatch("" + actual, "" + expected, message);
    }
}
