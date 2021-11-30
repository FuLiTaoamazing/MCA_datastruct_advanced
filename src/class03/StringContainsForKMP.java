package class03;

import java.util.Arrays;

/**
 * @ClassName: StringContainsForBFP.java
 * @author: FLT
 * @description: 通过BFP算法实现String类中的indexOf算法
 * @createTime: 2021年11月23日 21:06:00
 */
public class StringContainsForKMP {
    public static void main(String[] args) {
        String str = "aabaacddeefa";
        String match = "ddeefa";
        System.out.println(getIndexOf(str, match));
    }

    public static int getIndexOf(String s, String m) {
        if (m == null || s == null || m.length() == 0 || s.length() == 0 || m.length() > s.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNextArr(match);
        while (x < str.length && y < str.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }

        return y == match.length ? x - y : -1;
    }

    public static int[] getNextArr(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        //这里的cn代表的是next下存的最长前缀后缀匹配长度信息，并且是要和i-1比较字符的下标
        // 初始化为0因为i是从2开始  我们知道 下标为0的时候 next数组存的是-1
        //下标为1的时候数组存的是0
        //从 2开始 即 i-1的信息就是0
        int cn = 0;
        int i = 2;
        while (i < match.length) {
            if (match[cn] == match[i - 1]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
