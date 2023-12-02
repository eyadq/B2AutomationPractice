package com.loop.test.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
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
        logPrintFileMatch("image checksum", urllink, filePath);
    }

    public static void logPrintFileMatch (String tested, String urlLink, String filePath) {
        URL urlObject = null;
        URI uriObject = null;

        String fileExtension = filePath.substring(filePath.lastIndexOf('.'));
        String fileNameActual = filePath.substring(filePath.lastIndexOf('/')+1 , filePath.lastIndexOf('.')) + "-actual";
        String filePathActual = "src\\com\\loop\\test\\vercel\\data\\" + fileNameActual + fileExtension;

        byte[] dataActual = null;
        byte[] dataExpected = null;
        byte[] hashActual = null;
        byte[] hashExpected = null;

        String checksumExpected = null;
        String checksumActual = null;

        try {
            dataExpected = Files.readAllBytes(Path.of(filePath));
            hashExpected = MessageDigest.getInstance("MD5").digest(dataExpected);

            urlObject = new URL(urlLink);
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
            logPrintMatch(tested, checksumActual, checksumExpected);
        }


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

    public static void logPrintMatch(String tested, String[] actual, String[] expected){
        String output = "";
        if(Arrays.equals(expected, actual)){
            output = "TEST PASSED => The actual " + tested + " matches the expected " + tested + ":";
            System.out.println(output);
            for (int i = 0; i < actual.length; i++) {
                System.out.println("\t" + actual[i]);
            }
        } else {
            output = "TEST PASSED => The actual " + tested +  " does NOT match the expected " + tested + ":";
            System.err.println(output);
            for (int i = 0; i < actual.length; i++) {
                System.out.println("\tActual value: " + actual[i] + " does NOT match expected value: " + expected[i]);
            }
        }
    }

    public static void logPrintMatch(String tested, int actual, int expected) {
        logPrintMatch(tested, "" + actual, "" + expected);
    }

    public static void logPrintMatch(String tested, boolean actual, boolean expected) {
        logPrintMatch(tested, "" + actual, "" + expected);
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
