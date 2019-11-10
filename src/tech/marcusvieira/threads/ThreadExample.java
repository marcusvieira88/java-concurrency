package tech.marcusvieira.threads;

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
    }
}
