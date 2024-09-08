
public class Count {
    private int count;

    public int getCount() {
        return count;
    }

    public void increment() {
        synchronized (Main.lock) {
            count++;
        }
    }

    public void decrement() {
        synchronized (Main.lock) {
            count--;
        }
    }

    public void waitAndIncrement() throws InterruptedException {
        synchronized (Main.lock) {
            while (count % 2 == 0) {
                Main.lock.wait();
            }
            increment();
            Main.lock.notifyAll();
        }
    }
}
