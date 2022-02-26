package algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {


    public static void partition(int[] arr,int part){
        if(arr == null || arr.length < 2){
            return;
        }
        int partIndex = 0;
        for (int i = 0 ; i < arr.length ; i++){
            if(arr[i] <= part){
                int tmp = arr[partIndex];
                arr[partIndex++] = arr[i];
                arr[i] = tmp;
            }
        }
    }


    public static void quickSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length - 1);
    }

    public static void process(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        int[] partition = partition(arr, L, R);
        process(arr,L,partition[0] - 1);
        process(arr,partition[1] + 1,R);
    }

    public static int[] partition(int[] arr,int L,int R){
        if(L == R){
            return new int[]{L,R};
        }
        if(L > R){
            return new int[]{-1,-1};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more){
            if(arr[R] < arr[index]){
                swap(arr,index,--more);
            }else if(arr[R] > arr[index]){
                swap(arr,index++,++less);
            }else {
                index++;
            }
        }
        swap(arr,more,R);
        return new int[]{less + 1,more};
    }

    private static void swap(int arr[],int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        int i = 0 - 1;

        System.out.println( -1 / 2);

    }


}
