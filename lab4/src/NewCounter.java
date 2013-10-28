
public class NewCounter {
    volatile long count;

    public NewCounter(long i)
    {
        count = i;
    }

    public NewCounter(){
        count = 0;
    }

    public long get()
    {
        return count;
    }

    public long getAndIncrement()
    {
        return count++;
    }

}
