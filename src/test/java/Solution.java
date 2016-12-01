import java.io.*;
import java.util.*;

class Parser {
    class Comparator {
        public boolean compare(int a, int b) {
            return a == b;
        }

        public boolean compare(String a, String b) {
            return Objects.equals(a, b);
        }

        public boolean compare(int a[], int b[]) {
            return Arrays.equals(a, b);
        }
    }

    public boolean isBalanced(String str) {
        if (str == null || str.trim().length() == 0)
            return true;
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '(' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty())
                    return false;
                char ch1 = stack.pop();
                if (ch == '}' && ch1 != '{')
                    return false;
                if (ch == ']' && ch1 != '[')
                    return false;

            }
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }
}

public class Solution {
    static int getMin(String str) {
        if (str == null || str.length() == 0)
            return 0;
        long l = Long.parseLong(str, 2);
        System.out.println(l);
        if (isPowerOf5(l))
            return 1;
        int count = 0;
        for (long n = l; n >= 1; ) {
            if (isPowerOf5(n)) {
                String s = Long.toBinaryString(n);
                if (str.startsWith(s) || str.endsWith(s)) {
                    str = str.replaceFirst(s, "");
                    count++;
                } else
                    n--;
                if (str.length() == 0)
                    return count;
            } else {
                n--;
            }
        }
        return -1;
    }

    static boolean isPowerOf5(long l) {
        long n = l;
        while (n > 1) {
            if (n % 5 == 0)
                n = n / 5;
            else
                return false;
        }
        if (n == 1)
            return true;
        return false;
    }

    static String is_Palindrome(String s) {
        int count[] = new int[26];
        for (int i = 0; i < 26; i++)
            count[i] = 0;
        char[] chars = s.toCharArray();
        int oddC = 0;
        int evenC = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            count[ch - 97]++;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0)
                oddC++;
        }
        if (oddC > 0 && oddC % 2 == 0)
            return "NO";
        return "YES";
    }

    public static void main(String[] args) {
        //System.out.println(getMin("11001101"));
        //System.out.println(getMin("101101"));
        System.out.println(is_Palindrome("abc"));
        /*System.out.println(is_Palindrome("cdefghmnopqrstuvw"));
        System.out.println(is_Palindrome("aabbcbb"));
        System.out.println(is_Palindrome("aabbccba"));
        System.out.println(is_Palindrome("aaabbb"));*/
    }

   /* static void finalPrice(int[] prices) {
        int d[] = new int[prices.length]; // This is comment 1
        for (int i = 0; i < prices.length; i++) {
            int di = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    di = prices[j];
                    break;
                }
            }
            d[i] = di;
        }
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum += (prices[i] - d[i]);
        }
        System.out.println(sum);
        for (int i = 0; i < d.length; i++) {
            if (d[i] == 0)
                System.out.print(i + " ");
        }
        System.out.println();
    }
*/
/*
    Scanner sc = new Scanner(System.in);
    String line = null;
    boolean isContinue = false;
        while (sc.hasNextLine()) {
        line = sc.nextLine();

        int msIndex = line.indexOf("*//*");
        if (msIndex != -1) { // if single line
            line = line.substring(msIndex);
            isContinue = true;
        }
        int meIndex = line.indexOf("*//*");
        if (meIndex != -1) {
            line = line.substring(0, meIndex + 2);
            isContinue = false;
        }
        if (isContinue) {
            System.out.println(line);
            continue;
        } else if (meIndex != -1) {
            System.out.println(line);
            continue;
        }

        int slashIndex = line.indexOf("//");
        if (slashIndex == 0)
            System.out.println(line);
        else if (slashIndex > 0) {
            System.out.println(line.substring(slashIndex));
        }

    }*/
}