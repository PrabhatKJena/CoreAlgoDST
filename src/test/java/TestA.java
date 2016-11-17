/**
 * Created by prabhat on 16/11/16.
 */
public class TestA {
    public static void main(String[] args) {
       /* f(64);
        System.out.println(c);*/
        int a[] = {1, 2, 3, 4};
        int b[] = new int[2];
        System.arraycopy(a, 1, b, 1, 1);
        for (int i : b) {
            System.out.println(i);
        }
    }

    static int c = 0;

    static int f(double n) {
        System.out.println("n = " + n);
        c++;
        if (n <= 2)
            return 1;
        return f(Math.floor(Math.sqrt(n)) + n);
    }
}
