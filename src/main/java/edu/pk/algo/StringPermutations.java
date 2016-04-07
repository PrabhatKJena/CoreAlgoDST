package edu.pk.algo;

public class StringPermutations {
    public static void main(String[] args) {
        String s = "12345";
        permute(s.toCharArray(), 0, s.length() - 1, "");
        System.out.println("Total Permutation :" + count);
    }

    static int count = 0;

    private static void permute(char[] chars, int i, int j, String prefix) {
        if (i == j) {
            System.out.println(String.valueOf(chars));
            count++;
            return;
        }
        if (i + 1 == j) {
            System.out.println(prefix + chars[i] + "" + chars[j]);
            System.out.println(prefix + chars[j] + "" + chars[i]);
            count += 2;
            return;
        }
        for (int n = i; n <= j; n++) {
            swap(chars, n, i);
            permute(chars, i + 1, j, prefix + chars[i]);
            swap(chars, n, i);
        }
    }

    private static void swap(char[] chars, int n, int length) {
        chars[n] = (char) ((chars[n] + chars[length]) - (chars[length] = chars[n]));
    }
}
