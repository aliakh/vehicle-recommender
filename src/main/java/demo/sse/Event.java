package demo.sse;

public class Event {

    private final Progress progress;

    public Event(Progress progress) {
        this.progress = progress;
    }

    public Progress getProgress() {
        return progress;
    }
}
