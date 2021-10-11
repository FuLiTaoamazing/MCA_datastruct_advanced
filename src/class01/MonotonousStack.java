package class01;

import java.util.*;

/**
 * @ClassName: MonontouosStack.java
 * @author: FLT
 * @description:单调栈题目
 * @createTime: 2021年10月11日 22:35:00
 */
public class MonotonousStack {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 1, 5};
        System.out.println(Arrays.deepToString(getNearLessNoRepeat(arr)));
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
        Stack<Integer> stack = new Stack<>();
        //这里就是压入的集合 用Map映射
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (stack.isEmpty() || arr[stack.peek()] <= arr[i]) {
                if (!map.containsKey(i)) {
                    TreeSet<Integer> list = new TreeSet<>();
                    list.add(i);
                    map.put(i, list);
                } else {
                    map.get(i).add(i);
                }
                stack.add(i);
                continue;
            }
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer releaseIndex = stack.pop();
                answer[releaseIndex][1] = i;
                int nextStack = stack.isEmpty() ? -1 : stack.peek();
                if (nextStack == -1) {
                    answer[releaseIndex][0] = -1;
                } else {
                    answer[releaseIndex][0] = -1;
                    TreeSet<Integer> list = map.get(nextStack);
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        Integer next = iterator.next();
                        answer[releaseIndex][0] = next;
                        iterator.remove();
                        continue;
                    }
                }
            }
            if (!map.containsKey(i)) {
                TreeSet<Integer> list = new TreeSet<>();
                list.add(i);
                map.put(i, list);
            } else {
                map.get(i).add(i);
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            Integer releaseIndex = stack.pop();
            if (map.containsKey(releaseIndex)) {
                TreeSet<Integer> list = map.get(releaseIndex);
                Iterator<Integer> iterator = list.iterator();
                while (iterator.hasNext()) {
                    Integer cur = iterator.next();
                    answer[cur][1] = -1;
                    iterator.remove();
                    if (!iterator.hasNext()) {
                        answer[cur][1] = -1;
                        continue;
                    }
                    int next = iterator.next();
                    answer[cur][0] = next;
                }
            }
        }
        return answer;
    }
}
