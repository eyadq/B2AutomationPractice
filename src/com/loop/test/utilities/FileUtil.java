package com.loop.test.utilities;

import com.loop.test.vercel.VercelTestBase;

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

public class FileUtil {

    public static void main(String[] args) {
        String link = "https://loopcamp.vercel.app/img/avatar-blank.jpg";

        String checksumActual = getChecksum(downloadFile(link));
        String checksumExpected = getChecksum(getExpectedFilePathFromURL(link));
        System.out.println("checksumActual = " + checksumActual);
        System.out.println("checksumExpected = " + checksumExpected);
        //LogUtil.logPrintMatch(checksumActual, checksumExpected, "checksum of image");

    }

    public static String downloadFile(String link){
        String fileExtension = link.substring(link.lastIndexOf('.'));
        String fileNameActual = link.substring(link.lastIndexOf('/')+1 , link.lastIndexOf('.')) + "-actual";
        String filePath = VercelTestBase.MAIN_BINARY_RESOURCE_DIR + fileNameActual + fileExtension;

        URL urlObject;
        URI uriObject;

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

        return filePath;
    }

    public static String getExpectedFilePathFromURL(String link){
        String fileExtension = link.substring(link.lastIndexOf('.'));
        String fileName = link.substring(link.lastIndexOf('/')+1 , link.lastIndexOf('.'));
        return VercelTestBase.MAIN_BINARY_RESOURCE_DIR + fileName + fileExtension;
    }

    public static String getChecksum(String filePath){

        byte[] data = null;
        byte[] hash = null;
        String checksum = null;

        try {
            data = Files.readAllBytes(Path.of(filePath));
            hash = MessageDigest.getInstance("MD5").digest(data);
            checksum = new BigInteger(1, hash).toString(16);
        } catch(IOException | NoSuchAlgorithmException e){

        }
        return checksum;
    }
}
