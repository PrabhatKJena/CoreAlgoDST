package edu.pk.algo.dynapro;

import edu.pk.PKUtil;

import java.util.Arrays;

/**
 * The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given
 * sequence such that all elements of the subsequence are sorted in increasing order.
 * For example, length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int arr[] = {10, 22, 9, 33, 21, 50, 41, 1, 2, 3};
        int lic = findLisDynamic(arr);
        System.out.println(lic);
    }

    /**
     * Finding Longest Common Sub sequence
     * @param arr
     * @return
     */
    public static int findLisDynamic(int[] arr) {
        int[] lis = new int[arr.length];
        for (int i = 0; i < lis.length; i++)
            lis[i] = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] <= lis[j]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        PKUtil.print(arr);
        PKUtil.print(lis);

        int max = Arrays.stream(lis).max().getAsInt();
        printIncreasingSubSequence(lis, arr, max);
        return max;
    }

    /**
     * Printing in Reverse Order by the decreasing order of lis values
     * @param lis
     * @param arr
     * @param max
     */
    private static void printIncreasingSubSequence(int[] lis, int[] arr, int max) {
        for (int i = lis.length - 1; i >= 0; i--) {
            if (lis[i] == max) {
                System.out.print(arr[i] + "  ");
                int lastInc = i;
                for (int j = i - 1; j >= 0; j--) {
                    if (lis[j] + 1 == lis[lastInc]) {
                        System.out.print(arr[j] + "  ");
                        lastInc = j;
                    }
                }
                System.out.println();
                break;
            }
        }
    }
}
