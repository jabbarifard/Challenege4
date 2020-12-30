package com.example.challenge4;

public class Timer implements Runnable {

    private final int sec;

    public Timer(int sec) {
        this.sec = sec;
        run();
    }

    @Override
    public void run() {
        int count = sec;
        while (count > 0) {
            System.out.println(count);
            //here you can put your method to start the game.
            count--;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}