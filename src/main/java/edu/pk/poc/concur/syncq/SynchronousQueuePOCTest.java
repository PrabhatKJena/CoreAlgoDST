package edu.pk.poc.concur.syncq;

public class SynchronousQueuePOCTest {
    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueuePOC<Integer> queue = new SynchronousQueuePOC<>();
        Thread t = new Thread(() -> {
            try {
                for(int i=0;i<10;i++) {
                    //System.out.println("Taking.. ");
                    Integer take = queue.take();
                    System.out.println("Taken "+take);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Adding "+i);
                    queue.put(i);
                    //System.out.println("Added "+i);
                    Thread.sleep(200);
                }
                System.out.println(queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        System.out.println("End");
    }
}
