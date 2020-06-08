package tw.racket.racketclient;

import com.google.gson.annotations.SerializedName;

public class UnusedRequire extends Message {
    public static String TYPE = "unused require";
    @SerializedName("start")
    public int startPosition;
    @SerializedName("end")
    public int endPosition;

    public UnusedRequire(int startPosition, int endPosition) {
        super(TYPE);
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
