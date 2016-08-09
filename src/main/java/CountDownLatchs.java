import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.LongStream;

/**
 * Make the main thread pending for two daemon threads, which started simultaneously.
 *
 * @author codingever
 */
public class CountDownLatchs {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latchForDaemonThread = new CountDownLatch(1);
        CountDownLatch latchForMainThread = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        Runnable task = () -> {
            try {
                latchForDaemonThread.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long start = System.nanoTime();
            long sum = LongStream.range(1L, 100L).sum();
            long finish = System.nanoTime();
            System.out.println(Thread.currentThread().getName() + " finish task: " + sum + " by " + (finish - start) / 1000 + " ms");
            System.out.println(Thread.currentThread().isDaemon());
            latchForMainThread.countDown();
        };

        executorService.submit(task);
        executorService.submit(task);

        System.out.println("Main wait...");
        latchForDaemonThread.countDown();
        latchForMainThread.await();
        executorService.shutdown();
    }
}
