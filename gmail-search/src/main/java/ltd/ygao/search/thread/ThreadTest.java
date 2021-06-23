package ltd.ygao.search.thread;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kevin
 * @version 1.0
 * @date 2021/6/14 18:08
 */
public class ThreadTest {
  public static ExecutorService executor= Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.out.println("main........start.....");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程:{}" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果:" + i);
        }, executor);
        System.out.println("main........end.....");
    }
}
