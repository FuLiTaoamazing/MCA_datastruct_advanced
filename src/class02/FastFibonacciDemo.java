package class02;

/**
 * @ClassName: FastFibonacciDemo.java
 * @author: FLT
 * @description:斐波那契的求法
 * @createTime: 2021年10月13日 21:47:00
 */
public class FastFibonacciDemo {
    public static void main(String[] args) {
        System.out.println(f1(3));
    }
    //通过矩阵快速幂求斐波那契额数列
    public static int f1(int n){
        if(n<0){
            return 0;
        }
        if(n==1||n==2){
            return 1;
        }
        //这个就是表达式中|Fn,Fn-2|=|1,1|* matrix^n-2的哪个矩阵
        int[][] baseMatrix={
                {1,1},
                {1,0}
            };

        int[][] res=FastMatrixPowerDemo.fastMatrixPower(baseMatrix,n-2);
        //因为表达式中|Fn,Fn-1|=|F2,F1|*matrix^n-2 因为F2,F1=1所以可以知道 Fn就是最左边的列的和
        return res[0][0]+res[1][0];
    }




}
