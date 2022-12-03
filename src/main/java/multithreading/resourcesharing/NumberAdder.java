package multithreading.resourcesharing;

public class NumberAdder {

    private int number = 0;
    private Object object = new Object();

    public void increment() {
        synchronized (object) {
            number++;
        }
    }

    public void decrement() {
        synchronized (object) {
            number--;
        }
    }

    public synchronized int getNumber() {
        return this.number;
    }
}
