import java.util.concurrent.TimeUnit;

/**
 * Check race condition of two threads and verify that java doesn't guarantee
 * correct behavior in case no synchronized shared resources.
 *
 * @author codingever
 */
public class RaceCondition {
    private static volatile boolean done;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int i = 0;
            while (!done) {
                i++;
            }
            System.out.println("Done!");
        }).start();

        TimeUnit.SECONDS.sleep(2);
        done = true;
        System.out.println("done set to true");
    }
}
