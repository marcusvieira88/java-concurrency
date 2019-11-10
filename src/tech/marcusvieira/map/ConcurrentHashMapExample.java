package tech.marcusvieira.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentHashMapExample {

    public static void main(String[] args) {

        //Parallelism pool
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put(i, "test" + i);
        }

        map.forEach(1, (key, value) ->
            System.out.printf("key: %s; value: %s; thread: %s\n",
                key, value, Thread.currentThread().getName()));
    }
}
