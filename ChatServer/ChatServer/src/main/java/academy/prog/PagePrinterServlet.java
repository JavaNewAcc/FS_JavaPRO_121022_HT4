package academy.prog;

import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class PagePrinterServlet extends HttpServlet {

    private final UserList userList = UserList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter pw = resp.getWriter();
        pw.println("<html><head><title>Online users</title></head>");
        pw.println("<body><h3>The following users are currently online:</h3>");
        String[] list = new GsonBuilder().create().fromJson(userList.toJSON(), String[].class);
        for (String element : list) {
            pw.println("<p>" + element + "</p>");
        }
        pw.println("<a href=\"/usersOnline\">\n    <button>Users online</button>");
        pw.println("<a href=\"/index.jsp\">\n    <button>To home page</button>");
        pw.println("</body></html>");
    }
}