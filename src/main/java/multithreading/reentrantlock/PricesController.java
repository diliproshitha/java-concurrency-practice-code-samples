package multithreading.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PricesController {

    private Lock reentrantLock = new ReentrantLock();

    private double bitcoinPrice;
    private double etherPrice;
    private double liteCoinPrice;
    private double bitcoinCashPrice;
    private double ripplePrice;

    public Lock getReentrantLock() {
        return reentrantLock;
    }

    public void setReentrantLock(Lock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    public double getBitcoinPrice() {
        return bitcoinPrice;
    }

    public void setBitcoinPrice(double bitcoinPrice) {
        this.bitcoinPrice = bitcoinPrice;
    }

    public double getEtherPrice() {
        return etherPrice;
    }

    public void setEtherPrice(double etherPrice) {
        this.etherPrice = etherPrice;
    }

    public double getLiteCoinPrice() {
        return liteCoinPrice;
    }

    public void setLiteCoinPrice(double liteCoinPrice) {
        this.liteCoinPrice = liteCoinPrice;
    }

    public double getBitcoinCashPrice() {
        return bitcoinCashPrice;
    }

    public void setBitcoinCashPrice(double bitcoinCashPrice) {
        this.bitcoinCashPrice = bitcoinCashPrice;
    }

    public double getRipplePrice() {
        return ripplePrice;
    }

    public void setRipplePrice(double ripplePrice) {
        this.ripplePrice = ripplePrice;
    }
}
