package contacts;

import java.util.Scanner;

public class App {
    private final PhoneBook pb = new PhoneBook();

    void start() {
        String choice;
        do {
            choice = readLine("Enter action (add, remove, edit, count, list, exit): ").toLowerCase();
            switch (choice) {
                case "add":
                    addRecord();
                    break;
                case "remove":
                    removeRecord();
                    break;
                case "edit":
                    editRecord();
                    break;
                case "count":
                    System.out.printf("The Phone Book has %d records.%n", pb.countEntries());
                    break;
                case "list":
                    pb.listEntries();
                    break;
                case "exit":
                    break;  // need an empty case so we don't print the error message
                default:
                    System.out.println("Invalid choice! Please enter a valid action.");
                    break;
            }
        } while (!choice.equals("exit"));
    }

    private String readLine(String prompt) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     *  <p>Get a record number to act on from the user.</p>
     *  <p>Ask the user for a record number to act upon. We ensure the record
     *  number is positive and less than the number of records in the phone
     *  book. All displayed record numbers are the phone book index plus one.</p>
     * @return the record number chosen by the user, minus 1
     */
    private int getRecordNumber() {
        int record = Integer.MIN_VALUE;
        do {
            String entry = readLine("Select a record: ");
            if (entry.matches("^\\d+")) {
                record = Integer.parseInt(entry);
                if (record > pb.countEntries()) {
                    System.out.println("Invalid record number.");
                    record = Integer.MIN_VALUE;
                }
            } else {
                System.out.println("Please enter a positive integer.");
            }
        } while (record < 1);
        return record - 1;
    }

    private void addRecord() {
        String name = readLine("Enter the name: ");
        String surname = readLine("Enter the surname: ");
        String number = readLine("Enter the number: ");
        pb.addEntry(name, surname, number);
    }

    private void removeRecord() {
        if (pb.countEntries() > 0) {
            pb.listEntries();
            pb.deleteEntry(getRecordNumber());
        } else {
            System.out.println("No records to remove!");
        }
    }

    /**
     * <p>Edit a record.</p>
     * <p>Lists all the records in the phonebook, or print an error message if
     * there are no records. Prompt the user for the record number to edit,
     * the field to be edited, and the new value for the field.</p>
     */
    private void editRecord() {
        if (pb.countEntries() > 0) {
            pb.listEntries();
            int record = getRecordNumber();
            String field = editRecordMenu();
            String value = readLine("Enter " + field + ": ");
            pb.editEntry(record, field, value);
        } else {
            System.out.println("No records to edit!");
        }
    }

    /**
     * <p>Get the record field to edit.</p>
     * <p>Print's the field choices to be edited, then gets input from the user.
     * Validate the choice and print an error message if it's not one of the
     * choices.</p>
     * @return the field chosen by user
     */
    private String editRecordMenu() {
        String choice;
        do {
            choice = readLine("Select a field (name, surname, number): ").toLowerCase();
            switch (choice) {
                case "name":
                case "surname":
                case "number":
                    break;
                default:
                    System.out.println("Invalid field. Please choose again.");
                    choice = "";
            }
        } while (choice.isEmpty());

        return choice;
    }
}