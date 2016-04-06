package edu.pk.poc.concur.cyclbar;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            System.out.println("Triggering Mail.....");
        });
        Party first = new Party(1000, barrier, "PARTY-1");
        Party second = new Party(2000, barrier, "PARTY-2");
        Party third = new Party(3000, barrier, "PARTY-3");
        Party fourth = new Party(4000, barrier, "PARTY-4");
        first.start();
        second.start();
        third.start();
        fourth.start();
        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}

class Party extends Thread {
    private int duration;
    private CyclicBarrier barrier;

    public Party(int duration, CyclicBarrier barrier, String name) {
        super(name);
        this.duration = duration;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            System.out.println(Thread.currentThread().getName() + " is calling await()");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " has started running again");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CyclicBarrier {
    private int size;
    private int count;
    private Runnable action;
    private boolean isActionTriggered = false;

    public CyclicBarrier(int size) {
        this.size = size;
    }

    public CyclicBarrier(int size, Runnable action) {
        this.size = size;
        this.action = action;
    }

    synchronized public void await() throws InterruptedException {
        ++count;
        while (count < size)
            wait();
        if (!isActionTriggered)
            action.run();
        isActionTriggered = true;
        notifyAll();
    }
}
