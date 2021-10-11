package class01;

import java.util.LinkedList;

/**
 * @ClassName: SlidingSubArrValid.java
 * @author: FLT
 * @description:子数组达标的问题
 * @createTime: 2021年10月08日 21:43:00
 */
public class SlidingSubArrValid {
    public static void main(String[] args) {

    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: arr,所提供的的数组
     * @param: num;达标的要求
     * @Return: int
     * @Date: 2021/10/8 21:43
     **/
    public static int subArrValid(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //最大值的记录结构体
        LinkedList<Integer> qMax = new LinkedList<>();
        //最小值的记录结构体
        LinkedList<Integer> qMin = new LinkedList<>();
        //窗口的两个边界
        int L = 0;
        int R = 0;
        int res = 0;
        while (L < arr.length) {        //从0开始尝试所有的可能性
            while (R < arr.length) { //R是最后一个下标达标的位置的往后一个位置
                //记录最大值以及最小值的操作
                while (!qMin.isEmpty() && qMin.peekLast() >= arr[R]) {
                    qMin.pollLast();
                }
                qMin.add(R);
                while (!qMax.isEmpty() && qMax.peekLast() <= arr[R]) {
                    qMax.pollLast();
                }
                qMax.add(R);
                //当发现R来到的位置已经不满足了 就直接跳出当前循环
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //L...R-1位置上是满足num的要求的，求出有多少个个数
            res += R  - L;
            //L要往右走一个所以要过期这两个记录里面的东西
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            if (qMin.peekFirst() == L) {
                qMin.pollFirst();
            }
            L++;
        }
        return res;
    }




}
