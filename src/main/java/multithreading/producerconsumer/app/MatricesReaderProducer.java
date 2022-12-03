package multithreading.producerconsumer.app;

import java.io.FileReader;
import java.util.Scanner;

public class MatricesReaderProducer extends Thread{

    private Scanner scanner;
    private ThreadSafeQueue queue;
    private static final int N = 10;

    public MatricesReaderProducer(FileReader fileReader, ThreadSafeQueue queue) {
        scanner = new Scanner(fileReader);
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            float[][] matrix1 = readMatrix();
            float[][] matrix2 = readMatrix();

            if (matrix1 == null || matrix2 == null) {
                queue.terminate();
                System.out.println("No more matrices to read. Producer thread is terminating.");
                return;
            }

            MatricesPair matricesPair = new MatricesPair();
            matricesPair.matrix1 = matrix1;
            matricesPair.matrix2 = matrix2;

            queue.add(matricesPair);
        }
    }

    private float[][] readMatrix() {
        float[][] matrix = new float[N][N];
        for (int r = 0; r < N; r++) {
            if (!scanner.hasNext()) {
                return null;
            }

            String[] line = scanner.nextLine().split(", ");
            for (int c = 0; c < N; c++) {
                matrix[r][c] = Float.parseFloat(line[c]);
            }
        }
        scanner.nextLine();
        return matrix;
    }
}
