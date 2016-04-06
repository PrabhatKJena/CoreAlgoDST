package edu.pk.poc.concur.exchanger;
import java.util.concurrent.Exchanger;
public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread task1 = new Thread(new Task(exchanger, 10));
        Thread task2 = new Thread(new Task(exchanger, 20));
        task2.start();
        task1.start();
    }
}
class Task implements Runnable {
    Exchanger<Integer> exchanger;
    int value;
    public Task(Exchanger<Integer> exchanger, int value) {
        this.exchanger = exchanger;
        this.value = value;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Before Exchange :" + value);
        try {
            value = exchanger.exchange(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " After Exchange :" + value);
    }
}

/*
class Exchanger1<E> {
    private SynchronousQueue<E> queue;
    private volatile boolean flag = false;

    synchronized public E exchange(E ele) throws InterruptedException {
        E e = null;
        synchronized (this) {
            if (!flag) {
                flag = true;
                queue.put(ele);
            }else{
                flag = false;
                queue.take();
            }
        }
        return e;
    }
}*/
