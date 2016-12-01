/**
 * Created by prabhat on 16/11/16.
 */
public class TestA {
    class Inner {
        void test() {
            if (!TestA.this.f) {
                m1();
            }
        }
    }

    public void m1() {
        System.out.println(f);
    }

    private boolean f;

    public TestA() {
        new Inner().test();
    }

    public static void main(String[] args) {
        new TestA();
    }
}

