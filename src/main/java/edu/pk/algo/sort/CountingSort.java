package edu.pk.algo.sort;

import edu.pk.PKUtil;

public class CountingSort {
    public static void main(String[] args) {
        int a[] = {11, 12, 16, 13, -11, 18, 13, 12, 11, 14, -17}; //1 1 1 2 2 3 3 4 6 7 8
        countingSort(a, -17, 19);
        PKUtil.print(a);
    }

    /**
     * Modified version of Counting Sort which works with any specific range (low-high)
     * and also support -ve values.
     */
    public static void countingSort(int a[], int low, int high) {
        int len = a.length;
        int count[] = new int[high - low + 1];
        /**
         * 1. Find count of each item
         */
        for (int i = 0; i < len; i++) {
            count[a[i] - low]++;
        }
        /**
         * 2. Find accumulative count of each item to calculate the position of the items in output array
         */
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        /** 3. Take an item from input array, get the count from counter array.
         *     Here item-count will be the position of the last occurrence of the item in the input array.
         *  4. So put the item in (counter-1) position of the output array;
         *     And decrement the counter of the item in counter array, as the position of the next
         *     occurrence of the item will be the previous position of the output array
         */
        int[] output = new int[len];
        for (int i = 0; i < len; i++) {
            output[count[a[i] - low] - 1] = a[i];
            count[a[i] - low]--;
        }

        for (int i = 0; i < len; i++) {
            a[i] = output[i];
        }

    }

}
