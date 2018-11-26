package com.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 剑指Offer第一题  2018.11.26
 * 判断一个整型数组中是否有重复出现的元素
 * 方案1：排序+遍历  时间复杂度O(nlog(n))
 * 方案2:HashSet 遍历并add,当put失败时即出现重复  时间复杂度O(n) 空间复杂度O(n)
 * 方案3:遍历数组,比较当前元素与下标值是否相等;若不,比较当前元素与数组中下标为当前元素值处的元素,
 * 若相等则出现重复,若不相等,则交换两元素位置，继续比较.  时间复杂度O(n)  空间复杂度O(1)
 */
public class FindRepeatedNumber {
    public static void main(String[] args) {
        int[] array = new int[100];
        System.out.print("请输入数组中的元素个数");
        Scanner scann = new Scanner(System.in);
        int n = scann.nextInt();
        for (int i = 0; i < n; i++) {
            array[i] = scann.nextInt();
        }

        System.out.println(duplicate(array,n));

    }

    /**
     * 通过方案3实现
     * @param array
     * @param length
     * @return
     */
    public static boolean duplicate(int[] array,int length) {

        if (array == null || length <= 0) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if (array[i] < 0 || array[i] > length - 1) {
                return false;
            }
        }

        for (int i = 0; i < length; i++) {
            while (array[i] != i) {
                if (array[i] == array[array[i]]) {
                    return true;
                }
                int temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }
        return false;
    }

    /**\
     * 重复出现的
     * @param array
     * @param length
     * @return
     */
    public static int getDuplication(int[] array,int length) {
        if (array == null || length <= 0) {
            return -1;
        }
        int start = 1;
        int end = length - 1;
        while (end >= start) {
            //位运算求中位值
            int middle = ((end - start) >> 1) + start;
            int count = countRange(array,length,start,middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }

            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    public static int countRange(int[] array, int length, int start, int end) {
        if (array == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] >= start && array[i] <= end) {
                count++;
            }
        }
        return count;
    }
}
