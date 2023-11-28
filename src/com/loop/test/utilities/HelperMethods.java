package com.loop.test.utilities;

public class HelperMethods {

    public static void main(String[] args) {
        HelperMethods.logPrintMatch("loop image link", "https://loopcamp.vercel.app/img/logo.svg", VercelConstants.IMAGE_URL_LOGO_LOOP);
    }

    public static void logPrintMatch(String tested, String actual, String expected){
        String output = "";
        if(expected.equals(actual)){
            output = "TEST PASSED => The actual " + tested + ": \"" + actual + "\" matches the expected " + tested + ": \"" + expected + "\"";
            System.out.println(output);
        } else {
            output = "TEST FAILED => The actual " + tested + ": \"" + actual + "\" does NOT match the expected " + tested + ": \"" + expected + "\"";
            System.err.println(output);
        }
    }

    public static void logPrintContains(String tested, String actual, String expected){
        String output = "";
        if(expected.contains(actual)){
            output = "TEST PASSED => The actual " + tested + ": \"" + actual + "\" is contained in the expected " + tested + ": \"" + expected + "\"";
            System.out.println(output);
        } else {
            output = "TEST FAILED => The actual " + tested + ": \"" + actual + "\" is NOT contained in the expected " + tested + ": \"" + expected + "\"";
            System.err.println(output);
        }
    }
}
