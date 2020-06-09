package tw.racket.racketclient;

public class UnusedRequire extends Message {
    private int start;
    private int end;

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }
}
