package com.loop.test.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HelperMethods {

    public static void main(String[] args) {
        //HelperMethods.logPrintMatch("loop image link", "https://loopcamp.vercel.app/img/logo.svg", VercelConstants.IMAGE_URL_LOGO_LOOP);

        String urllink = "https://loopcamp.vercel.app/img/avatar-blank.jpg";
        String filePath = "src/com/loop/test/vercel/data/avatar-blank.jpg";
//        logPrintFileMatch(urllink, filePath, "image checksum");

        String checksumActual = checksum(Enums.ImageType.URL, urllink);
        String checksumExpected = checksum(Enums.ImageType.FILE, filePath);
        logPrintMatch(checksumActual, checksumExpected, "checksum of image");
    }

    public static void logPrintFileMatch (String urlLinkActual, String filePathExpected, String message) {
        URL urlObject = null;
        URI uriObject = null;

        String fileExtension = filePathExpected.substring(filePathExpected.lastIndexOf('.'));
        String fileNameActual = filePathExpected.substring(filePathExpected.lastIndexOf('/')+1 , filePathExpected.lastIndexOf('.')) + "-actual";
        String filePathActual = "src\\com\\loop\\test\\vercel\\data\\" + fileNameActual + fileExtension;

        byte[] dataActual = null;
        byte[] dataExpected = null;
        byte[] hashActual = null;
        byte[] hashExpected = null;

        String checksumExpected = null;
        String checksumActual = null;

        try {
            dataExpected = Files.readAllBytes(Path.of(filePathExpected));
            hashExpected = MessageDigest.getInstance("MD5").digest(dataExpected);

            urlObject = new URL(urlLinkActual);
            uriObject = urlObject.toURI();

            //code from https://crunchify.com/5-different-ways-to-download-a-file-from-any-given-url-in-java/
            //only things we need to change here are URL object: urlObject and filepath for file to download: filePathActual
            ReadableByteChannel byteChannel = Channels.newChannel(urlObject.openStream());
            FileOutputStream outputStream = new FileOutputStream(filePathActual);
            outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            outputStream.close();
            byteChannel.close();

            dataActual = Files.readAllBytes(Path.of(filePathActual));
            hashActual = MessageDigest.getInstance("MD5").digest(dataActual);

            checksumExpected = new BigInteger(1, hashExpected).toString(16);
            checksumActual = new BigInteger(1, hashActual).toString(16);

            new File(filePathActual).delete();

        } catch (IOException e) {
            Thread.currentThread().getStackTrace();
        } catch (URISyntaxException e) {
            Thread.currentThread().getStackTrace();
        } catch (NoSuchAlgorithmException e){
            Thread.currentThread().getStackTrace();
        } catch (NullPointerException e) {
            Thread.currentThread().getStackTrace();
        }

        if(checksumActual == null){
            System.err.println("\"TEST FAILED => File or image verification failed. Check if file or image is reachable or check given links are accurate");
        } else {
            logPrintMatch(checksumActual, checksumExpected, message);
        }


    }

    public static String checksum (Enums.ImageType type, String link){

        byte[] data = null;
        byte[] hash = null;
        String checksum = null;

        String filePath = "";
        switch(type){
            case URL:

                URL urlObject = null;
                URI uriObject = null;

                //https://loopcamp.vercel.app/img/avatar-blank.jpg"
                String fileExtension = link.substring(link.lastIndexOf('.'));
                String fileNameActual = link.substring(link.lastIndexOf('/')+1 , link.lastIndexOf('.')) + "-actual";
                filePath = "src\\com\\loop\\test\\vercel\\data\\" + fileNameActual + fileExtension;

                try{
                    urlObject = new URL(link);
                    uriObject = urlObject.toURI();

                    //code from https://crunchify.com/5-different-ways-to-download-a-file-from-any-given-url-in-java/
                    //only things we need to change here are URL object: urlObject and filepath for file to download: filePathActual
                    ReadableByteChannel byteChannel = Channels.newChannel(urlObject.openStream());
                    FileOutputStream outputStream = new FileOutputStream(filePath);
                    outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
                    outputStream.close();
                    byteChannel.close();

                } catch (URISyntaxException e){
                    //Bad URI
                } catch (MalformedURLException e){
                    //Bad URL
                } catch (IOException e){
                    //openStream(), transferFrom(), close()
                }
                break;
            case FILE:
                filePath = link;
                break;
        }
        try {
            data = Files.readAllBytes(Path.of(filePath));
            hash = MessageDigest.getInstance("MD5").digest(data);
            checksum = new BigInteger(1, hash).toString(16);
        } catch(IOException | NoSuchAlgorithmException e){

        }
        return checksum;
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
            output = "TEST PASSED => The actual " + message +  " does NOT match the expected " + message + ":";
            System.err.println(output);
            for (int i = 0; i < actual.length; i++) {
                System.out.println("\tActual value: " + actual[i] + " does NOT match expected value: " + expected[i]);
            }
        }
    }

    public static void logPrintMatch(int actual, int expected, String message) {
        logPrintMatch("" + actual, "" + expected, message);
    }

    public static void logPrintMatch(boolean actual, boolean expected, String message) {
        logPrintMatch("" + actual, "" + expected, message);
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

}
