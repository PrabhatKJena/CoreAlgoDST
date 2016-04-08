package edu.pk.algo.dnc;

public class PowerNByDivideNConquer {
    public static void main(String[] args) {
        System.out.println(pow(2, -5));
        System.out.println(Math.pow(2, 1.2));
    }

    /**
     * Time complexity T(n) = O(logn)
     * @param i
     * @param j
     * @return
     */
    private static float pow(int i, int j) {
        if (j == 0)
            return 1;
        float temp = pow(i, j / 2);
        if (j % 2 == 0) { // since n^4 = n^2 * n^2
            return temp * temp;
        } else {
            if (j > 0) {
                return i * temp * temp; // since 2^3 = 2 * 2^1 * 2^1
            } else
                return (temp * temp) / i; // since 2^-3 = (2^-1 * 2^-1)/2
        }
    }
}
