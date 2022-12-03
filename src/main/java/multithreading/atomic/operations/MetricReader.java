package multithreading.atomic.operations;

public class MetricReader extends Thread{

    private Metrics metrics;

    public MetricReader(Metrics metrics) {
        this.metrics = metrics;
    }

    @Override
    public void run() {
        while  (true) {
            System.out.println("current avg is " + metrics.getAverage());
        }
    }
}
