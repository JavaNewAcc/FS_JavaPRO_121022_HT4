package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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

    public String toJSON() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(date).append(", From: ").append(from).append(", To: ").append(to).append("] ").append(text).toString();
    }

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode(); // 200?
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
