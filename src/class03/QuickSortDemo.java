package class03;

import java.util.Arrays;

/**
 * @ClassName: QuickSortDemo.java
 * @author: FLT
 * @description:快速排序
 * @createTime: 2021年11月17日 19:41:00
 */
public class QuickSortDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 0};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l > r) {
            return;
        }
        //随机抽取某个index下的数作为基值
        int randomOffset = (int) (Math.random() * (r-l + 1));

        swap(arr, l + randomOffset, r);
        int[] coords = partition(arr, l, r);
        process(arr, l, coords[0] - 1);
        process(arr, coords[1] + 1, r);
    }

    //这里的return是返回的是左边界和右边界
    public static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int index = l;
        int lees = l - 1;
        int more = r;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++lees);
            } else {
                swap(arr, index, --more);
            }
        }
        //把当前指向比arr[r]位置大和r位置进行交换
        swap(arr, more, r);
        return new int[]{lees + 1, more};
    }

    //交换数组中 l 与 r 位置上的元素
    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
