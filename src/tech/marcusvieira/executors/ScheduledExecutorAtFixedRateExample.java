package tech.marcusvieira.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorAtFixedRateExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        int initialDelay = 0;
        int period = 1;

        //Please keep in mind that scheduleAtFixedRate() doesn't take into account the actual duration of the task.
        // So if you specify a period of one second but the task needs 2 seconds to be executed then the thread pool
        // will working to capacity very soon
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }
}
