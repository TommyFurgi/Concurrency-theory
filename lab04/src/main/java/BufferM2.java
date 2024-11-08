public interface BufferM2 {
    void put(int portion) throws InterruptedException;
    void get(int portion) throws InterruptedException;
}
