package app.vercel.test.pages;

import app.vercel.test.utilities.FileUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimpleSwing {

    enum ImageType{
        URL,
        FILE
    }

    public static void showImage(String filePathActual, String filepathExpected){

        JLabel actualImage = createImage(filePathActual);
        JLabel expectedImage = createImage(filepathExpected);

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

    public static JLabel createImage(String filePath){
        BufferedImage img = null;

        File fileObject = null;
        fileObject = new File(filePath);
        try {
            img = ImageIO.read(fileObject);

        } catch (IOException e) {
        }




        return  new JLabel(new ImageIcon(img));
    }

    public static void main(String[] args) {
        String link = "https://loopcamp.vercel.app/img/avatar-blank.jpg";
        String filePathActual = FileUtil.downloadFile(link);
        String filePathExpected = FileUtil.getExpectedFilePathFromURL(link);

        showImage(filePathActual, filePathExpected);
        new File(filePathActual).delete();
    }
}
