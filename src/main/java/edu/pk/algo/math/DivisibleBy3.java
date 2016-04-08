package edu.pk.algo.math;
/**
 * Write an Efficient Method to Check if a Number is Multiple of 3
 *
 * Example: 23 (00010111)
 1) Get count of all set bits at odd positions (For 23 it’s 3).
 2) Get count of all set bits at even positions (For 23 it’s 1).
 3) If difference of above two counts is a multiple of 3 then number is also a multiple of 3.

 Above can be proved by taking the example of 11 in decimal numbers. (In this context 11 in decimal numbers
 is same as 3 in binary numbers)
 If difference between sum of odd digits and even digits is multiple of 11 then decimal number is multiple of 11.
 Let’s see how.

 Let’s take the example of 2 digit numbers in decimal
 AB = 11A -A + B = 11A + (B – A)
 So if (B – A) is a multiple of 11 then is AB.

 Let us take 3 digit numbers.
 ABC = 99A + A + 11B – B + C = (99A + 11B) + (A + C – B)
 So if (A + C – B) is a multiple of 11 then is (A+C-B)
 */
public class DivisibleBy3 {
    public static void main(String[] args) {
        System.out.println(isDivisibleBy3(37));
    }

    private static boolean isDivisibleBy3(int n) {
        int oddCount = 0;
        int evenCount = 0;
        if (n == 0)
            return true;
        if (Math.abs(n) < 2)
            return false;

        while (n > 0) {
            if ((n & 1) != 0) //if every odd ith digit is 1 then count odd
                oddCount++;
            n >>= 1;
            if ((n & 1) != 0) //if every even ith digit is 1 then count even
                evenCount++;
            n >>= 1;
        }
        return (isDivisibleBy3(Math.abs(oddCount - evenCount)));
    }
}
