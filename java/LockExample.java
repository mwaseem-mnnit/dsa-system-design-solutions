import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExample {
    public static ReadWriteLock lock = new ReentrantReadWriteLock();
    public static Lock writeLock = lock.writeLock();
    public static Lock readLock = lock.readLock();

    public static void main(String[] args) {
        Random rand = new Random(); //instance of random class
        int upperbound = 2;

        for (int i = 0; i < 10; i++) {
            int int_random = rand.nextInt(upperbound);
            if(int_random == 0) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        closed();
                    }
                };
                r.run();
            } else {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        open();
                    }
                };
                r.run();
            }
        }
    }

    public static void closed() {
        try {
            writeLock.lock();
            System.out.println("closed");
        } finally {
            writeLock.unlock();
        }
    }

    public static void open() {
        try {
            readLock.lock();
            System.out.println("open");
        } finally {
            readLock.unlock();
        }
    }
}