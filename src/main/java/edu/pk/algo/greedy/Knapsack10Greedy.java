package edu.pk.algo.greedy;

import edu.pk.Util;

public class Knapsack10Greedy {
    public static void main(String[] args) {
        int wt[] = {2, 3, 4, 5};
        int val[] = {3, 4, 5, 8};
        int W = 5;
        int total = knapsackGreedy(val, wt, val.length, W);
        System.out.println(total);
        int total1 = knapsackDyna(val, wt, val.length, W);
        System.out.println(total1);
    }

    /**
     * By Greedy Method
     */
    public static int knapsackGreedy(int[] val, int[] wt, int length, int W) {
        if (W == 0 || length == 0)
            return 0;
        /* If weight of the nth item is more than Knapsack capacity W, then
           this item cannot be included in the optimal solution     */
        if (wt[length - 1] > W)
            return knapsackGreedy(val, wt, length - 1, W);
        else
            //Maximum value obtained by n-1 items and W weight (excluding nth item).
            return Math.max(knapsackGreedy(val, wt, length - 1, W),
                    val[length - 1] + knapsackGreedy(val, wt, length - 1, W - wt[length - 1]));
    }

    /*
        By Dynamic Method
     */
    public static int knapsackDyna(int[] val, int[] wt, int length, int W) {
        int T[][] = new int[length + 1][W + 1];
        for (int i = 0; i < length + 1; i++) { // for each item including 0th item
            for (int w = 0; w <= W; w++) {
                /* if knapsack weight is 0 OR 0 item, then the maximum value can be collected is 0 */
                if (i == 0 || w == 0)
                    T[i][w] = 0;
                else {
                    // Note : i denotes all items of wt including 0th item,
                    // so weight of ith item => wt[i-1], value of ith item => val[i-1]
                    if (wt[i - 1] > w) { // If current item has more weight than required weight, then exclude this item
                        T[i][w] = T[i - 1][w];
                    } else { // else take it by calculating both scenario
                        int maxWithoutCurrentItem = T[i - 1][w]; // Max value can be collected Without taking current item
                        int maxWithCurrentItem = T[i - 1][w - wt[i - 1]] + val[i - 1]; // Max value can be collected including current item
                        T[i][w] = Math.max(maxWithCurrentItem, maxWithoutCurrentItem);
                    }
                }
            }
        }
        System.out.println("Matrix : ");
        Util.print(T);
        System.out.println("Required Items : ");
        printResultantItems(T, T.length - 1, T[0].length - 1, val, wt);
        return T[length][W];
    }

    private static void printResultantItems(int[][] T, int i, int w, int[] val, int[] wt) {
        if (i == 0 && w == 0)
            return;
        // if max resultant value is same with the max resultant value without the ith item,
        // i.e the current item is not taken
        if (T[i - 1][w] == T[i][w])
            printResultantItems(T, i - 1, w, val, wt);
        else { // The current item is taken
            System.out.println("(" + wt[i - 1] + "," + val[i - 1] + ")");
            // move to the max resultant value without the current items
            printResultantItems(T, i - 1, w - wt[i - 1], val, wt);
        }
    }
}
