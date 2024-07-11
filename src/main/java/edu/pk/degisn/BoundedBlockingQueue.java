package edu.pk.design;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue<T> {

  private T[] queue;
  private int head;
  private int tail;
  private int size;
  private Lock lock = new ReentrantLock();
  private Condition full = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  public BoundedBlockingQueue(int capacity) {
    queue = (T[]) new Object[capacity];
    head = 0;
    tail = 0;
    size = 0;
  }
	
  public void enqueue(T x) throws InterruptedException {
    lock.lock();
    try {
      while (size == queue.length) {
        System.out.println("full waiting");
        full.await();
      }
      queue[tail] = x;
      size++;
      tail = (tail + 1) % queue.length;
      System.out.println("Added " + x + " size = " + size);
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  public T dequeue() throws InterruptedException {
    lock.lock();
    try {
      while (size == 0) {
        System.out.println("empty waiting");
        notEmpty.await();
      }
      T x = queue[head];
      size--;
      System.out.println("Removed " + x + " size = " + size);
      head = (head + 1) % queue.length;
      full.signal();
      return x;
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] args) {
    final BoundedBlockingQueue<Long> blockingQueue = new BoundedBlockingQueue<>(10);
    final Random random = new Random(2000);
    // Producer
    new Thread(() -> {
      while (true) {
        try {
          blockingQueue.enqueue(System.currentTimeMillis());
          Thread.sleep(random.nextInt(500));
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }).start();

    // Consumer
    new Thread(() -> {
      while (true) {
        try {
          final Long item = blockingQueue.dequeue();
          Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }).start();

  }
}
