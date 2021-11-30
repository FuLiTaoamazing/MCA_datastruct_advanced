package class03;

/**
 * @ClassName: StringContainsDemo.java
 * @author: FLT
 * @description:判断一个字符串是否存在另外个字符串中 返回最先出现的位置
 * @createTime: 2021年11月17日 21:03:00
 */
public class StringContainsDemo {
    public static void main(String[] args) {
        String str = "babcd";
        String match = "abc";
        System.out.println(process(str, match));
    }


    //暴力解法

    public static int process(String str, String match) {
        if (str.length() == 0) {
            return -1;
        }
        if (match.length() == 0) {
            return -1;
        }
        int i = 0;
        int strLen = str.length();
        int matchLen = match.length();
        while (true) {
            if (i > strLen-1) {
                return -1;
            }
            int index = i;
            for (int j = 0; j < matchLen; j++) {
                if (str.charAt(index) != match.charAt(j)) {
                    i++;
                    continue;
                }
                index++;
            }

            return i;
        }
    }

}
