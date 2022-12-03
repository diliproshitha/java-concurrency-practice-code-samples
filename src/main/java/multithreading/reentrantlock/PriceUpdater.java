package multithreading.reentrantlock;

import java.util.Random;

public class PriceUpdater extends Thread {

    private PricesController pricesController;
    private Random random = new Random();

    public PriceUpdater(PricesController pricesController) {
        this.pricesController = pricesController;
    }

    @Override
    public void run() {
        while (true) {
            pricesController.getReentrantLock().lock();

            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pricesController.setBitcoinPrice(random.nextInt(20000));
                pricesController.setEtherPrice(random.nextInt(2000));
                pricesController.setLiteCoinPrice(random.nextInt(500));
                pricesController.setBitcoinCashPrice(random.nextInt(5000));
                pricesController.setRipplePrice(random.nextDouble());
            }
            finally {
                pricesController.getReentrantLock().unlock();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
