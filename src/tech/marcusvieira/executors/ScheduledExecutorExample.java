package tech.marcusvieira.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        try {
            Runnable task = () -> System.out.println(" Scheduling: " + System.nanoTime());
            ScheduledFuture<?> future = executor.schedule(task, 5, TimeUnit.SECONDS);

            long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
            System.out.printf("Remaining Delay: %sms", remainingDelay);
        } finally{
            executor.shutdown();
        }
    }
}
