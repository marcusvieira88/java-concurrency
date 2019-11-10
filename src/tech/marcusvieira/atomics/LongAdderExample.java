package tech.marcusvieira.atomics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;
import tech.marcusvieira.utils.ConcurrentUtils;

public class LongAdderExample {

    public static void main(String[] args) {

        //LongAdder provides methods add() and increment() just like the atomic number classes and is also thread-safe.
        // But instead of summing up a single result this class maintains a set of variables internally to reduce
        // contention over threads. The actual result can be retrieved by calling sum() or sumThenReset().
        LongAdder adder = new LongAdder();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        IntStream.range(0, 1000)
            .forEach(i -> executor.submit(adder::increment));

        ConcurrentUtils.stopExecutor(executor);

        System.out.println(adder.sumThenReset());
    }
}
