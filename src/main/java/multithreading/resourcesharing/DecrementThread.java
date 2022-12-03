package multithreading.resourcesharing;

public class DecrementThread extends Thread{

    private NumberAdder numberAdder;

    public DecrementThread(NumberAdder numberAdder) {
        this.numberAdder = numberAdder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            numberAdder.decrement();
        }
    }
}
