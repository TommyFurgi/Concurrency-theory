import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
//        bufferTask();
        producersAndConsumersNaiveTask();
        producersAndConsumersWithoutHungerTask();
    }

    public static void bufferTask() {
        Lock lock = new ReentrantLock();
        Condition[] notifiers = new Condition[6];
        for (int i = 0; i < 6; i++) {
            notifiers[i] = lock.newCondition();
        }

        Buffer buffer = new Buffer(100, lock, notifiers);

        Thread producer = new Thread(new Producer(buffer, 0));
        Thread consumer = new Thread(new Consumer(buffer, 6));
        Thread[] processors = new Thread[5];

        for (int i = 1; i <= 5; i++) {
            processors[i - 1] = new Thread(new Processor(buffer, i));
        }

        producer.start();
        for (int i = 1; i <= 5; i++) {
            processors[i - 1].start();
        }
        consumer.start();
    }

    public static void producersAndConsumersNaiveTask() throws IOException, InterruptedException {
        List<Integer> sizes = Arrays.asList(100, 1000, 10000);
        for (int value : sizes) {
            int M = value;
            Lock lock = new ReentrantLock();
            FileWriter fileWriter = new FileWriter("./docs/naive.csv", true);
            BufferM2Naive buffer = new BufferM2Naive(M, lock, fileWriter);

            int numberOfWorkers = value/10;
            Thread[] producers = new Thread[numberOfWorkers];
            Thread[] consumers = new Thread[numberOfWorkers];

            for (int i = 0; i < numberOfWorkers; i++) {
                producers[i] = new Thread(new ProducerM2(buffer, i, M / 2));
                consumers[i] = new Thread(new ConsumerM2(buffer, i, M / 2));
            }

            for (int i = 0; i < numberOfWorkers; i++) {
                producers[i].start();
                consumers[i].start();
            }

            for (int i = 0; i < numberOfWorkers; i++) {
                producers[i].join();
                consumers[i].join();
            }
        }
    }

    public static void producersAndConsumersWithoutHungerTask() throws IOException, InterruptedException {
        List<Integer> sizes = Arrays.asList(100, 1000, 10000);
        for (int value : sizes) {
            int M = value;
            ReentrantLock lock = new ReentrantLock();
            FileWriter fileWriter = new FileWriter("./docs/without-hunger.csv", true);
            BufferM2WithoutHunger buffer = new BufferM2WithoutHunger(M, lock, fileWriter);

            int numberOfWorkers = value / 10;
            Thread[] producers = new Thread[numberOfWorkers];
            Thread[] consumers = new Thread[numberOfWorkers];

            for (int i = 0; i < numberOfWorkers; i++) {
                producers[i] = new Thread(new ProducerM2(buffer, i, M / 2));
                consumers[i] = new Thread(new ConsumerM2(buffer, i, M / 2));
            }

            for (int i = 0; i < numberOfWorkers; i++) {
                producers[i].start();
                consumers[i].start();
            }

            for (int i = 0; i < numberOfWorkers; i++) {
                producers[i].join();
                consumers[i].join();
            }
        }
    }
}
