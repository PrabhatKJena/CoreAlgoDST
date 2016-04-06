package edu.pk.poc.concur.syncq;

public class SynchronousQueuePOC<E> {
    E item;

    synchronized public void put(E item) throws InterruptedException {
        while (this.item != null) {
            wait();
        }
        this.item = item;
        notifyAll();
        while (this.item != null) {
            wait();
        }
    }

    synchronized public E take() throws InterruptedException {
        while (this.item == null)
            wait();
        E itm = this.item;
        this.item = null;
        notifyAll();
        return itm;
    }
}

