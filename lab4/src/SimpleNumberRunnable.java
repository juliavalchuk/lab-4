
public class SimpleNumberRunnable implements Runnable {
    private final NewCounter counter;
    private final int threadId;
    private final long NUMB = 10;

    public SimpleNumberRunnable(int threadId, NewCounter counter) {
        this.counter = counter;
        this.threadId = threadId;
    }

    @Override
    public void run() {

        long i = 0;
        while (i < NUMB)
        {
            Main.sync.lock(threadId);
            try{
                i = counter.getAndIncrement();
            } finally {
                Main.sync.unlock(threadId);
            }
            if(isPrime(i))
                System.out.println("Thread " + threadId + ": " + i);
        }

    }

    private boolean isPrime(long a)
    {
        long n = (long)Math.sqrt(a);
        for(long i = 2; i <= n; ++i)
            if(a % i == 0) return false;
        return true;
    }
}
