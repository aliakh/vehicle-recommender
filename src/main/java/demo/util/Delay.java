package demo.util;

public class Delay {

    public static void limitRate() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
