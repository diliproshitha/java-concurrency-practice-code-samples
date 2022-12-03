package multithreading.resourcesharing;

public class IncrementThread extends Thread{

    private NumberAdder numberAdder;

    public IncrementThread(NumberAdder numberAdder) {
        this.numberAdder = numberAdder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            numberAdder.increment();
        }
    }
}
