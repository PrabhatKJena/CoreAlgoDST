package edu.pk.file;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by pkumarjena on 12/9/16.
 *
 * Reads line from a file from bottom to top and print (line # n,n-1,...1)
 */
public class RandomAccessFilePro {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/pkumarjena/Documents/raf_test.txt", "r");// read-only mode

        // the length of the file (no of characters)
        long length = raf.length();
        int i = -1;
        StringBuilder line = new StringBuilder();
        while (length != 0) {
            raf.seek(length);
            if ((i = raf.read()) != '\n') {
                line.insert(0, (char) i);
            } else { // if new line then print and reset the string
                System.out.println(line.toString());
                line.setLength(0);
            }
            length--;
        }

    }
}
