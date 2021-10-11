package class01;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName: SlidingWindowMaxArray.java
 * @author: FLT
 * @description:窗口最大值的问题
 * @createTime: 2021年10月08日 20:54:00
 */
public class SlidingWindowMaxArray {
    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 7, 9, 1, 2};
        System.out.println(Arrays.toString(getMaxWindow(arr, 3)));
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: arr,要划过的数组
     * @param: w;窗口的大小
     * @Return: int[]
     * @Date: 2021/10/8 20:55
     **/
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        //LinkedList底层就是双端队列实现的 在这里放的是数组的下标
        LinkedList<Integer> qMax = new LinkedList<>();
        //这里代表结果的个数 一个窗口的大小和数组的大小就决定了结果的长度
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int r = 0; r < arr.length; r++) {
            //把当前窗口状态的最大值处理一下
            //当双端队列不为空而且双端队列的尾巴的值小于等于当前窗口R要入队的值的时候就弹出
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[r]) {
                qMax.pollLast();
            }
            //从尾部入队 这里记录的是他的下标值
            qMax.add(r);
            //这个语句代表 窗口的左边已经划出这个队列头部的值就过期掉
            if (qMax.peekFirst() == r - w) {
                qMax.pollFirst();
            }
            //当R滑动到了大于等于窗口宽度的时候就记录当前的最大值情况
            if (r >= w - 1) {
                res[index++] = arr[qMax.peekFirst()];
            }

        }

        return res;
    }

}
