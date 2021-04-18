package tech.marcusvieira.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Utils {

    /**
     * Stops the executor and wait all executor tasks finishes, if has a problem in the termination the non-finished
     * tasksare killed.
     *
     * @param executor - executor instance
     */
    public static void stopExecutor(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Termination interrupted!");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("Killing non-finished tasks!");
            }
            executor.shutdownNow();
        }
    }

    public static void sleepThread(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
