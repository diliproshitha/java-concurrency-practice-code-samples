package multithreading.producerconsumer.app;

import java.io.*;

public class Main {

    private static final String INPUT_FILE = "./out/matrices";
    private static final String OUT_FILE = "./out/matrices_results.txt";

    public static void main(String[] args) throws IOException {

        ThreadSafeQueue queue = new ThreadSafeQueue();
        File inputFile = new File(INPUT_FILE);
        File outFile = new File(OUT_FILE);

        MatricesReaderProducer matricesReaderProducer = new MatricesReaderProducer(new FileReader(inputFile), queue);
        MatricesMultiplierConsumer matricesMultiplierConsumer = new MatricesMultiplierConsumer(queue, new FileWriter(outFile));
        matricesMultiplierConsumer.start();
        matricesReaderProducer.start();
    }
}
