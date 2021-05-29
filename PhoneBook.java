package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> entries = new ArrayList<>();

    void addEntry(String name, String surname, String phone) {
        entries.add(new Contact(name, surname, phone));
        System.out.println("A Phone Book with a single record created!");
    }
}
