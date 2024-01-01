package app.vercel.practice.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;
    private static final String PATH = "configuration.properties";

    static {
        try{
            FileInputStream input = new FileInputStream(PATH);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String keyName){
        return properties.getProperty(keyName);
    }
}
