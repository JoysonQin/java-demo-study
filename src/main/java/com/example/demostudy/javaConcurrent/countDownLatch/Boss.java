package com.example.demostudy.javaConcurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Boss implements Runnable{

    private CountDownLatch countDownLatch;
    private CountDownLatch startCountDown;

    public Boss(CountDownLatch countDownLatch, CountDownLatch startCountDown){
        this.countDownLatch = countDownLatch;
        this.startCountDown = startCountDown;
    }


    @Override
    public void run() {
        try {
            this.startCountDown.await();
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.checkWork();
    }


    public void checkWork(){
        System.out.println("老板检查几个工人的活!");
    }
}
