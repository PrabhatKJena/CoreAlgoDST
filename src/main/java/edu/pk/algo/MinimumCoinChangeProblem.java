package edu.pk.algo;

import edu.pk.PKUtil;
import edu.pk.Util;

import java.util.Arrays;
import java.util.Collections;

public class MinimumCoinChangeProblem {
    public static void main(String[] args) {
        double coins[] = {10, 5, .25, .05, 1, 2};
        double val = 9.5;
        int n = findMinCoins(coins, val);
        System.out.println(n);
    }

    /**
     * Find minimum number of coins required for a given amount
     * @param coins
     * @param val
     * @return
     */
    private static int findMinCoins(double[] coins, double val) {
        // Sort coins in ascending order
        Arrays.sort(coins);
        int c = 0;
        // add coins in descending order
        for (int i = coins.length - 1; i >= 0; ) {
            if (coins[i] <= val) {
                System.out.print(coins[i] + ", ");
                c++;
                val -= coins[i];
            } else
                i--;
            if (val == 0.0)
                break;
        }
        System.out.println();
        if (val > 0.0) {
            return -1;
        }
        return c;
    }
}
