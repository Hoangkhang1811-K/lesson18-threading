package baitap2;

public class TestThread {
    public static void main(String[] args) {
        OddThread odd = new OddThread();
        EvenThread even = new EvenThread();

        odd.start();

        try {
            odd.join();
        } catch (InterruptedException e) {
            System.out.println("Main bi ngat khi join OddThread");
            return;
        }

        even.start();
    }
}

class OddThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 35; i += 2) {
            System.out.println("OddThread: " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("OddThread bi ngat!");
                return;
            }
        }
    }
}

class EvenThread extends Thread {
    @Override
    public void run() {
        for (int i = 2; i <= 30; i += 2) {
            System.out.println("EvenThread: " + i);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                System.out.println("EvenThread bi ngat!");
                return;
            }
        }
    }
}