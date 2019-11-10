package tech.marcusvieira.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import tech.marcusvieira.utils.ConcurrentUtils;

public class SynchronizationExample {

    private static final int NUM_INCREMENTS = 10000;

    private static int syncCount = 0;
    private static int nonSyncCount = 0;

    public static void main(String[] args) {
        testSyncIncrement();
        testNonSyncIncrement();
    }

    private static void testSyncIncrement() {
        syncCount = 0;

        ExecutorService executor = Executors.newFixedThreadPool(3);

        IntStream.range(0, NUM_INCREMENTS)
            .forEach(i -> executor.submit(SynchronizationExample::incrementSync));

        ConcurrentUtils.stopExecutor(executor);

        System.out.println("   Sync: " + syncCount);
    }

    private static void testNonSyncIncrement() {
        nonSyncCount = 0;

        ExecutorService executor = Executors.newFixedThreadPool(3);

        IntStream.range(0, NUM_INCREMENTS)
            .forEach(i -> executor.submit(SynchronizationExample::increment));

        ConcurrentUtils.stopExecutor(executor);

        System.out.println("NonSync: " + nonSyncCount);
    }

    private static synchronized void incrementSync() {
        syncCount = syncCount + 1;
    }

    private static void increment() {
        nonSyncCount = nonSyncCount + 1;
    }
}
