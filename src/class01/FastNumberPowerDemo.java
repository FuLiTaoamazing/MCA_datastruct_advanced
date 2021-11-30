package class01;

/**
 * @ClassName: FastNumberPowerDemo.java
 * @author: FLT
 * @description:常数的快速计算N次幂
 * @createTime: 2021年10月13日 21:28:00
 */
public class FastNumberPowerDemo {
    public static void main(String[] args) {
        System.out.println(fastNumberPower(2,3));
    }

    //number:要求n次幂的数
    //n:要求几次
    public static int fastNumberPower(int number,int n){
        //主要思想是 number 的n次乘 把n转换成二进制就能实现log2(n)的时间复杂度
        //res=1的时候代表0项的时候
        int res=1;
        //准备一个temp记录他的一次幂
        int temp=number;
        int p=n;
        //每计算过一次幂就右移1位置
        for (; p != 0; p >>= 1) {
            //当最右边的为1的时候说明这个时候需要把temp*进去res中
            if ((p & 1) == 1) {
                res*=temp;
            }
            //不然temp就自己相乘
            temp*=temp;
        }

        return res;
    }
}
