import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] a = sc.nextLine().toCharArray();
        char[] b = sc.nextLine().toCharArray();
        int arr[] = new int[256];

        for (int i = 0; i < a.length; i++) {
            arr[a[i]]++;
        }

        int delCount = 0;
        for (int i = 0; i < b.length; i++) {
            int ch = b[i];
            if (arr[ch] == 0)
                delCount++;
            else{
                arr[ch]--;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0)
                delCount += arr[i];
        }
        System.out.println(delCount);
    }
}