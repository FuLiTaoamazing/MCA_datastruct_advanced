package class03;

import com.sun.corba.se.impl.logging.OMGSystemException;

/**
 * @ClassName: BFPRTDemo.java
 * @author: FLT
 * @description:利用BFPRT算法解决第K小的数的问题
 * @createTime: 2021年11月29日 20:24:00
 */
public class BFPRTDemo {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        System.out.println(normalSolutionMethod(arr, 5));
        System.out.println(topKSolutionForBFPRT(arr, 5));
    }

    //常规解法求 TOP-K值
    public static int normalSolutionMethod(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }
        return process(arr, 0, arr.length - 1, k - 1);
    }

    public static int process(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        //随机选取一个数来作为样本比较
        int pivot = arr[(int) ((r - l) * Math.random())];
        //进行荷兰国旗问题 找到等于区的上边界下边界
        int[] range = partition(arr, l, r, pivot);
        //这里的range 和index比较包含了一些隐藏的说明
        //假如index是在有序数组中 他和在这个边界之中就说明arr[index]就等于这个边界的数
        //比他小的话就考虑他左边
        //比他大就考虑他的右边
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, l, range[0] - 1, index);
        } else {
            return process(arr, range[0] + 1, r, index);
        }
    }

    public static int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l;

        while (cur < more) {
            if (arr[cur] == pivot) {
                cur++;
            } else if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else {
                swap(arr, cur++, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    //通过 BFPRT来解决TOP-K的方法
    public static int topKSolutionForBFPRT(int[] arr, int k) {
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    //假设排序的话 位于arr[index]上的数是什么
    public static int bfprt(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        int pivot = medianOfMedians(arr, l, r);
        int[] range = partition(arr, l, r, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
           return bfprt(arr, l, range[0] - 1, index);
        } else {
            return  bfprt(arr, range[1] + 1, r, index);
        }

    }

    //在BFPT算法中 通过尽量挑选在数组排序中最位于中间位置的数 来实现O(N)的复杂度
    //这个方法就是完成 把一个数组五个一组分组 排序在把他们的中位数单独拎出来形成一个新的数组
    //返回这个新生成的数组中的中位数
    public static int medianOfMedians(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        //新生成的中位数数组 的长度是原数组的 size/5+offset 因为最后一组可能不达到5个
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = team * 5;
            //为啥右边界得靠Math.min来选择呢 因为可能最后一组不到五个
            mArr[team] = getMedian(arr, teamFirst, Math.min(teamFirst + 4, r));
        }

        //找到新数组的中位数
        return bfprt(mArr, 0, mArr.length-1, mArr.length / 2);
    }

    public static int getMedian(int[] arr, int l, int r) {
        insertSort(arr, l, r);
        return arr[r + l / 2];
    }

    //为啥选择 选择排序来进行呢 因为这个排序算法的常数项最低
    public static void insertSort(int[] arr, int l, int r) {
        //选择排序的思想是  0~1上有序 0~i上有序
        //因为0~0上只有一个数就天然有序
        for (int i = l + 1; i <= r; i++) {
            for (int j = i - 1; j >= l && arr[j] > arr[j + 1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }


}
