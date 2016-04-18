package edu.pk.algo;

import edu.pk.PKUtil;
import edu.pk.Util;

import java.util.Arrays;
import java.util.Collections;

public class MinimumCoinChangeProblem {
    public static void main(String[] args) {
        double coins[] = {10, 5, .25, .5, 1, 2};
        double val = 9.90;
        int n = findMinCoins(coins, val);
        System.out.println(n);
    }

    private static int findMinCoins(double[] coins, double val) {
        Arrays.sort(coins);
        int c = 0;
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
