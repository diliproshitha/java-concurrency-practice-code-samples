package multithreading.basics;

public class ThreadInheritance {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new NewThread();

        thread.setName("My Thread");

        thread.start();
    }

    private static class NewThread extends Thread {

        @Override
        public void run() {
            System.out.println("We are now in thread: " + this.getName());
        }
    }
}
