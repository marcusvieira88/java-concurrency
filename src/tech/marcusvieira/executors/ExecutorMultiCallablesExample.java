package tech.marcusvieira.executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorMultiCallablesExample {

    public static void main(String[] args) {

        //Creates a work-stealing thread pool using the number of available processors
        //as its target parallelism level.
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
            () -> "task1",
            () -> "task2",
            () -> "task3");

        try {
            executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        System.out.println("Calling futures");
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
