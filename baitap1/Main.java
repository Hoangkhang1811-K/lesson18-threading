package baitap1;

public class Main {
    public static void main(String[] args) {
        NumberGenerator generator1 = new NumberGenerator();
        NumberGenerator generator2 = new NumberGenerator();

        Thread t1 = new Thread(generator1, "Thread-1");
        Thread t2 = new Thread(generator2, "Thread-2");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }
}

class NumberGenerator implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName()
                    + " | number=" + i
                    + " | generatorHash=" + this.hashCode());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " bi ngat!");
                return;
            }
        }
    }
}
