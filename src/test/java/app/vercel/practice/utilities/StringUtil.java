package app.vercel.practice.utilities;

public class StringUtil {

    public static String toNameCase(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
