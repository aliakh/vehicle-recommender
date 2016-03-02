package demo.util;

import java.util.Date;

public /*TODO*/ class Process {

    private long startTime;
    private int max;
    private int cur;

    public Process(int max) {
        this.startTime = new Date().getTime();
        this.max = max;
    }

    public void inc() {
        cur++;
    }

    public String get() {
        int sec = (int) (new Date().getTime() - startTime)/1000;
        int etc = (int) (sec * (((double) max / cur) - 1));
        return "Progress: " + 100 * cur / max + " %, ETC " + etc + " s";
    }
}
