package algorithm.sort;

import java.util.Arrays;

/**
 * 计数排序
 * 缺点:只能排序一定范围的数据 比如 0～200
 * 额外空间复杂度O(n)
 * 时间复杂度O(n)
 */
public class CountSort {


    public static void sort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
             while ((bucket[i]--) != 0){
                 arr[index++] = i;
             }
        }
    }

    public static void compare(int[] arr){
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            sort(arr1);
            compare(arr2);
            if (!isEqual(arr1,arr2)){
                succeed = false;
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
                break;
            }
        }
        System.out.println(succeed ? "nice" : "fuck");

    }
    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }







}
