import javax.swing.*;

public abstract class Philosopher extends Thread {
    protected int philosopherId;
    protected Semaphore leftFork;
    protected Semaphore rightFork;

    public Philosopher(int philosopherId, Semaphore leftFork, Semaphore rightFork) {
        this.philosopherId = philosopherId;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    protected void think() throws InterruptedException {
        System.out.println("Philosopher " + philosopherId + " is thinking.");
//        Thread.sleep((long) (Math.random() * 2000));
    }

    protected void eat() throws InterruptedException {
        System.out.println("Philosopher " + philosopherId + " is eating.");
//        Thread.sleep((long) (Math.random() * 2000));
    }
}
