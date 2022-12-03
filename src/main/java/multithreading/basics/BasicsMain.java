package multithreading.basics;

public class BasicsMain {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("We are now in thread: " + Thread.currentThread().getName());
            System.out.println("Current priority: " + Thread.currentThread().getPriority());
            throw new RuntimeException("Yooooo");
        });

        thread.setName("My Thread");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Thread was fucked up: " + t.getName());
                e.printStackTrace();
            }
        });
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(10000);
    }
}
