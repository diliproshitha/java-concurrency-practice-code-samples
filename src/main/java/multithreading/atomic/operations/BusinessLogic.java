package multithreading.atomic.operations;

import java.util.Random;

public class BusinessLogic extends Thread{

    private Metrics metrics;
    private Random random;

    public BusinessLogic(Metrics metrics) {
        this.metrics = metrics;
    }

    @Override
    public void run() {
        while (true) {
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();
            metrics.addSample(end - start);
        }
    }
}
