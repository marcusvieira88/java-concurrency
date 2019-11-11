package tech.marcusvieira.atomics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import tech.marcusvieira.utils.ConcurrentUtils;

public class Volatile {

    //stay in the main memory
    static volatile Integer volatileInt = 0;

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(() -> {
                volatileInt++;
            }));

        ConcurrentUtils.stopExecutor(executor);

        System.out.println(volatileInt);
    }
}
