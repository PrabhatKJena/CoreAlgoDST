package edu.pk.poc.concur.cdl.poc;

public class CountDownLatchPOC {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);

        Thread deploymentProcess = new Thread(new Deployer(latch));
        Thread verifierProcess = new Thread(new Verifier(latch));
        deploymentProcess.start();
        verifierProcess.start();
    }
}

class Deployer implements Runnable {
    CountDownLatch latch;
    Deployer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Deployment Started");
        doSomething(1000);
        System.out.println("...WAR Deployed");
        latch.countDown();
        doSomething(1000);
        System.out.println("...SQL Script Executed");
        latch.countDown();

        doSomething(1000);
        System.out.println("...Config Changed");
        latch.countDown();

        doSomething(1000);
        System.out.println("...Server Restarted");
        latch.countDown();
        System.out.println("Deployment Completed");
    }

    private void doSomething(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
        }
    }
}

class Verifier implements Runnable {
    CountDownLatch latch;
    Verifier(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("Started Verifying");
            Thread.sleep(3000);
            System.out.println("Finished Verifying");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CountDownLatch {
    volatile int size;

    public CountDownLatch(int size) {
        this.size = size;
    }

    synchronized public void await() throws InterruptedException {
        while (this.size > 0) {
            wait();
        }
    }

    synchronized public void countDown() {
        --this.size;
        if(this.size == 0)
            notifyAll();
    }
}
