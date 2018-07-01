package com.example.demostudy.javaConcurrent.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**基于AQS AbstractQueueSynchronizer队列同步器实现
 * 栅栏：CyclicBarrier 循环栅栏：设置栅栏的数量和冲破栅栏后首次执行的方法，每个线程中调用cyclicBarrier的await，只有当每个线程都执行到await，会去执行栅栏定义中的runnable方法，这个执行完后才会去执行每个线程的方法。
 等价于fork的功能。
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("我是栅栏中定义的方法。");
                    Thread.sleep(1000);
                    System.out.println("已经冲破了所有的栅栏，本方法执行完后会去执行每个线程await后的方法。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ExecutorService executorPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            int name = i;
            executorPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("------------" + name + "线程先格子做自己的事情。");
                        Thread.sleep(1000);
                        cyclicBarrier.await();

                        System.out.println("------------" + name + "线程大家都到期了做自己的事情。");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }


}
