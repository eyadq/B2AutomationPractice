package app.vercel.practice.utilities;

public class ArrayUtil {
    public static int[] getIntArray(int start, int end){
        int length = end - start + 1;
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = end - i;
        }
        return arr;
    }
}
