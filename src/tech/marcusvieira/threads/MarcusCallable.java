package tech.marcusvieira.threads;

import java.util.concurrent.Callable;

public class MarcusCallable implements Callable<Double> {

    @Override
    public Double call() throws Exception {
         System.out.println("I'm Marquinhos");
        return 10D;
    }
}
