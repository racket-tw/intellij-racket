package tw.racket.racketclient;

public class NewDefinition extends Message {
    private int start;
    private int end;
    private String name;

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public String getName() {
        return this.name;
    }
}
