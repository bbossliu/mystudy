package com.mystudy.lx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dalaoban
 * @create 2020-07-01-23:36
 */
public class EightQueens {
    private static  volatile int sum = 0;

    /**
     * 1、定义数组--1维？2维  --每个元素只需要存放所在列数即可 1维
     * 2、确定该位置是否可以放，可以则直接存放
     * 3、不能则继续向下寻找，直到列数到底，--开始回溯（行数到底或列数到底）
     * 4、比较是否能放---当前棋子所在的行数 列数都是已知的
     * 5、怎么判断对角线上存在元素 当前元素的行与之前元素的行之差的绝对值等于当前元素的列与之前元素列之差相等
     */

    /**
     *
     * @param que 之前存放元素的位置 （所在列数）
     * @param row   当前所要存放元素的行
     * @param size 方格为几方几列
     */
    public static void eightQueens(int que[],int row,int size){
        //开始回溯,说明一种情况以及走完了 , sum++
        if(row>=size){
            for (int i = 0; i < que.length; i++) {
                System.out.print(que[i]+",");
            }
            System.out.println();
            sum++;
            return;
        }
        for(int i=0;i<size;i++){
            int p = 0;
            for(int j=0;j<row;j++){
                //判断当前元素所存放的列以及对角线之前元素是否有存放过,有则打断
                if(que[j]==i || Math.abs(row-j)==Math.abs(i-que[j]) ){
                    p=1;
                    break;
                }
            }
            //将当前元素存放的位置存入que数组中  全部循环完且没有元素与他冲突
            if(p==0){
                que[row]=i;
                eightQueens(que,row+1,size);
            }
        }
        //当前行每一列都无法存放，应该回溯
        return;

    }

    public static void main(String[] args) {
        int que[] = new int[8];
        for(int i=0;i<que.length;i++){
            que[i] = -1;
        }

        eightQueens(que,0,que.length);
        System.out.println(sum);
    }
}

//class Test{
//    public List<List<String>> solveNQueens(int n) {
//        List<List<String>> ans = new ArrayList<>();
//        char[][] nums = new char[n][n];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(nums[i], '.');
//        }
//        eightQueens(nums,0, ans,n);
//        return ans;
//    }
//
//    private void eightQueens(char[][] nums, int row, List<List<String>> ans, int size) {
//        //开始回溯,说明一种情况以及走完了 , sum++
//        if(row>=size){
//            List<String> tempList = new ArrayList<>();
//            for (int i = 0; i < nums.length; i++) {
//                tempList.add(String.valueOf(nums[i]));
//                ans.add(tempList);
//            }
//            return;
//        }
//        for(int i=0;i<size;i++){
//            int p = 0;
//            for(int j=0;j<row;j++){
//                //判断当前元素所存放的列以及对角线之前元素是否有存放过,有则打断
//                if(que[j]==i || Math.abs(row-j)==Math.abs(i-que[j]) ){
//                    p=1;
//                    break;
//                }
//            }
//            //将当前元素存放的位置存入que数组中  全部循环完且没有元素与他冲突
//            if(p==0){
//                que[row]=i;
//                eightQueens(que,row+1,size);
//            }
//        }
//        //当前行每一列都无法存放，应该回溯
//        return;
//    }
////
////    /**
////     * 1、定义数组--1维？2维  --每个元素只需要存放所在列数即可 1维
////     * 2、确定该位置是否可以放，可以则直接存放
////     * 3、不能则继续向下寻找，直到列数到底，--开始回溯（行数到底或列数到底）
////     * 4、比较是否能放---当前棋子所在的行数 列数都是已知的
////     * 5、怎么判断对角线上存在元素 当前元素的行与之前元素的行之差的绝对值等于当前元素的列与之前元素列之差相等
////     */
////
////    /**
////     *
////     * @param que 之前存放元素的位置 （所在列数）
////     * @param row   当前所要存放元素的行
////     * @param size 方格为几方几列
////     */
////    public static void eightQueens(int que[],int row,int size){
////        //开始回溯,说明一种情况以及走完了 , sum++
////        if(row>=size){
////            for (int i = 0; i < que.length; i++) {
////                System.out.print(que[i]+",");
////            }
////            System.out.println();
////            sum++;
////            return;
////        }
////        for(int i=0;i<size;i++){
////            int p = 0;
////            for(int j=0;j<row;j++){
////                //判断当前元素所存放的列以及对角线之前元素是否有存放过,有则打断
////                if(que[j]==i || Math.abs(row-j)==Math.abs(i-que[j]) ){
////                    p=1;
////                    break;
////                }
////            }
////            //将当前元素存放的位置存入que数组中  全部循环完且没有元素与他冲突
////            if(p==0){
////                que[row]=i;
////                eightQueens(que,row+1,size);
////            }
////        }
////        //当前行每一列都无法存放，应该回溯
////        return;
////
////    }
//}

