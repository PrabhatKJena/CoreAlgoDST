package edu.pk.algo;

/*
 * Given a string you need to print all possible strings that can be made by placing spaces (zero or one) in between them.

 Input:  str[] = "ABC"
 Output: ABC
 AB C
 A BC
 A B C
 */
public class PossibleStringsWithSpace {
    public static void main(String[] args) {
        String str = "ABC";
        printAllPossible(str);
    }

    private static void printAllPossible(String str) {
        char ch[] = str.toCharArray();
        char buf[] = new char[(2 * ch.length)];
        buf[0] = ch[0];
        printPatterns(ch, buf, 1, 1, ch.length);
    }

    private static void printPatterns(char[] ch, char[] buf, int i, int j, int length) {
        if (i == length) {
            buf[j] = '\0';
            printBuf(buf);
            return;
        }
        // Having two options A -> AB OR A->A_B
        // 1. Either append the next character
        buf[j] = ch[i];
        printPatterns(ch, buf, i + 1, j + 1, length);

        // OR 2. Append space followed by next character
        buf[j] = ' ';
        buf[j + 1] = ch[i];
        printPatterns(ch, buf, i + 1, j + 2, length);
    }

    private static void printBuf(char[] buf) {
        for (int i=0; buf[i] != '\0'; i++) {
            System.out.print(buf[i]);
        }
        System.out.println();
    }
}
