public class Main {

    public static void main (String[] args) throws InterruptedException {
        taskCounterWithoutSynchronization();
        taskCounterWithSynchronization();
        taskProducersAndConsumers();
    }

    public static void taskProducersAndConsumers () {
        Buffer buffer = new Buffer();
        Producer p1 = new Producer(buffer, 1);
        Producer p2 = new Producer(buffer, 2);
        Producer p3 = new Producer(buffer, 3);
        Consumer c1 = new Consumer(buffer, 1);
        Consumer c2 = new Consumer(buffer, 2);
        Consumer c3 = new Consumer(buffer, 3);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        Thread t4 = new Thread(c1);
        Thread t5 = new Thread(c2);
        Thread t6 = new Thread(c3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    public static void taskCounterWithoutSynchronization () throws InterruptedException {
        CounterWithoutSynchronization counter = new CounterWithoutSynchronization();
        ThreadIncrement thread1 = new ThreadIncrement(counter);
        ThreadDecrement thread2 = new ThreadDecrement(counter);


        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter.num);
    }

    public static void taskCounterWithSynchronization() throws InterruptedException {
        CounterWithSynchronization counter = new CounterWithSynchronization();
        ThreadIncrement thread1 = new ThreadIncrement(counter);
        ThreadDecrement thread2 = new ThreadDecrement(counter);


        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter.num);
    }

}
