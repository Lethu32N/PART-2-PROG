package loginsystem;

import java.util.Random;

public class Message {

    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(int numMessagesSent, String recipient, String message) {
        this.messageID = generateMessageID();
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    private String generateMessageID() {
        Random random = new Random();
        long number = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {

        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0, 2) + ":" +
                numMessagesSent + ":" +
                (firstWord + lastWord).toUpperCase();
    }

    public String SentMessage(int option) {

        switch (option) {

            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    public String printMessages() {

        return """
               ------------------------------
               Message ID: """ + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + message +
               "\n------------------------------";
    }

    public int returnTotalMessages() {
        return numMessagesSent;
    }
}