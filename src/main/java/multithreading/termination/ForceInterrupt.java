package multithreading.termination;

import java.math.BigInteger;

public class ForceInterrupt {

    public static void main(String[] args) {

        Thread thread = new Thread(new LongComputationalTask(new BigInteger("200000"), new BigInteger("10000000000")));

        // daemon
        thread.setDaemon(true);
        thread.start();
        thread.interrupt();
    }

    private static class LongComputationalTask implements Runnable {

        private BigInteger base, power;

        public LongComputationalTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base + "^" + power + " = " + pow(power, base));
        }

        private BigInteger pow (BigInteger base, BigInteger power) {

            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }
}
