package edu.pk;

/**
 * Ordering execution of multiple threads
 */
public class MultiThreadSequence {
    public static void main(String[] args) {
        LastThread lastThread = new LastThread();
        Thread t1 = new Thread(new Print1(lastThread), "Print1");
        Thread t2 = new Thread(new Print2(lastThread), "Print2");
        Thread t3 = new Thread(new Print3(lastThread), "Print3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class LastThread {
    int threadNo;
}

class Print1 implements Runnable {

    LastThread lastThread;

    public Print1(LastThread lastThread) {
        this.lastThread = lastThread;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i += 3) {
            synchronized (lastThread) {
                while (!(lastThread.threadNo == 0 || lastThread.threadNo == 3)) {
                    try {
                        lastThread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("....." + Thread.currentThread().getName() + ":" + i);
                lastThread.threadNo = 1;
                lastThread.notifyAll();
            }
        }

    }
}

class Print2 implements Runnable {

    LastThread lastThread;

    public Print2(LastThread lastThread) {
        this.lastThread = lastThread;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 100; i += 3) {
            synchronized (lastThread) {
                while (!(lastThread.threadNo == 1)) {
                    try {
                        lastThread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("..........." + Thread.currentThread().getName() + ":" + i);
                lastThread.threadNo = 2;
                lastThread.notifyAll();
            }
        }

    }
}

class Print3 implements Runnable {

    LastThread lastThread;

    public Print3(LastThread lastThread) {
        this.lastThread = lastThread;
    }

    @Override
    public void run() {
        for (int i = 3; i <= 100; i += 3) {
            synchronized (lastThread) {
                while (!(lastThread.threadNo == 2)) {
                    try {
                        lastThread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("................." + Thread.currentThread().getName() + ":" + i);
                lastThread.threadNo = 3;
                lastThread.notifyAll();
            }
        }

    }
}