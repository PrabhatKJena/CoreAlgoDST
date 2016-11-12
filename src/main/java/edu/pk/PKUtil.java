package edu.pk;

import edu.pk.dst.Iterable;
import edu.pk.dst.Node;

public class PKUtil {
    public static void print(int[] a) {
        for (int i : a) {
            System.out.printf("%-8d", i);
        }
        System.out.println();
    }

    public static void print(double[] a) {
        for (double i : a) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

    public static void print(Iterable<Node> iterable) {
        iterable.forEach(e -> System.out.printf("%-8d", e.getData()));
        System.out.println();
    }
}
