package com.example.demostudy.javaConcurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private CountDownLatch countDownLatch;

    private CountDownLatch startCountLatch;

    private Integer i;

    public Worker(CountDownLatch countDownLatch,CountDownLatch startCountLatch, Integer i) {
        this.countDownLatch = countDownLatch;
        this.startCountLatch = startCountLatch;
        this.i = i;
    }

    @Override
    public void run() {
        try {
            this.startCountLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.doWork();
        System.out.println("工人" + i + "活干完了。");
        this.countDownLatch.countDown();
    }


    public void doWork() {
        System.out.println("工人" + i + "努力干活中。");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
