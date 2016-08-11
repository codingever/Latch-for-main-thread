import java.util.concurrent.TimeUnit;

/**
 * Check race condition of two threads and verify that java doesn't guarantee
 * correct behavior in case no synchronized shared resources.
 *
 * In case using OS Windows run this program twice:
 * - w/out any flags
 * - add -server flag
 *
 * In case using Mac OS run this program twice:
 * - w/out any flags
 * - add -d32 flag
 *
 * and feel the difference.
 *
 * @author codingever
 */
public class RaceCondition {
    private static boolean done;

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
