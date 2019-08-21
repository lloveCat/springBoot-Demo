package com.lhh.demo.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static int src[][] = {{150,110,120,60},{70,160,10,160},{170,150,10,90},{180,160,80,200},{180,20,190,80}};
    public static int test[][] = {{135, 127, 137, 72},{63, 153, 9, 192},{171, 164, 9, 96},{216, 192, 72, 186},{216, 22, 171, 72}};
    public static int desSum1[] = {471, 417, 440, 666, 481};
    public static int desSum2[] = {801, 658, 398, 618};
    public static int upSrc[][] = new int[5][4];
    public static int lowSrc[][] = new int [5][4];
    public static int des[][] = new int[5][4];
    public static void main(String args[]) {
        for (int i = 0 ; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                upSrc[i][j] = (int)(src[i][j] * 1.2);
                des[i][j] = upSrc[i][j];
                lowSrc[i][j] = (int)(src[i][j] * 0.9);
            }
        }
        test();
        //[135, 120, 144, 72]
        //[63, 150, 12, 192]
        //[153, 167, 12, 108]
        //[186, 191, 93, 196]
        //[264, 30, 137, 50]
//        for (int i = 0; i < 5; i++) {
//            System.out.println("每一项初始值：" + Arrays.toString(src[i]));
//            System.out.println("每一项最大值：" + Arrays.toString(upSrc[i]));
//            System.out.println("每一项最小值：" + Arrays.toString(lowSrc[i]));
//        }
//        ddd(0);
//        System.out.println("ddd exit();");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(Arrays.toString(des[i]));
//        }
    }
    public static void ddd(int num) {      //索引num
        if (num == 16) {
            for (int i = 0; i < 4; i++) {
                des[4][i] = desSum2[i] -des[0][i] - des[1][i] - des[2][i] - des[3][i];
            }
            if (des[4][0] + des[4][1] +des[4][2] + des[4][3] != desSum1[4]) return;
            for (int i = 0; i < 4; i++) {
                if (des[4][i] > upSrc[4][i] || des[4][i] < lowSrc[4][i]) return;
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Arrays.toString(des[i]));
            }
            return;
        }
        int nn = num / 4;
        int mm = num % 4;
        int lowNum = lowSrc[nn][mm];  //索引最小值
        int upNum = upSrc[nn][mm];    //索引最大值
        for (int n=lowNum; n<=upNum; n++) {
            des[nn][mm] = n;
            int sumMax = 0;     //用于计算每一列的当前最大和
            int sumMin = 0;     //用于计算每一列的当前最小和
            for (int i = 0; i <= nn; i++) {     //当前该列中已分配的数据
                sumMax += des[i][mm];
            }
            sumMin = sumMax;
            for (int i = nn + 1; i < 5; i++) {  //当前该列中未分配的数据，取最大最小值计算。这里防止之前修改过后面的值
                sumMin += lowSrc[i][mm];
                sumMax += upSrc[i][mm];
            }
            if (sumMax < desSum2[mm]) continue;        //当前的值取得的最大和小于预期值，继续往上加
            if (sumMin > desSum2[mm]) break;           //当前的值取得的最小和大于预期值，不需要往上加了，放弃继续加
            if (num % 4 == 2) {                         //通过每一行的前三个数计算出第四个数
                des[nn][3] = desSum1[nn] - des[nn][0] - des[nn][1] - des[nn][2];
                if (des[nn][3] > upSrc[nn][3]) continue;    //第四个数大于它的上限，则当前值往上加，第四个数则往下降
                else if (des[nn][3] < lowSrc[nn][3]) break; //第四个数小于它的上限，则当前值往上加它只会越小，所以放弃继续加
                ddd(num + 2);
            } else {
                ddd(num + 1);
            }
        }
    }
    public static boolean test() {
        boolean flag = true;
        for (int i = 0; i < 20; i++) {
            int x = i/4;
            int y = i%4;
            if (test[x][y] > upSrc[x][y] || test[x][y] < lowSrc[x][y]) {
                flag = false;
                System.out.println(i);
                break;
            }
        }
        System.out.println(flag);
        for (int i = 0; i < 4; i++) {
            System.out.println(test[0][i] + test[1][i] + test[2][i] + test[3][i] + test[4][i]);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(test[i][0] + test[i][1] + test[i][2] + test[i][3]);
        }
        return flag;
    }
}