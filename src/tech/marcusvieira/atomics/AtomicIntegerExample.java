package tech.marcusvieira.atomics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import tech.marcusvieira.utils.Utils;

public class AtomicIntegerExample {

    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        Utils.stopExecutor(executor);

        System.out.println(atomicInt.get());

        //update and get value
        AtomicInteger atomicInt2 = new AtomicInteger(0);

        ExecutorService executor2 = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
            .forEach(i -> {
                Runnable task = () ->
                    atomicInt2.updateAndGet(n -> n + 2);
                executor2.submit(task);
            });

        Utils.stopExecutor(executor2);

        System.out.println(atomicInt2.get());

        //accumlate and get operation
        AtomicInteger atomicInt3 = new AtomicInteger(0);

        ExecutorService executor3 = Executors.newFixedThreadPool(4);

        IntStream.range(0, 1000)
            .forEach(i -> {
                Runnable task = () ->
                    atomicInt3.accumulateAndGet(i, (n, m) -> n + m);
                executor3.submit(task);
            });

        Utils.stopExecutor(executor3);

        System.out.println(atomicInt3.get());
    }
}
