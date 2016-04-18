package edu.pk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class Util {

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
