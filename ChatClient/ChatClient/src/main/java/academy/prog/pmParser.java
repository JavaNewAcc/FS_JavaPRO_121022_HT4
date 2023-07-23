package academy.prog;

public class pmParser {
    private String[] separatedText = new String[2];

    public String[] pmParser(String incomingText) {

        separatedText[0] = incomingText.trim().substring(1).split(" ")[0];
        separatedText[1] = incomingText.trim().substring(separatedText[0].length() + 1).trim();
        return separatedText;
    }
}