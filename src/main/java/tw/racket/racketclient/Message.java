package tw.racket.racketclient;

import com.google.gson.annotations.SerializedName;

public class Message {
    // field for distinguish message
    @SerializedName("type")
    String type;

    public Message(String type) {
        this.type = type;
    }
}
