import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class LamportsBakeryAlgorithm {
    AtomicIntegerArray flag;
    AtomicIntegerArray label;
    final int N;

    public LamportsBakeryAlgorithm(int n)
    {
        flag = new AtomicIntegerArray(n);
        label = new AtomicIntegerArray(n);
        N = n;

    }

    public void lock(int threadId)
    {
        flag.set(threadId, 1);
        label.set(threadId, max(label) + 1);
        flag.set(threadId, 0);

        for(int i = 0; i < N; ++i)
        {
            if(i != threadId)
            {
                while (flag.get(i) != 0) {}
                while (label.get(i) != 0 && (label.get(i) < label.get(threadId) ||
                        (label.get(i) == label.get(threadId) && threadId > i))) {}
            }
        }
    }

    public void unlock(int threadId)
    {
        label.set(threadId, 0);
    }


    private int max(AtomicIntegerArray array)
    {
        int n = array.length();
        AtomicInteger maxAI = new AtomicInteger();
        maxAI.set(0);

        for(int i = 0; i < n; ++i)
        {
           if(maxAI.get() < array.get(i)) maxAI.set(array.get(i));
        }

        return maxAI.get();
    }
}
