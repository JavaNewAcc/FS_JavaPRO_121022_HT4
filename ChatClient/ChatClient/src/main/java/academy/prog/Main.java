package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] pmData;
        String pmAddressee = null;
        String login = "";

        try {
            while (login.trim().isEmpty()) {
                System.out.println("Enter your login: ");
                login = scanner.nextLine();
            }

            Thread th = new Thread(new GetThread(login));
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) {
                    break;
                }

                if (text.trim().indexOf("@") == 0) {
                    pmParser userData = new pmParser();
                    pmData = userData.pmParser(text);
                    pmAddressee = pmData[0];
                    text = pmData[1];
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
