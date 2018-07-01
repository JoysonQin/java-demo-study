package com.example.demostudy;

import java.util.Comparator;

public class NoVisibility {

    private static boolean run;
    private static int number;

    private static class ReadThread extends Thread{
        @Override
        public void run(){
            while (!run){
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NoVisibility.ReadThread().start();
//        Thread.sleep(200);
        number = 40;
        new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        run = true;
    }
}
