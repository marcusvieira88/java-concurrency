package tech.marcusvieira.threads;

import java.util.concurrent.Callable;

public class ThreadExample {

    public static void main(String[] args) {
        Runnable exampleTask = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Thread name=" + threadName);
        };

        System.out.println("Example task executed using the main thread!");
        exampleTask.run();

        System.out.println("Example task executed using the new thread!");
        Thread thread = new Thread(exampleTask);
        thread.start();

        System.out.println("Example task2 executed using the new thread!");
        Thread thread2 = new AvguThread();
        thread2.start();

        System.out.println("Example task3 executed using the new thread!");
        Callable thread3 = new MarcusCallable();
        try {
            final Double response = (Double) thread3.call();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
