package com.loop.test.vercel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleSwing {

    enum ImageType{
        URL,
        FILE
    }

    public static void showImage(String urlActual, String filepathExpected){

        JLabel actualImage = createImage(ImageType.URL, urlActual);
        JLabel expectedImage = createImage(ImageType.FILE, filepathExpected);

        JLabel actualText = new JLabel("Actual");
        JLabel expectedText = new JLabel("Expected");

        JPanel imageRow = new JPanel();
        imageRow.add(actualImage);
        imageRow.add(expectedImage);
        imageRow.setLayout(new FlowLayout());

        JPanel textRow = new JPanel();
        textRow.setLayout(new FlowLayout(0, 100, 0));
        textRow.add(actualText, FlowLayout.LEFT);
        textRow.add(expectedText);

        JFrame frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        frame.add(imageRow);
        frame.add(textRow);


        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static JLabel createImage(ImageType type, String source){
        BufferedImage img = null;

        switch(type){
            case URL:
                URL urlObject = null;
                try{
                    urlObject = new URL(source);
                } catch (MalformedURLException e){
                }
                try {
                    img = ImageIO.read(urlObject);

                } catch (IOException e) {
                }
                break;
            case FILE:
                File fileObject = null;
                fileObject = new File(source);
                try {
                    img = ImageIO.read(fileObject);

                } catch (IOException e) {
                }
        }

        return  new JLabel(new ImageIcon(img));
    }

    public static void main(String[] args) {
        String link = "https://loopcamp.vercel.app/img/avatar-blank.jpg";
        String source = "src/com/loop/test/vercel/data/avatar-blank.jpg";

        showImage(link, source);
    }
}
