package algorithm.util;

public class PrintHelper {


    public static void printBinary(int num){
        for (int i = 31; i >= 0 ; i--) {
            System.out.print(((num >> i) & 1) == 1 ? 1 : 0);
        }
    }



}
