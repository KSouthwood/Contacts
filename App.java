package contacts;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {
    private final PhoneBook pb = new PhoneBook();

    void start(String fileName) {
        if (!fileName.isEmpty()) {
            File file = new File(fileName);
            pb.setFilename(fileName);
            if (file.exists()) {
                pb.readFromFile();
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
            startMenu();
    }

    void startMenu() {
        String choice;
        do {
            choice = readLine("[menu] Enter action (add, list, search, count, exit): ", true);
            switch (choice) {
                case "add":
                    addMenu();
                    break;
                case "list":
                    listMenu();
                    break;
                case "search":
                    searchMenu();
                    break;
                case "count":
                    System.out.printf("The Phone Book has %d records.%n", pb.countEntries());
                    break;
                case "exit":
                    break;  // need an empty case so we don't print the error message
                default:
                    System.out.println("Invalid choice! Please enter a valid action.");
                    continue;
            }
            System.out.println();
        } while (!choice.equals("exit"));
    }

    /**
     * <p>Reads a line of input from the command line using Scanner.</p>
     * <p>Get input from the user displaying the supplied prompt. Returns the
     * whole line either as is or converted to lower case.</p>
     * @param prompt text to display to the user
     * @param lowerCase flag to control if we return the string as lower case
     * @return the input text
     */
    private String readLine(String prompt, boolean lowerCase) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return lowerCase ? scanner.nextLine().toLowerCase() : scanner.nextLine();
    }

    /**
     * <p>Validate the input String is a positive integer between one (1) and
     * <code>max</code>.</p>
     * @param number String to be parsed
     * @param max maximum number to accept
     * @return an int between 1 and the number of records, else MIN_VALUE
     */
    private int validateRecordNumber(String number, int max) {
        int record = Integer.MIN_VALUE;
        if (number.matches("^\\d+")) {
            record = Integer.parseInt(number);
            if (record < 1 || record > max) {
                System.out.println("Invalid record number.");
                record = Integer.MIN_VALUE;
            }
        } else {
            System.out.println("Please enter a positive integer.");
        }
        return record;
    }

    /**
     * <p>Ask the user what type of record to add.</p>
     */
    private void addMenu() {
        String choice;
        do {
            choice = readLine("Enter the type (person, organization): ", true);
            switch (choice) {
                case "person":
                    addPerson();
                    break;
                case "organization":
                    addOrganization();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    choice = "";
                    break;
            }
        } while (choice.isEmpty());
    }

    private void addPerson() {
        String name = readLine("Enter the name: ", false);
        String surname = readLine("Enter the surname: ", false);
        String birthdate = readLine("Enter the birth date: ", false);
        if (!birthdate.matches("\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[0-1])")) {
            System.out.println("Bad birth date!");
            birthdate = "";
        }
        String gender = readLine("Enter the gender (M, F): ", false);
        if (!gender.matches("[MFmf]")) {
            System.out.println("Bad gender!");
            gender = "";
        }
        String number = readLine("Enter the number: ", false);
        pb.addPerson(name, surname, birthdate, gender, number);
    }

    private void addOrganization() {
        String name = readLine("Enter the organization name: ", false);
        String address = readLine("Enter the address: ", false);
        String phone = readLine("Enter the number: ", false);
        pb.addOrganization(name, address, phone);
    }

    /**
     * <p>Edit a record.</p>
     * <p>Lists all the records in the phonebook, or print an error message if
     * there are no records. Prompt the user for the record number to edit,
     * the field to be edited, and the new value for the field.</p>
     */
    private void editRecord(int record) {
            List<String> fieldsOfRecord = pb.getFieldsOfRecord(record);
            String fields = fieldsOfRecord.toString()
                    .replace("[", "(")
                    .replace("]", ")");
            String field;
            do {
                field = readLine("Select a field " + fields + ": ", true);
                if (!fieldsOfRecord.contains(field)) {
                    System.out.println("Invalid field. Please choose again.");
                }
            } while (!fieldsOfRecord.contains(field));
            String value = readLine("Enter " + field + ": ", false);
            pb.editEntry(record, field, value);
    }

    private void listMenu() {
        String choice;
        int record;
        pb.listEntries();

        if (pb.countEntries() > 0) {
            do {
                choice = readLine("[list] Enter action ([number], back): ", true);
                if (choice.equals("back")) {
                    return;
                }
                record = validateRecordNumber(choice, pb.countEntries());
            } while (record < 1);

            record--;
            pb.printRecord(record);
            recordMenu(record);
        }
    }

    private void recordMenu(int record) {
        String choice;
        do {
            choice = readLine("[record] Enter action (edit, delete, menu): ", true);
            switch (choice) {
                case "edit":
                    editRecord(record);
                    break;
                case "delete":
                    pb.deleteEntry(record);
                    break;
                case "menu":
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    choice = "";
                    break;
            }
        } while (choice.equals("edit") || choice.isEmpty());
    }

    private void searchMenu() {
        if (pb.countEntries() > 0) {
            String query;
            String choice;
            do {
                query = readLine("Enter search query: ", true);
                List<Contact> results = pb.search(query);
                if (results.isEmpty()) {
                    choice = "back";
                } else {
                    do {
                        choice = readLine("[search] Enter action ([number], back, again): ", true);
                        switch (choice) {
                            case "back":
                            case "again":
                                break;
                            default:
                                int record = validateRecordNumber(choice, results.size());
                                if (record > 0) {
                                    pb.printRecord(pb.getRecordNumber(results.get(record - 1)));
                                    recordMenu(pb.getRecordNumber(results.get(record - 1)));
                                } else {
                                    choice = "";
                                }
                                break;
                        }
                    } while (choice.isEmpty());
                }
            } while (choice.equals("again"));
        } else {
            System.out.println("No entries to search!");
        }
    }
}