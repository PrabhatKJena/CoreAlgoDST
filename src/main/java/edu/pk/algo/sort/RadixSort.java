package edu.pk.algo.sort;

import edu.pk.PKUtil;

import java.util.Arrays;
import java.util.OptionalInt;

public class RadixSort {
    public static void main(String[] args) {
        int a[] = {1300, 1103, 114, 504, 78, 239, 11, 99, 760};
        radixSort(a);
    }

    public static void radixSort(int[] a) {
        int len = a.length;
        // Use any util to get max number to find maximum no of digits
        OptionalInt max = Arrays.stream(a).max();
        if (max.isPresent()) {
            int maxNo = max.getAsInt();

            // Repeatedly Sort by 1st digit to nth digit
            int nthDigit = 1;
            while (maxNo > 0) { // iterate for the number of digits of the max number
                countingSort(a, nthDigit);
                nthDigit++;
                maxNo /= 10;
                PKUtil.print(a);
            }
        }
    }

    /**
     * Modified CountingSort :
     * 1. Sort based on the nth digit (not the whole number)
     * 2. Take number in reverse order of Counting Sort (i.e from right to left) of th input array
     *    and put into output array based on the position by calculating accumulative count
     *    Reason : After each round of sorting, if two or more numbers having same digit in nth place,
     *    the earlier number is smaller than the followed number. So pick from rigth to left, so that
     *    the greater number will be at the larger index(position) in the output array
     *
     * What is the running time of Radix Sort?
     * Let there be d digits in input integers. Radix Sort takes O(d*(n+b)) time where b is the base for representing numbers,
     * for example, for decimal system, b is 10. What is the value of d? If k is the maximum possible value, 
     * then d would be O(logb(k)). So overall time complexity is O((n+b) * logb(k)). Which looks more than the time complexity 
     * of comparison based sorting algorithms for a large k. Let us first limit k. Let k <= nc where c is a constant. 
     * In that case, the complexity becomes O(nLogb(n)). But it still doesn’t beat comparison based sorting algorithms.
     */
    private static void countingSort(int a[], int nthDigit) {
        int len = a.length;
        int exp = (int) Math.pow(10, (nthDigit - 1));
        int count[] = new int[10]; // Counting based on the nthDigit, so range of digit is 10 [0-9]
        for (int i = 0; i < len; i++) {
            count[(a[i] / exp) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        int[] output = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int index = (a[i] / exp) % 10;
            output[count[index] - 1] = a[i];
            count[index]--;
        }
        for (int i = 0; i < len; i++) {
            a[i] = output[i];
        }
    }
}
