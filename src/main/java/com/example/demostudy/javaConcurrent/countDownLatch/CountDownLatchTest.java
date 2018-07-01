package com.example.demostudy.javaConcurrent.countDownLatch;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**基于AQS AbstractQueueSynchronizer队列同步器实现
 * 闭锁：CountDownLatch:减计数闭锁，先设置闭锁，设置锁的数量。然后每个线程调用countDown减计数，然后某一个线程wait，当countDownLatch为0的时候，才会执行wait后面的代码。
 这种方式用于对等待一组线程做事情，然后等各线程都做完了再做某件事。
 等价于join的功能。
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        ExecutorService executorPool = Executors.newCachedThreadPool();
        CountDownLatch startCountDown = new CountDownLatch(1);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Worker w1 = new Worker(countDownLatch,startCountDown,1);
        Worker w2 = new Worker(countDownLatch,startCountDown,2);
        Worker w3 = new Worker(countDownLatch,startCountDown,3);
        Boss boss = new Boss(countDownLatch,startCountDown);
        executorPool.submit(w1);
        executorPool.submit(w2);
        executorPool.submit(w3);
        executorPool.submit(boss);
        System.out.println("老板分配三个工人干活！");
        System.out.println("老板等三个工人活干完了才检查。");

        startCountDown.countDown();

        executorPool.shutdown();
    }
}
