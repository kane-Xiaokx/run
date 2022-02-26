package algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {


    /**
     * 归并排序--递归的方式
     * @param arr
     */
    public static void mergeSortRecession(int[] arr,int L,int R){
        //不需要排序
        if(arr == null || arr.length < 2 || (R - L) <= 0){
            return;
        }
        //获得中间点
        int mid = (L + ((R - L) >> 1));
        mergeSortRecession(arr,L,mid); //求左边的的数组有序
        mergeSortRecession(arr,mid + 1 , R);
        merge(arr,L,mid,R);
    }

    /**
     * 普通的归并排序
     * @param arr
     */
    public static void mergeSortCommon(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int length = arr.length;
        int mergeSize = 1; //步长
        while (mergeSize < length){
            int L = 0;
            while (L < length){
                if(mergeSize >= length - L){ //如果剩下的左边的数组,没有 步长 大,那么忽略,因为没有大于步长,那么说明没有右边的数组,不需要合并
                    break;
                }
                //获得中点位置,因为左边的数组是 L-> mid
                //而右边的数组是 mid + 1 -> R
                int mid = L + mergeSize;
                int R =  Math.min(mid + mergeSize + 1, length - 1);
                merge(arr,L,mid,R);
                L = R + 1;
            }
            if(mergeSize > (length >> 1)){
                break;
            }
            mergeSize = mergeSize << 1;
        }

    }

    private static void merge(int[] arr,int L ,int M , int R){
        int[] helper = new int[R - L + 1]; //
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R){
            helper[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++] ;
        }
        while (p1 <= M){
            helper[i++] = arr[p1 ++];
        }
        while (p2 <= R){
            helper[i++] = arr[p2 ++];
        }
        for (int j = 0; j < helper.length; j++) {
            arr[L ++ ] = helper[j];
        }

    }

    /**
     * 获得一个随机长度并且数字随机的数据
     * @param maxLength 数组的最大长度
     * @param max 随机数的最大值
     * @return
     */
    public static int[] generateRandomArray(int maxLength,int max){

        int[] array = new int[(int)((maxLength + 1) * Math.random())];
        for (int i = 0 ; i < array.length ; i++){
            array[i] = (int)((max + 1) * Math.random()) - (int)(max * Math.random());
        }
        return array;
    }

    /**
     * 小和问题
     * @return
     */
    public static int smallPlus(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return process(arr,0,arr.length - 1);
    }

    private static int process(int[] arr,int L,int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr,L,M) //
                +
                process(arr,M + 1,R)
                +
                mergePlus(arr,L,M,R);
    }

    /**
     * 利用归并排序的merge过程的比较
     * [3,1,2,4,7,3,5,6]
     * [1,2,3,4] [3,5,6,7]
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    private static int mergePlus(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        int ans = 0;
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R){
            ans += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1 ++] : arr[p2 ++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0 ; i < help.length ; i ++){
            arr[L++] = help[i];
        }
        return ans;
    }


    public static int smallPlusVerification(int[] arr){
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                ans += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        int times = 10000;
        int maxValue = 500;
        int maxLength = 100;

        boolean success = true;
        for (int i = 0 ; i < times ; i++){

            int[] ints = generateRandomArray(maxLength, maxValue);
            int[] copy = Arrays.copyOf(ints, ints.length);
            int ans1 = reverseCouple(ints);
            int ans2 = comparator(copy);
            if(ans1 != ans2){
                success = false;
                System.out.println("ans1:" + ans1 + ",ans2:"+ans2);
            }
            /*Arrays.sort(copy);
            mergeSortRecession(ints,0,ints.length - 1);

            for(int j = 0; j < copy.length ; j++){
                if(copy[j] != ints[j]){
                    success = false;
                    System.out.println("err1:"+Arrays.toString(copy));
                    System.out.println("err2:"+Arrays.toString(ints));
                    break;
                }

            }*/
            if(!success){
                break;
            }
        }

        System.out.println(success ? "success" : "fail");

    }


    /**
     * 逆序对
     * @param arr
     * @return
     */
    public static int reverseCouple(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return processReverseCouple(arr,0,arr.length - 1);
    }

    public static int processReverseCouple(int[] arr,int L,int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return processReverseCouple(arr,L,M)
                +
                processReverseCouple(arr,M + 1, R)
                +
                processReverseCoupleMerge(arr,L,M,R);
    }
    public static int processReverseCoupleMerge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        int ans = 0;
        int i = help.length - 1;
        int p1 = M;
        int p2 = R;
        while (p1 >= L && p2 > M){
            ans += arr[p1] > arr[p2] ? (p2 - M) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1 --] : arr[p2 --];
        }
        while (p1 >= L){
            help[i--] = arr[p1--];
        }
        while (p2 > M){
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length ; i++){
            arr[L+i] = help[i];
        }

        return ans;
    }

    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
