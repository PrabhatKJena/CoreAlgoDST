package edu.pk.poc.concur.cdl;

public class CountDownLatchPOCByJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread deploymentProcess = new Thread(new Deployer());
        Thread verifierProcess = new Thread(new Verifier());
        deploymentProcess.start();

        deploymentProcess.join();
        verifierProcess.start();
    }
}

class Deployer implements Runnable {

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
    }

    private void doSomething(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
        }
    }
}

class Verifier implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Started Verifying...");
            Thread.sleep(3000);
            System.out.println("Finished Verifying...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
