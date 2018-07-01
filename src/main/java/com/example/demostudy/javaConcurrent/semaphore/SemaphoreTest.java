package com.example.demostudy.javaConcurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**基于AQS AbstractQueueSynchronizer队列同步器实现
 * 信号量：semaphore，它和synchronized相差无几，拿资源，拿‘锁’。只不过可以拿多个而已（一个线程拿一个，多个线程；一个线程拿多个，多个线程），初始化的时候代表该资源可以被几个线程拿到，可以重入。
 拿锁的时候acquire，semaphore里面维护了一个int，如果该值不能为0，则拿锁成功，否则阻塞。
 释放锁：处理完资源后，记得调用release释放，semaphore里面的int值会+1（CAS原子操作）.这样别的线程又可以拿到锁了。
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        //只能让3个线程同时操作到临界资源
        Semaphore semaphore = new Semaphore(3);

        ExecutorService executorPool = Executors.newCachedThreadPool();
        int threadNumber = 5;

        for (int i = 0; i < threadNumber; i++) {
            int threadName = i;
            executorPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(threadName + "线程尝试获取锁。");
                        Thread.sleep(2000);
                        semaphore.acquire();
                        System.out.println(threadName + "线程获取到锁，可以操作临界资源了");
                        Thread.sleep(2000);
                        semaphore.release();
                        System.out.println(threadName + "线程释放了锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorPool.shutdown();
    }
}
