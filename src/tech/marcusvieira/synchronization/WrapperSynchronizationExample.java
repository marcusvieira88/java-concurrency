package tech.marcusvieira.synchronization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import tech.marcusvieira.utils.Utils;

public class WrapperSynchronizationExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        //block the whole object during the operation
        Map<Integer, Integer> wrapperMap = Collections.synchronizedMap(new HashMap<>());
        final long initTime = System.currentTimeMillis();

        IntStream.range(0, 10000)
            .forEach(i -> executor.submit(()-> wrapperMap.put(i, i)));

        long endTime = System.currentTimeMillis();
        System.out.println("Execution Wrapper time="+ (endTime - initTime));

        Utils.stopExecutor(executor);

        ExecutorService executor2 = Executors.newFixedThreadPool(2);

        //block only the bucket that is being changed
        Map<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();
        final long initTime2 = System.currentTimeMillis();

        IntStream.range(0, 10000)
            .forEach(i -> executor2.submit(()-> concurrentMap.put(i, i)));

        long endTime2 = System.currentTimeMillis();
        System.out.println("Execution Concurrent time="+ (endTime2 - initTime2));

        Utils.stopExecutor(executor2);
    }
}
