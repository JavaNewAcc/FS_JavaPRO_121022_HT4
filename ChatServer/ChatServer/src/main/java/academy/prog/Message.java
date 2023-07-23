package academy.prog;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message {
    private Date date = new Date();
    private String from;
    private String to;
    private String text;

    public Message(String from, String text, String to) {
        this.from = from;
        this.text = text;
        this.to = to;
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(date).append(", From: ").append(from).append(", To: ").append(to).append("] ").append(text).toString();
    }
}
