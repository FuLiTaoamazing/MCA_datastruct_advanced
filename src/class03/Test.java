package class03;

import java.util.Arrays;

/**
 * @ClassName: Test.java
 * @author: FLT
 * @description:
 * @createTime: 2021年11月29日 19:52:00
 */
public class Test {
    public static void main(String[] args) {
        int[] arr={5,4,3,2,1};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l > r) {
            return;
        }
        int randomOffset = (int) ((r-l + 1) * Math.random());
        //随机快拍 O(logN)的主要就是这个代码
        swap(arr, l + randomOffset, r);
        int[] coords = partition(arr, l, r);
        process(arr, l, coords[0] - 1);
        process(arr, coords[1] + 1, r);
    }

    public static int[] partition(int[] arr, int l, int r) {

        if (l == r) {
            return new int[]{l, r};
        }
        int index = l;
        //小于arr[r]的下标起始位置`
        int less = l - 1;
        int more = r;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, r);
        //这里返回的就是等于区的便捷
        return new int[]{less+1, more};
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
