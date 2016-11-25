package demo.sse;

public class Error {

    private final Throwable t;

    public Error(Throwable t) {
        this.t = t;
    }

    public Throwable getT() {
        return t;
    }
}
