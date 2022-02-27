package algorithm.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给出一个二维数组,数组里面的都整数的线段,
 * 并且[start,end] start <= end
 * 求某一部分线段重合最多次数的线段
 */
public class CoverMax {

    public static int max(int[][] arr){
        //[177, 178]
        //[2, 35]
        //线段应该先经过一次排序,如果没有经过排序,以上面的例子为例
        //177，178 先放到 小根堆里面 此时 178在里面
        //接下来2,35进来了
        //此时 178 <= 2不成立,所以 35放了进去
        //此时有 35,178 这样的情况存在  这种情况是表示 2 ~ 178的线段
        //但实际上 是 2 ～ 35 线段 此时 2并没有穿过 36 ～ 178 这段
        //如果先排序一边,由小到大排序,则可以避免这种情况
        //Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        Queue<Integer> queue = new PriorityQueue<>();
        int max = 0;
        for (int[] ints : arr) {
            while (!queue.isEmpty() && queue.peek() <= ints[0]){
                queue.poll();
            }
            queue.add(ints[1]);
            if(max < queue.size()){
                max = queue.size();
            }
        }
        return max;
    }
    public static int max2(int[][] arr){
        Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
        return max(arr);
    }
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = {{177,178},{2,35}};
            int ans1 = maxCover1(lines);
            int ans3 = max(lines);
            int ans2 = max2(lines);
            if (ans1 != ans3) {
                for (int[] line : lines) {
                    System.out.println(Arrays.toString(line));
                }
                System.out.println("Oops!");
                return;
            }
        }
        System.out.println("test end");

    }
}
