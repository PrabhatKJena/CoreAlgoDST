package edu.pk.algo;

/**
 * How many different ways can you make change for an amount, given a list of coins?
 * In this problem, your code will need to efficiently compute the answer.
 */
public class ChangeCoinProblem {
    public static void main(String[] args) {
        int S[] = {1, 2, 3, 4, 5};
        int n = 5;
        int a = countRecursive(S, 0, n);
        System.out.println(a);
        a = countDynamic(S, n);
        System.out.println(a);
    }

    /**
     * Dynamic Method
     * @param s coins
     * @param n amount to change
     * @return no of ways the amount can be changed
     */
    private static int countDynamic(int[] s, int n) {
        int a[][] = new int[n + 1][s.length + 1];
        for (int i = 0; i < a.length; i++)
            a[i][0] = 0;
        for (int j = 0; j < a[0].length; j++)
            a[0][j] = 1;
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a[0].length; j++) {
                if (s[j - 1] > i)
                    a[i][j] = a[i][j - 1];
                else {
                    a[i][j] = a[i][j - 1] + a[i - s[j - 1]][j];
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
