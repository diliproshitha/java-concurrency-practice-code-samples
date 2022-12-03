package multithreading.resourcesharing;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        NumberAdder numberAdder = new NumberAdder();
        IncrementThread incrementThread = new IncrementThread(numberAdder);
        DecrementThread decrementThread = new DecrementThread(numberAdder);

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        System.out.println(numberAdder.getNumber());
    }
}
