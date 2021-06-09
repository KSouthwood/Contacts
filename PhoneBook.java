package contacts;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 1L;
    private static List<Contact> entries = new ArrayList<>();
    private static String fileName = "";

    void addPerson(String name, String surname, String birthday, String gender, String phone) {
        entries.add(new Person(name, surname, birthday, gender, phone));
        serialize();
        System.out.println("The record was added.");
    }

    void addOrganization(String name, String address, String phone) {
        entries.add(new Organization(name, address, phone));
        serialize();
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
                System.out.printf("%d. %s%n", entries.indexOf(entry) + 1, entry.getFullName());
            }
            System.out.println();
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
            serialize();
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
            System.out.println("Saved");
            serialize();
            printRecord(recordNum);
        } else {
            System.out.println("Invalid record number!");
        }
    }

    void printRecord(int record) {
        System.out.println(entries.get(record).toString());
    }

    List<String> getFieldsOfRecord(int record) {
        return entries.get(record).getFields();
    }

    /**
     * <p>Finds all records that match a given query.</p>
     * <p>Iterate through all the records finding the ones that the name matches
     * a given query. Lists them one-by-one or prints an error if none were found.
     * Then returns the list to the caller.</p>
     * @param query search string to look for
     * @return a List of all matches
     */
    List<Contact> search(String query) {
        final List<Contact> results = new ArrayList<>();
        for (Contact entry : entries) {
            if (entry.getSearchFields().toLowerCase().contains(query)) {
                results.add(entry);
            }
        }

        if (!results.isEmpty()) {
            for (Contact result : results) {
                System.out.printf("%d. %s%n", results.indexOf(result) + 1, result.getFullName());
            }
            System.out.println();
        } else {
            System.out.println("No search results found.");
        }

        return results;
    }

    int getRecordNumber(Contact record) {
        return entries.indexOf(record);
    }

    void setFilename(String name) {
        fileName = name;
    }

    void readFromFile() {
        try {
            entries = (List<Contact>) SerializationUtils.deserialize(fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void serialize() {
        if (!fileName.isEmpty()) {
            try {
                SerializationUtils.serialize(entries, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
