package loginsystem;

import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String firstName;
        String lastName;
        String username;
        String password;
        String phone;

        Login user;

        while (true) {

            System.out.print("Enter first name: ");
            firstName = input.nextLine();

            System.out.print("Enter last name: ");
            lastName = input.nextLine();

            System.out.print("Enter username: ");
            username = input.nextLine();

            System.out.print("Enter password: ");
            password = input.nextLine();

            System.out.print("Enter phone number (+27...): ");
            phone = input.nextLine();

            user = new Login(username, password, phone, firstName, lastName);

            String registerMessage = user.registerUser();

            System.out.println(registerMessage);

            if (registerMessage.equals("User successfully registered.")) {
                break;
            }

            System.out.println("Please try again.\n");
        }

        System.out.print("\nEnter username to login: ");
        String loginUser = input.nextLine();

        System.out.print("Enter password to login: ");
        String loginPass = input.nextLine();

        boolean success = user.loginUser(loginUser, loginPass);

        System.out.println(user.returnLoginStatus(success));

        if (success) {

            System.out.println("\nWelcome to QuickChat.");

            System.out.print("How many messages would you like to send? ");
            int totalMessages = input.nextInt();
            input.nextLine();

            int sentCount = 0;

            while (true) {

                System.out.println("""
                        
                        Choose an option:
                        1) Send Messages
                        2) Show recently sent messages
                        3) Quit
                        """);

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {

                    case 1:

                        for (int i = 0; i < totalMessages; i++) {

                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter your message: ");
                            String messageText = input.nextLine();

                            Message msg = new Message(i + 1, recipient, messageText);

                            System.out.println(msg.checkRecipientCell());
                            System.out.println(msg.checkMessageLength());

                            if (messageText.length() <= 250 &&
                                    recipient.matches("^\\+27\\d{9}$")) {

                                System.out.println("""
                                        
                                        Select option:
                                        1) Send Message
                                        2) Disregard Message
                                        3) Store Message
                                        """);

                                int sendChoice = input.nextInt();
                                input.nextLine();

                                System.out.println(msg.SentMessage(sendChoice));

                                if (sendChoice == 1 || sendChoice == 3) {

                                    sentCount++;

                                    System.out.println(msg.printMessages());
                                }
                            }
                        }

                        System.out.println("Total messages sent: " + sentCount);
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        input.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }

        input.close();
    }
}