package multithreading.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Join {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(100000000L, 3435L, 34563L, 3456L, 2423L, 65732L);
        List<FactorialThread> threads = new ArrayList<>();

        for (Long i : inputNumbers) {
            threads.add(new FactorialThread(i));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        Thread.sleep(2000);
        for (Thread thread : threads) {
            thread.interrupt();
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            }
            else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }
}
