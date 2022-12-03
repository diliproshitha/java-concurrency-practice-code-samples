package multithreading.producerconsumer.app;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadSafeQueue {

    private final Queue<MatricesPair> queue = new LinkedList<>();
    private boolean isEmpty = true;
    private boolean isTerminate = false;
    private static final int CAPACITY = 5;

    public synchronized void add(MatricesPair matricesPair) {

        while (queue.size() == CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.add(matricesPair);
        isEmpty = false;
        notify();
    }

    public synchronized MatricesPair remove() {
        MatricesPair matricesPair = null;
        while(isEmpty && !isTerminate) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(queue.size() == 1) {
            isEmpty = true;
        }
        if (queue.size() == 0 && isTerminate) {
            return null;
        }

        System.out.println("Queue size: " + queue.size());
        matricesPair = queue.remove();

        if (queue.size() == CAPACITY - 1) {
            notify();
        }
        return matricesPair;
    }

    public synchronized void terminate() {
        isTerminate = true;
        notifyAll();
    }
}
