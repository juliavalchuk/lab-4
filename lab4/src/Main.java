
public class Main {
    public static LamportsBakeryAlgorithm sync;

    public static void main(String[] args){
        final int NUMB = 2;
        NewCounter counter = new NewCounter(1);
        sync = new LamportsBakeryAlgorithm(NUMB);

        Runnable[]  runn = new Runnable[NUMB];
        Thread[] thread = new Thread[NUMB];

        for(int i = 0; i < NUMB; ++i)
        {
            runn[i] = new SimpleNumberRunnable(i, counter);
            thread[i] = new Thread(runn[i]);
            thread[i].start();
        }

        for(int i = 0; i < NUMB; ++i)
        {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
