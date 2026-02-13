package baitap3;

public class PrimeThreadDemo {

    public static void main(String[] args) {
        long durationMs = 2000; //

        LazyPrimeFactorization lazy = new LazyPrimeFactorization(durationMs);
        OptimizedPrimeFactorization opt = new OptimizedPrimeFactorization(durationMs);

        Thread t1 = new Thread(lazy, "LazyPrime");
        Thread t2 = new Thread(opt, "OptimizedPrime");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main bi ngat!");
        }

        System.out.println("\n===== KET QUA =====");
        System.out.println("LazyPrime dem duoc: " + lazy.getCount() + " so nguyen to");
        System.out.println("OptimizedPrime dem duoc: " + opt.getCount() + " so nguyen to");
    }
}

class LazyPrimeFactorization implements Runnable {
    private final long durationMs;
    private volatile long count = 0;

    public LazyPrimeFactorization(long durationMs) {
        this.durationMs = durationMs;
    }

    public long getCount() {
        return count;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long n = 2;

        while (System.currentTimeMillis() - start < durationMs) {
            if (isPrimeLazy(n)) {
                count++;
                System.out.println(Thread.currentThread().getName() + " -> " + n);
            }
            n++;
        }
    }

    private boolean isPrimeLazy(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        for (long i = 2; i <= n - 1; i++) { // chua toi uu
            if (n % i == 0) return false;
        }
        return true;
    }
}

class OptimizedPrimeFactorization implements Runnable {
    private final long durationMs;
    private volatile long count = 0;

    public OptimizedPrimeFactorization(long durationMs) {
        this.durationMs = durationMs;
    }

    public long getCount() {
        return count;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        long n = 2;

        while (System.currentTimeMillis() - start < durationMs) {
            if (isPrimeOptimized(n)) {
                count++;
                System.out.println(Thread.currentThread().getName() + " -> " + n);
            }
            n++;
        }
    }

    private boolean isPrimeOptimized(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        long limit = (long) Math.sqrt(n);
        for (long i = 3; i <= limit; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
