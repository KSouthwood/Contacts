package contacts;

import java.time.LocalDate;

public class Person extends Contact {
    private String name;
    private String surname;
    private LocalDate birthday;
    private String gender;

    Person(String name, String surname, String birthday, String gender, String phone) {
        super(phone);
        setName(name);
        setSurname(surname);
        setBirthday(birthday);
        setGender(gender);
    }

    void setName(String name) {
        this.name = name;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setBirthday(String birthday) {
        if (birthday.isEmpty()) {
            this.birthday = LocalDate.MIN;
        } else {
            this.birthday = LocalDate.parse(birthday);
        }
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    String getName() {
        return this.name;
    }

    String getSurname() {
        return this.surname;
    }

    String getBirthday() {
        return this.birthday.equals(LocalDate.MIN) ? "[no data]" : this.birthday.toString();
    }

    String getGender() {
        return this.gender.isEmpty() ? "[no data]" : this.gender;
    }

    @Override
    void editField(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "birth":
                setBirthday(value);
                break;
            case "gender":
                setGender(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
        }

        setDateEdited();
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Birth date: " + getBirthday() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Number: " + getPhoneNumber() + "\n" +
                "Time created: " + getDateCreated() + "\n" +
                "Time last edit: " + getDateEdited();
    }
}
