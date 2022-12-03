package multithreading.producerconsumer.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class MatricesMultiplierConsumer extends Thread{

    private ThreadSafeQueue queue;
    private FileWriter fileWriter;
    private static final int N = 10;

    public MatricesMultiplierConsumer(ThreadSafeQueue queue, FileWriter fileWriter) {
        this.queue = queue;
        this.fileWriter = fileWriter;
    }

    @Override
    public void run() {
        while (true) {
            MatricesPair matricesPair = queue.remove();
            if (matricesPair == null) {
                System.out.println("No more matrices to read. Consumer is terminating!");
                break;
            }

            float[][] result = multiplyMatrices(matricesPair.matrix1, matricesPair.matrix2);
            try {
                saveMatrixToFile(fileWriter, result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private float[][] multiplyMatrices(float[][] m1, float[][] m2) {
        float[][] result = new float[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int k = 0; k < N; k++) {
                    result[r][c] += m1[r][k] * m2[k][c];
                }
            }
        }

        return result;
    }

    private static void saveMatrixToFile(FileWriter fileWriter, float[][] matrix) throws IOException {
        for (int r = 0; r < N; r++) {
            StringJoiner stringJoiner = new StringJoiner(", ");
            for (int c = 0; c < N; c++) {
                stringJoiner.add(String.format("%.2f", matrix[r][c]));
            }
            fileWriter.write(stringJoiner.toString());
            fileWriter.write('\n');
        }
        fileWriter.write('\n');
    }


}
