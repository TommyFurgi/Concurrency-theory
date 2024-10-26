public class Main {

    public static void main(String[] args) {
//        printersTask();
        waiterTask();
    }

    public static void printersTask() {
        int usersNumber = 5;
        PrinterMonitor monitor = new PrinterMonitor(2);
        User[] users = new User[usersNumber];

        Thread[] threads = new Thread[usersNumber];
        for (int i = 0; i < usersNumber; i ++){
            users[i] = new User(monitor, i);
            threads[i] = new Thread(users[i]);
        }
        for(int i = 0; i < usersNumber; i++){
            threads[i].start();
        }
    }

    public static void waiterTask() {
        int pairs = 3;
        Waiter waiter = new Waiter(pairs);
        Consumer[] consumers = new Consumer[pairs*2];

        Thread[] threads = new Thread[pairs*2];
        for (int i = 0; i < pairs*2; i ++){
            consumers[i] = new Consumer(waiter, i/2);
            threads[i] = new Thread(consumers[i]);
        }
        for(int i = 0; i < pairs*2; i++){
            threads[i].start();
        }
    }
}
