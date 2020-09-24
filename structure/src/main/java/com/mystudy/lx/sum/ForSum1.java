package com.mystudy.lx.sum;

/**
 * @author dalaoban
 * @create 2020-06-04-23:14
 */

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ForSum1 {
//
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        Set<List<Integer>> sets=new HashSet<>(1);
//
//        for(int i=0;i<=nums.length-4;i++){
//            for(int j=i+1;j<=nums.length-3;j++){
//                for(int k=j+1;k<=nums.length-2;k++){
//                    for(int m=k+1;m<nums.length;m++){
//                        Integer n=nums[i]+nums[j]+nums[k]+nums[m];
//                        if(n.equals(target)){
//                            List<Integer> list=new ArrayList<>();
//                            list.add(nums[i]);
//                            list.add(nums[j]);
//                            list.add(nums[k]);
//                            list.add(nums[m]);
//                            list.sort(new Comparator<Integer>() {
//                                @Override
//                                public int compare(Integer o1, Integer o2) {
//                                    return o1.compareTo(o2);
//                                }
//                            });
//                            sets.add(list);
//                        }
//                    }
//                }
//            }
//        }
//        List<List<Integer>> lists = new ArrayList<>(sets);
//        return lists;
//    }

}

class ForSum2{
    public static void main(String[] args) {
        int test = test(17);
        System.out.println(test);

        int i = Integer.highestOneBit(17);
        System.out.println(i);
    }

    public static int test(int cap){
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        HashMap<Object, Object> map = new HashMap<>(17);
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;


    }
}
