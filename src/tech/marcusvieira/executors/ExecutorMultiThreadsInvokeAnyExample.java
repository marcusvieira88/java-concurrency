package tech.marcusvieira.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorMultiThreadsInvokeAnyExample {

    public static void main(String[] args) {

        //Creates a work-stealing thread pool using the number of available processors
        //as its target parallelism level.
        ExecutorService executor = Executors.newWorkStealingPool();

        Callable<String> slowTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return "Test Marcus";
            } catch (InterruptedException e) {
                throw new IllegalStateException("Task Interrupted", e);
            }
        };

        List<Callable<String>> callables = Arrays.asList(
            () -> "task2",
            slowTask,
            () -> "task3");

        try {
            final String result = executor.invokeAny(callables);
            System.out.println("Result result=" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
