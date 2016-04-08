package edu.pk.algo.math;

/**
 * Babylonian method for square root
 *
 * https://www.deltacollege.edu/dept/basicmath/Babylonian.htm
 */
public class SqrtByBabylonianMethod {
    public static void main(String[] args) {
        System.out.println(sqrt(50000));
    }

    private static float sqrt(float n) {
        float x = n; // arbitrary guess
        float y = 1;
        float error = 0.000001f;
        while (x - y > error) {
            y = n / x;
            x = (x + y) / 2; // Next guess
        }
        return x;
    }
}