package edu.pk.poc.concur.cdl.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);

        Thread verifierProcess = new Thread(new Verifier(latch));

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new Thread(new Deployer(latch), "Server 1"));
        executorService.submit(new Thread(new Deployer(latch), "Server 2"));
        executorService.submit(new Thread(new Deployer(latch), "Server 3"));
        executorService.submit(new Thread(new Deployer(latch), "Server 4"));

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
        doSomething(2000);
        System.out.println("...WAR Deployed");
        doSomething(1000);
        System.out.println("...SQL Script Executed");

        doSomething(3000);
        System.out.println("...Config Changed");

        doSomething(3000);
        System.out.println("...Server Restarted");
        System.out.println("Deployment Completed");
        latch.countDown();
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
            latch.await(); // Wait until deployment complete on 4 servers
            System.out.println("Started Verifying...");
            Thread.sleep(3000);
            System.out.println("Finished Verifying...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
