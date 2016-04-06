import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int t0 = 0; t0 < t; t0++) {
            String str = sc.next();
            char[] chars = str.toCharArray();
            int l = chars.length;
            int l1 = l / 2;
            boolean f = true;
            for (int i = 1, j = l - 2; i <= l1 && j >= l1; i++, j--) {
                if (Math.abs(chars[i] - chars[i - 1]) != Math.abs(chars[j] - chars[j + 1])) {
                    f = false;
                    break;
                }
            }
            if (f)
                System.out.println("Funny");
            else
                System.out.println("Not Funny");
        }
    }
}

class Util {

    public static void print(int a[][]) {
        for (int x[] : a) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        //System.out.println("--------------");
    }

    public static void print(int a[]) {
        for (int i = 65; i <= 90; i++) {
            System.out.print((char) i + ":" + (a[i] + a[i + 32]) + "  ");
        }
        System.out.println();
    }

    private static InputStreamReader streamReader = new InputStreamReader(System.in);
    private static LineNumberReader lineNumberReader = new LineNumberReader(streamReader);

    public String nextLine() {
        try {
            return lineNumberReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
