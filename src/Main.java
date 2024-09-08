
public class Main {
    public final static Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {
        Count count=new Count();
        Thread increment1=new Thread(()-> {
            Thread.currentThread().setName("Increment-1");
            System.out.println(Thread.currentThread().getName());
                for(int i=0;i<1000;i++) {
                    count.increment();
                }
        });
        Thread increment2=new Thread(()-> {
            Thread.currentThread().setName("Increment-2");
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 1000; i++) {
                count.increment();
            }
        });
        Thread increment3=new Thread(()->{
            Thread.currentThread().setName("Increment-3");
            System.out.println(Thread.currentThread().getName());
                for(int i=0;i<1000;i++) {
                    count.increment();
                }
        });
        Thread decrement1=new Thread(()-> {
            Thread.currentThread().setName("Decrement-1");
            System.out.println(Thread.currentThread().getName());
                for(int i=0;i<1000;i++) {
                    count.decrement();
                }
        });
        Thread decrement2=new Thread(()-> {
            Thread.currentThread().setName("Decrement-2");
            System.out.println(Thread.currentThread().getName());
                for(int i=0;i<1000;i++) {
                    count.decrement();
                }
        });
        Thread waitAndIncrement=new Thread(()->{
            Thread.currentThread().setName("Wait and Increment");
            System.out.println(Thread.currentThread().getName());
            for(int i=0;i<1000;i++) {
                try {
                    count.waitAndIncrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        increment1.start();
        increment2.start();
        increment3.start();
        decrement1.start();
        decrement2.start();
        waitAndIncrement.start();
        increment1.join();
        increment2.join();
        increment3.join();
        decrement1.join();
        decrement2.join();
        System.out.println("Result:"+count.getCount());
        waitAndIncrement.join();
        System.out.println("Result:"+count.getCount());


    }

}
