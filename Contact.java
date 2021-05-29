package contacts;

public class Contact {
    private String name;
    private String surname;
    private String phoneNumber;

    Contact(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        System.out.println("A record created!");
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
