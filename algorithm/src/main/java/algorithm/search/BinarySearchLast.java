package algorithm.search;

/**
 * 二分查找一个有序的数组中的一个数的最左边的位置或者最右边的位置
 */
public class BinarySearchLast {


    public static int lastLeft(int[] data,int target){
        int index = 0;
        int L = 0;
        int R = data.length - 1;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if(data[mid] >= target){
                index = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static int lastRight(int[] data,int target){
        int index = 0;
        int L = 0;
        int R = data.length - 1;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if(data[mid] <= target){
                index = mid;
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] data = {1,2,2,2,2,3,4,5,6,7,8,9,10};
        int i = lastRight(data, 2);
        System.out.println(i);
    }


}
