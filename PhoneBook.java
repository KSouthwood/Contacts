package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Contact> entries = new ArrayList<>();

    void addPerson(String name, String surname, String birthday, String gender, String phone) {
        entries.add(new Person(name, surname, birthday, gender, phone));
        System.out.println("The record was added.");
    }

    void addOrganization(String name, String address, String phone) {
        entries.add(new Organization(name, address, phone));
        System.out.println("The record was added.");
    }

    int countEntries() {
        return entries.size();
    }

    /**
     * <p>Display the entries in the phone book.</p>
     * <p>List all the entries in the phone book printing out the index
     * (adjusted upwards by one) in a formatted line. Prints an error message
     * if there are no current entries.</p>
     */
    void listEntries() {
        if (entries.size() > 0) {
            for (Contact entry : entries) {
                if (entry instanceof Person) {
                    System.out.printf("%d. %s %s%n", entries.indexOf(entry) + 1,
                            ((Person) entry).getName(),
                            ((Person) entry).getSurname());
                } else {
                    System.out.printf("%d. %s%n", entries.indexOf(entry) + 1,
                            ((Organization) entry).getOrganizationName());
                }
            }
        } else {
            System.out.println("There are no records to display.");
        }
    }

    /**
     * <p>Delete an entry from the phone book.</p>
     * @param recordNum the record number to delete
     */
    void deleteEntry(int recordNum) {
        if (recordNum >= 0 && recordNum < entries.size()) {
            entries.remove(recordNum);
            System.out.println("The record was removed!");
        } else {
            System.out.println("Invalid record number!");
        }
    }

    /**
     * <p>Edits an entry.</p>
     * <p>Changes a field of a specified record number to a new provided value.</p>
     * @param recordNum record number to change
     * @param field which field to change
     * @param value the new value of the field
     */
    void editEntry(int recordNum, String field, String value) {
        if (recordNum >= 0 && recordNum < entries.size()) {
            entries.get(recordNum).editField(field, value);
            System.out.println("The record was updated!");
        } else {
            System.out.println("Invalid record number!");
        }
    }

    void printInfo(int record) {
        System.out.println(entries.get(record).toString());
    }

    String getEntryType(int record) {
        if (entries.get(record) instanceof Person) {
            return "person";
        } else {
            return "organization";
        }
    }
}
