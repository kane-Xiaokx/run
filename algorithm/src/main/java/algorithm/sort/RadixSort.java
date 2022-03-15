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
                j = digit(arr[i],d); //一个数字,的个位数、十位数、百位数...是0 1 2 3 4..9的哪一位
                count[j]++; //如果是0 则count数据的index=0的数字 + 1
            }
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];//累加 index = 0 是 0～0的总和,index=1是0～1的总和...index=9是0～9的总和
            }
            for (i = R;  i >= L ; i--) {
                j = digit(arr[i],d); //从右到左查询个位数、十位数、百位数的数字的尾数在这轮排序中被放到了哪个桶里
                helper[--count[j]] = arr[i];//倒出来,并且该位置的数量-1
            }
            for (i = L,j = 0;i <= R ; i++,j++){
                arr[i] = helper[j]; //将这轮排序好的数字 放回去
            }

        }



    }

    private static int digit(int value,int bit){
        return (value / (int)Math.pow(value,bit - 1)) % 10;
    }



}
