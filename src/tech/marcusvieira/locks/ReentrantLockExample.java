package tech.marcusvieira.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import tech.marcusvieira.utils.ConcurrentUtils;

public class ReentrantLockExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        ReentrantLock lock = new ReentrantLock();

        //first slow thread hold the lock
        executor.submit(() -> {
            lock.lock();
            try {
                ConcurrentUtils.sleepThread(3);
            } finally {
                lock.unlock();
            }
        });

        //second thread can not access the resource
        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        ConcurrentUtils.stopExecutor(executor);
    }
}
