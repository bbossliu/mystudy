package com.mystudy.lx.sort.bucket;

/**
 * @author dalaoban
 * @create 2020-05-24-10:18
 * 桶排序简单版排序
 */
public class MyBucketSortTest01 {

    /**
     * 简化版桶排序实现思路
     * 1、准备一个装载排序数的桶（即一个int[]，长度为排序数中最大的数+1）
     * 2、初始化排序桶（桶的下标代表排序数、桶中内容代表该数出现的次数、初始化为0）
     * 3、循环读取排序数将对应的数组下标元素每读到一次则+1
     * 4、循环读取排序桶、桶中的数目则代表下标元素打印的次数
     *
     * 具体需要写哪些方法:
     * 1、寻找排序数中最大值
     * 2、初始化数组的方法
     * 3、对待排序数组进行排序的方法
     * 4、打印按指定排序规定排好序的内容
     * @param args
     */

    public static void main(String[] args) {
        int[] sortNum={5,5,1,4,3};
        int maxNum = lookFormaxNum(sortNum);
        int[] sortBucket = initSortBucket(maxNum);
        sortArray(sortNum,sortBucket);
        printSortArray(sortBucket,true);
        System.out.println();
        printSortArray(sortBucket,false);

    }


    /**
     * 寻找待排序数组中的最大值
     * @param sortNum
     * @return
     */
    public  static int lookFormaxNum(int[] sortNum){
        //假设第一个元素为最大值
        int maxNum=sortNum[0];
        for(int i=1;i<sortNum.length;i++){
            if(sortNum[i]>maxNum){
                maxNum=sortNum[i];
            }
        }
        return maxNum;
    }

    /**
     * 返回初始化好排序桶，全部初始化为0
     * @return
     */
    public static int[] initSortBucket(int maxNum){
        int[] sortBucket=new int[maxNum+1];
        return sortBucket;
    }

    /**
     * 对待排序数组进行排序的方法
     * 按循序对排序桶中下标为该元素的排序桶中的内容+1
     * @param sortNum
     * @param sortBucket
     */
    public static void sortArray(int[] sortNum, int[] sortBucket){

        //1、循环带排序素组将对应下标为该元素的内容+1
        for(int i=0;i<sortNum.length;i++){
            sortBucket[sortNum[i]]++;
        }
    }

    /**
     * 按指定规则进行打印
     * @param sortBucket 排序桶
     * @param isAsc 代表是否升序排列
     */
    public static void printSortArray(int[] sortBucket, boolean isAsc ){
        //降序排列
        if(!isAsc){
            System.out.print("降序排列为："+'\t');
            /*
            * 从排序桶数组下标最大的位置开始读取
            * sortBucket[i]代表下标为i的元素出现了几次（即出现几次则打印几次）
            * */
            for (int i=sortBucket.length-1;i>0;i--){
                //sortBucket[i]代表下标为i的元素出现了几次（即出现几次则打印几次）
                for(int j=0;j<sortBucket[i];j++){
                    System.out.print(i+"\t");
                }
            }
        }else {
            System.out.print("升序排列为："+'\t');
            for (int i=1;i<sortBucket.length;i++){
                //sortBucket[i]代表下标为i的元素出现了几次（即出现几次则打印几次）
                for(int j=0;j<sortBucket[i];j++){
                    System.out.print(i+"\t");
                }
            }
        }
    }
}


