package class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName: SubArrMaxNum.java
 * @author: FLT
 * @description:单调栈求子数组中 (sub累加和)*(sub中最小值) 的最大
 * @createTime: 2021年10月12日 22:08:00
 */
public class SubArrMaxNums {

    public static int subArrMaxNum(int[] arr) {
        int answer = 0;
        //先计算整个数组上的每个位置的累加和
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        //利用单调栈 求出一个坐标上的左边第一个比他小的值 右边第一个比他小的值
        int[][] limit = getNearLess(arr);

        //遍历这个数组求出最大的子数组 (sub累加和)*(sub中最小值)
        for (int i = 0; i < arr.length; i++) {
            int leftLimit = limit[i][0] == -1 ? 1 : limit[i][0] + 1;
            int rightLimit = limit[i][1] == -1 ? arr.length - 1 : limit[i][1] - 1;
            answer = Math.max(sum[rightLimit] - sum[leftLimit - 1] * arr[i], answer);
        }


        return answer;
    }

    //求出左边第一个比他小的坐标 和右边第一个比他小的坐标
    public static int[][] getNearLess(int[] arr) {
        int[][] answer = new int[arr.length][2];
        //单调栈
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popList = stack.pop();
                //判断当前弹出的元素往下是否还有元素 没有就返回-1 如果有取那个list的最后一个坐标
                int lessLeftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer pop : popList) {
                    answer[pop][1] = i;
                    answer[pop][0] = lessLeftIndex;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int lessLeftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer pop : popList) {
                answer[pop][1] = -1;
                answer[pop][0] = lessLeftIndex;
            }
        }
        return answer;
    }
}
