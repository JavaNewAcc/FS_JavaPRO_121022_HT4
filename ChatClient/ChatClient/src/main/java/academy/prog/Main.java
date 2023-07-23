package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pmData;
        String pmAddressee;
        String login = "";

        try {
            while (login.trim().isEmpty()) {
                System.out.println("Enter your login: ");
                login = scanner.nextLine();
            }

            User user = new User(login);

            int resUser = user.sendUser(Utils.getURL() + "/add_user");

            if (resUser != 200) { // 200 OK
                System.out.println("HTTP error occurred: " + resUser);
                return;
            }

            Thread th = new Thread(new GetThread(login));
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                pmAddressee = null;
                String text = scanner.nextLine();
                if (text.isEmpty()) {
                    int resUserDel = user.sendUser(Utils.getURL() + "/del_user");
                    if (resUserDel != 200) { // 200 OK
                        System.out.println("HTTP error occurred: " + resUserDel);
                        return;
                    }
                    break;
                }

                if (text.trim().indexOf("@") == 0) {
                    pmParser userData = new pmParser();
                    pmData = userData.pmParser(text);
                    pmAddressee = pmData[0];
                    text = pmData[1];
                }

                if (text.trim().equals("/users")) {
                    System.out.println("These users are online now:");
                    user.getUser(Utils.getURL() + "/users");
                    continue;
                }

                Message m = new Message(login, text, pmAddressee);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occurred: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
