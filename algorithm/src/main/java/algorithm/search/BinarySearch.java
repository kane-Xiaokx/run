package algorithm.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 二分题目
 */
public class BinarySearch {

    /**
     * 二分查找,这个查找有一前提,就是data[]这个数组里面的值是有序的
     * 数组从左到右是从小到大排序的
     * @param data
     * @param target
     * @return
     */
    public static int binarySearchTarget(int[] data, int target){
        int L = 0;
        int R = data.length - 1;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if(data[mid] > target){//如果二分查找到的中间值大于目标,说明目标值在左半区域
                R = mid - 1;
            }else if(data[mid] < target){ //如果二分查找的中间值小于目标,说明目标值的右半区域
                L = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 递归版本的二分查找
     * @return
     */
    public static int binarySearchRecusing(int[] data,int L,int R,int target){

        int mid = L + ((R - L) >> 1);
        if(L <= R){
            if(data[mid] > target){
                return binarySearchRecusing(data,L,mid - 1,target);
            }else if(data[mid] < target){
                return binarySearchRecusing(data,mid + 1,R,target);
            }else if(target == data[mid]){
                return mid;
            }
        }
        return -1;
    }


    /**
     * 普通查找,用来和二分查找做对比
     * @param data
     * @param target
     * @return
     */
    public static int commonSearch(int data[],int target){
        for (int i = 0; i < data.length; i++) {
            if(data[i] == target){
                return i;
            }
        }
        return -1;
    }

    public static int[] generatorRandomArray(int maxLength,int maxValue){
        int length = (int)(Math.random() * (maxLength + 1));
        int[] result = new int[length];
        Set<Integer> hash = new HashSet<>(result.length);
        int i = 0;
        for (; i < result.length; ) {
            int value = (int) (((Math.random() * maxValue) + 1) - (Math.random() * maxValue));
            if(hash.add(value)){
                result[i] = value;
                i++;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int maxValue = 10000;
        int length = 1000;
        int testTimes = 10000;
        boolean success = true;
        for (int i = 0; i < testTimes; i++) {
            int target = (int) ((Math.random() * maxValue) + 1);
            int[] data = generatorRandomArray(length, maxValue);
            int a = binarySearchTarget(data,target);
            int b = binarySearchRecusing(data,0,data.length - 1,target);
            int c = commonSearch(data, target);
            if(a != b || a != c || b != c){
                System.out.println("a:"+a);
                System.out.println("b:"+b);
                System.out.println("c:"+c);
                System.out.println(Arrays.toString(data));
                System.out.println("target:"+target);
                success = false;
                break;
            }
        }
        System.out.println(success ? "success!" : "fail!");
    }


}
