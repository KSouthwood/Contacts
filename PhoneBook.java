package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Contact> entries = new ArrayList<>();

    void addEntry(String name, String surname, String phone) {
        entries.add(new Contact(name, surname, phone));
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
            for (int index = 0; index < entries.size(); index++) {
                System.out.printf("%d. %s %s, %s%n",
                        index + 1,
                        entries.get(index).getName(),
                        entries.get(index).getSurname(),
                        entries.get(index).getPhoneNumber());
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
            switch (field) {
                case "name":
                    entries.get(recordNum).setName(value);
                    break;
                case "surname":
                    entries.get(recordNum).setSurname(value);
                    break;
                case "number":
                    entries.get(recordNum).setPhoneNumber(value);
                    break;
            }
            System.out.println("The record was updated!");
        } else {
            System.out.println("Invalid record number!");
        }
    }
}
