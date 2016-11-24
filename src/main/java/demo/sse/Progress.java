package demo.sse;

import java.util.Date;

public class Progress {

    private final long startTime;
    private final int max;
    private final int cur;

    private Progress(long startTime, int max, int cur) {
        this.startTime = startTime;
        this.max = max;
        this.cur = cur;
    }

    public Progress(int max) {
        this(
                new Date().getTime(),
                max,
                0
        );
    }

    public Progress next() {
        return new Progress(
                this.startTime,
                this.max,
                this.cur + 1
        );
    }

    public long getStartTime() {
        return startTime;
    }

    public long getLeftTime() {
        return new Date().getTime() - startTime;
    }

    public long getRemindedTime() {
        return (long) (getLeftTime() * (((double) max / cur) - 1));
    }

    public int getMax() {
        return max;
    }

    public int getCur() {
        return cur;
    }
}
