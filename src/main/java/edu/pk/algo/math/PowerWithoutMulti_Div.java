package edu.pk.algo.math;

/**
 * Write you own Power without using multiplication(*) and division(/) operators
 * <p>
 * For example to calculate 5^6.
 * 1) First 5 times add 5, we get 25. (5^2)
 * 2) Then 5 times add 25, we get 125. (5^3)
 * 3) Then 5 time add 125, we get 625 (5^4)
 * 4) Then 5 times add 625, we get 3125 (5^5)
 * 5) Then 5 times add 3125, we get 15625 (5^6)
 */
public class PowerWithoutMulti_Div {
    public static void main(String[] args) {
        System.out.println(powByIterative(3, 4));
        System.out.println(powByRecursive(3, 4));
    }

    /* Recursive Method */
    private static int powByRecursive(int a, int b) {
        if (b == 0)
            return 1;
        return multiply(a, powByRecursive(a, b - 1));
    }

    private static int multiply(int a, int b) {
        if (b == 1)
            return a;
        if (b == 0)
            return 0;
        return a + multiply(a, b - 1);
    }

    /* Iterative method */
    private static int powByIterative(int a, int b) {
        int a0 = a;
        int sum = 0;
        while (b > 1) {
            for (int i = 1; i <= a; i++) {
                sum += a0;
            }
            a0 = sum;
            sum = 0;
            b--;
        }
        return a0;
    }

}
