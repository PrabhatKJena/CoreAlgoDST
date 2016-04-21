package edu.pk.algo.dynapro;

/**
 * How many different ways can you make change for an amount, given a list of coins?
 * In this problem, your code will need to efficiently compute the answer.
 */
public class ChangeCoinProblem {
    public static void main(String[] args) {
        int S[] = {1, 2, 4, 5};
        int n = 6;
        int a = countRecursive(S, 0, n);
        System.out.println(a);
        a = countDynamic(S, n);
        System.out.println(a);
    }

    /**
     * Dynamic Method
     *
     * @param s coins
     * @param n amount to change
     * @return no of ways the amount can be changed
     */
    private static int countDynamic(int[] s, int n) {
        int a[][] = new int[s.length + 1][n + 1];
        for (int i = 0; i <= s.length; i++) { // for all coins fom S0 to S(length)-1
            for (int j = 0; j <= n; j++) { // for all values from 0 to n
                if (j == 0) {// if amount is 0 then only 1 solution i.e no coin
                    a[i][j] = 1;
                } else if (i == 0) { // if 0 coins are there, then 0 solutions for all required amounts
                    a[i][j] = 0;
                } else if (s[i - 1] > j) { // if ith coin is greater than j amount, then don't include ith coin
                                            // i-1 because S doesn't contain 0 th coin
                    a[i][j] = a[i - 1][j];
                } else {
                    int x = a[i - 1][j]; // excluding ith coin
                    int y = a[i][j - s[i - 1]]; // including the coin
                    a[i][j] = x + y;
                }
            }
        }
        printArray(a);
        return a[a.length - 1][a[0].length - 1];
    }

    private static void printArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%4d", a[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Recursive Method
     *
     * @param S
     * @param m
     * @param n
     * @return
     */
    private static int countRecursive(int[] S, int m, int n) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        if (m == S.length && n > 0)
            return 0;
        return countRecursive(S, m + 1, n) + countRecursive(S, m, n - S[m]);
        // For Non-Repeat
        //return countRecursive(S, m + 1, n) + countRecursive(S, m +1, n - S[m]);
    }
}
