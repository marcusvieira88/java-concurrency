package tech.marcusvieira.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;
import tech.marcusvieira.utils.Utils;

public class OptimisticLockingExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        // READ(1s) happens -> WRITE(2s) happens -> READ(1s) not executed because the write looking (2X)

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid 1: " + lock.validate(stamp));
                Utils.sleepThread(1);
                System.out.println("Optimistic Lock Valid 2: " + lock.validate(stamp));
                Utils.sleepThread(2);
                System.out.println("Optimistic Lock Valid 3: " + lock.validate(stamp));
            } finally {
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                Utils.sleepThread(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        Utils.stopExecutor(executor);
    }
}
