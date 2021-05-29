package contacts;

import java.util.Scanner;

public class App {
    private PhoneBook pb = new PhoneBook();

    void start() {
        String name = readLine("Enter the name of the person:");
        String surname = readLine("Enter the surname of the person:");
        String phone = readLine("Enter the number:");
        pb.addEntry(name, surname, phone);
    }

    private String readLine(String prompt) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
