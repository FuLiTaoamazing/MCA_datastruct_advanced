package class01;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.*;

/**
 * @ClassName: MonontouosStack.java
 * @author: FLT
 * @description:单调栈题目
 * @createTime: 2021年10月11日 22:35:00
 */
public class MonotonousStack {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 3, 2, 1, 5};
        System.out.println(Arrays.deepToString(getNearLessRepeat(arr)));
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: arr;目标数组
     * @Return: int[][]   第一个数组代表的是目标数组的下标，第二数组0位置代表左边的位置 1位置代表右边的位置
     * @Date: 2021/10/11 22:37
     **/
    public static int[][] getNearLessNoRepeat(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[][] answear = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            //当栈中为空 或者栈中的栈顶元素小于当前元素就直接入栈
            if (stack.isEmpty() || arr[stack.peek()] < arr[i]) {
                //栈中存的是下标值
                stack.add(i);
                continue;
            }
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer releaseIndex = stack.pop();
                answear[releaseIndex][1] = i;
                //看看当前要释放的位置下一个位置是否有元素
                int stackNext = stack.isEmpty() ? -1 : stack.peek();
                answear[releaseIndex][0] = stackNext;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            int releaseIndex = stack.pop();
            int stackNext = stack.isEmpty() ? -1 : stack.peek();
            answear[releaseIndex][1] = -1;
            answear[releaseIndex][0] = stackNext;
        }
        return answear;
    }

    public static int[][] getNearLessRepeat(int[] arr) {
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
