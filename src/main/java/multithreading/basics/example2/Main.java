package multithreading.basics.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingHacker(vault));
        threads.add(new DescendingHacker(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int password) {

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.password == password;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName((this.getClass().getSimpleName()));
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread: " + this.getName());
            super.start();
        }
    }

    private static class AscendingHacker extends HackerThread {

        public AscendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHacker extends HackerThread {

        public DescendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {

        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println("Game over hackers");
            System.exit(0);
        }
    }
}
