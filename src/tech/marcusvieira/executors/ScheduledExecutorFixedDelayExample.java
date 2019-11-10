package tech.marcusvieira.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorFixedDelayExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        //This example schedules a task with a fixed delay of one second between the end of an execution and the start
        // of the next execution. The initial delay is zero and the tasks duration is two seconds. So we end up with
        // an execution interval of 0s, 3s, 6s, 9s and so on. As you can see scheduleWithFixedDelay() is handy if you
        // cannot predict the duration of the scheduled tasks.
        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }
}
