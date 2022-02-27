package algorithm.sort;

/**
 * 基数排序
 *排序的数字不能小于0
 */
public class RadixSort {


    public static void radixSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        radixSort(arr,0,arr.length - 1,maxBit(arr));
    }

    private static int maxBit(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int ans = 0;
        while (max > 0){
            max /= 10;
            ans++;
        }
        return ans;

    }

    private static void radixSort(int[] arr,int L,int R,int maxBit){
        int radix = 10;
        int i , j = 0;
        int[] helper = new int[R - L  + 1];
        for (int d = 1; d <= maxBit; d++) {
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = digit(arr[i],d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = R;  i >= L ; i--) {
                j = digit(arr[i],d);
                helper[--count[j]] = arr[i];
            }
            for (i = L,j = 0;i <= R ; i++,j++){
                arr[i] = helper[j];
            }

        }



    }

    private static int digit(int value,int bit){
        return (value / (int)Math.pow(value,bit - 1)) % 10;
    }



}
