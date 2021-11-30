package class02;

/**
 * @ClassName: FastMatrixPowerDemo.java
 * @author: FLT
 * @description:快速求矩阵幂的demo
 * @createTime: 2021年10月13日 21:35:00
 */
public class FastMatrixPowerDemo {
    //matrix:所求的矩阵;n要求的n次方
    public static int[][] fastMatrixPower(int[][] matrix, int n) {
        int[][] res = new int[matrix.length][matrix[0].length];
        //把res初始化为单位矩阵 对角线上为1其他地方都为0
        for (int i = 0; i < matrix.length; i++) {
            res[i][i] = 1;
        }
        int p = n;
        int[][] temp = matrix;
        for (; p != 0; p >>= 1) {
            if ((p & 1) == 1) {
                res = multipleMatrix(res, temp);
            }
            temp=multipleMatrix(temp,temp);
        }

        return res;
    }

    //计算两个矩阵的乘积
    public static int[][] multipleMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][j] * m2[k][j];
                }
            }
        }


        return res;
    }

}
