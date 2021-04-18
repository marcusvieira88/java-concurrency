package tech.marcusvieira.futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureCallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        Callable<String> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return "Test Marcus";
            } catch (InterruptedException e) {
                throw new IllegalStateException("Task Interrupted", e);
            }
        };

        //Executors.newFixedThreadPool(1) is equal to newSingleThreadExecutor()
        ExecutorService executor = Executors.newFixedThreadPool(1);
        try {
            Future<String> future = executor.submit(task);

            System.out.println("Future is done=" + future.isDone());

//            Result in TimeoutException because the task takes 2 Seconds to finish.
            String result = future.get(1, TimeUnit.SECONDS);

//            String result = future.get();

            System.out.println("Future done is done=" + future.isDone());
            System.out.print("result: " + result);
        } finally {
            executor.shutdown();
        }
    }
}
