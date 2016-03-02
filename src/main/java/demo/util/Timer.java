package demo.util;

import java.util.Date;

public /*TODO*/ class Timer {

    private String name;
    private long startTime;

    private Timer() {
    }

    public Timer(String name) {
        this.name = name;
        this.startTime = new Date().getTime();

        System.out.println(name + " started");
    }

    public int stop() {
        int sec = (int)(new Date().getTime() - startTime);
        System.out.println(name + " finished in " + sec + " ms");
        return sec;
    }
}
