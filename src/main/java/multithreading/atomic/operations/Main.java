package multithreading.atomic.operations;

public class Main {

    public static void main(String[] args) {
        Metrics metrics = new Metrics();

        BusinessLogic businessLogic1 = new BusinessLogic(metrics);
        BusinessLogic businessLogic2 = new BusinessLogic(metrics);
        MetricReader metricReader = new MetricReader(metrics);

        businessLogic1.start();
        businessLogic2.start();
        metricReader.start();
    }
}
