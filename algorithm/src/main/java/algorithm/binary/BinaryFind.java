package algorithm.binary;

import algorithm.util.PrintHelper;

/**
 * 位运算 ^ 可以成无进位相加
 */
public class BinaryFind {

    /**
     * 一个乱序的数组,其中有一个数出现了奇数次,其他但数值出现的次数都是偶数次
     * @param data
     * @return
     */
    public static int findOnlyOne(int[] data){
        int target = 0;
        for (int datum : data) {
            target ^= datum;
        }
        return target;
    }

    /**
     * 一个数组中有两个数出现了奇数次
     * 其他的数字都出现了偶数次
     * @param data
     */
    public static void findOnlyTwo(int[] data){

        int lastRightOne = 0;//最右侧的1
        int err = 0;
        for (int datum : data) {
            err ^= datum;
        }
        lastRightOne = err & ((~err) + 1);
        int find = 0;
        for (int datum : data) {
            if((datum & lastRightOne) == 0){
                find ^= datum;
            }
        }
        System.out.println("一个数是:"+find);
        System.out.println("另一个数是:"+ (err ^ find));
    }

    /**
     * 获得一个数最左侧的1
     * 在计算机中 所有的数都是以二进制存在的,比如数字12在int中的二进制表示
     * 000...1100
     * 而获得一个数的最右边的1的数,则是 原来的二进制 & 原来的二进制取反 + 1
     *
     * 比如:12
     * 12  = 000...1100
     * &
     * ~12 + 1 = 111...0011 + 1
     *         = 111...0100
     *         = 111...0100
     *         &
     *      12 = 000...1100
     *     结果 = 000...0100   最后就把最右的1取出了
     *     ps:在计算机中一个数的负数等于该数值取反+1;eg:12 -> ~12 + 1 = -12;
     * @param i
     * @return
     */
    public static int findLastRightOne(int i){
        return i & (-i);
    }


    public static void main(String[] args) {

        int i = 12;
        PrintHelper.printBinary(i);
        int lastRightOne = findLastRightOne(i);
        System.out.println();
        PrintHelper.printBinary(lastRightOne);
    }






}
