package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String name;
    private String surname;
    private String phoneNumber;

    Pattern separators = Pattern.compile("[- ]");
    Pattern leadingPlus = Pattern.compile("(^\\+?[a-zA-Z0-9]+)");
    Pattern plusWithParens = Pattern.compile("(^\\+?\\([a-zA-Z0-9]+\\))");
    Pattern parentheses = Pattern.compile("\\([a-zA-Z0-9]{2,}\\)");
    Pattern groupLength = Pattern.compile("[a-zA-Z0-9]{2,}");

    Contact(String name, String surname, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "";
        }
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getPhoneNumber() {
        return hasNumber() ? this.phoneNumber : "[no number]";
    }

    public boolean hasNumber() {
        return !this.phoneNumber.isEmpty();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String[] groups = separators.split(phoneNumber);
        boolean validNumber = true;
        int parenCount = 0;
        for (int index = 0; index < groups.length; index++) {
            Matcher plus = leadingPlus.matcher(groups[index]);
            Matcher plusParen = plusWithParens.matcher(groups[index]);
            Matcher paren = parentheses.matcher(groups[index]);
            Matcher group = groupLength.matcher(groups[index]);
            parenCount += paren.matches() || plusParen.matches() ? 1 : 0;
            switch (index) {
                case 0:
                    validNumber &= plus.matches() || plusParen.matches() ||
                            paren.matches() || group.matches();
                    break;
                case 1:
                    validNumber &= paren.matches() || group.matches();
                    break;
                default:
                    validNumber &= group.matches();
                    break;
            }

            // break out of for loop if valid is false at any point or we have two parentheses matches
            if (!validNumber || !(parenCount < 2)) {
                validNumber = false;
                break;
            }
        }
        return validNumber;
    }
}
