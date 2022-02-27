package algorithm.heap;

import algorithm.util.ArraysUtil;

import java.util.Arrays;

/**
 * 小根堆
 */
public class MinHeap {

    /**
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr,int index){
        while (arr[index] < arr[(index - 1) / 2]){
            ArraysUtil.swap(arr,index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr,int index,int limit){
        int left = index * 2 + 1;
        while (left < limit){
            int largest = left + 1 < limit && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if(largest == index){
                break;
            }
            ArraysUtil.swap(arr,largest,index);
            index = largest;
            left = largest * 2 + 1;
        }

    }


    public static void main(String[] args) {
        int[] arr = {9,8,7,5,6,2,3,1,4};
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        System.out.println(Arrays.toString(arr));
    }

    
}
