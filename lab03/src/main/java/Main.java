public class Main {

    public static void main(String[] args) {
//        printersTask();
        waiterTask();
    }

    public static void printersTask() {
        int usersNumber = 5;
        PrinterMonitor monitor = new PrinterMonitor(2);

        Thread[] threads = new Thread[usersNumber];
        for (int i = 0; i < usersNumber; i ++){
            User user = new User(monitor, i);
            threads[i] = new Thread(user);
        }
        for(int i = 0; i < usersNumber; i++){
            threads[i].start();
        }
    }

    public static void waiterTask() {
        int pairs = 3;
        Waiter waiter = new Waiter(pairs);

        Thread[] threads = new Thread[pairs*2];
        for (int i = 0; i < pairs*2; i ++){
            Consumer consumer = new Consumer(waiter, i/2);
            threads[i] = new Thread(consumer);
        }
        for(int i = 0; i < pairs*2; i++){
            threads[i].start();
        }
    }
}
