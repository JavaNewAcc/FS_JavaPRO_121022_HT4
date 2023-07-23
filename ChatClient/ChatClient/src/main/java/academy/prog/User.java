package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class User {
    private String login;

    public User(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append("Login: ").append(login).append("] ").toString();
    }

    public int sendUser(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            String json = new GsonBuilder().create().toJson(this);
            os.write(json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw e;
        }
        return conn.getResponseCode();
    }

    public void getUser(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");

        try (InputStream is = conn.getInputStream()) {
            byte[] byteBuf = RespBodyToArr.responseBodyToArray(is);
            String buf = new String(byteBuf, StandardCharsets.UTF_8);
            String[] list = new GsonBuilder().create().fromJson(buf, String[].class);
            for (String user : list) {
                System.out.println(user);
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
