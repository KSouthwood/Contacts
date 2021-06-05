package contacts;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String phoneNumber;
    private LocalDateTime dateCreated;
    private LocalDateTime dateEdited;

    // regex patterns to validate the phone number
    static Pattern separators = Pattern.compile("[- ]");
    static Pattern leadingPlus = Pattern.compile("(^\\+?[a-zA-Z0-9]+)");
    static Pattern plusWithParens = Pattern.compile("(^\\+?\\([a-zA-Z0-9]+\\))");
    static Pattern parentheses = Pattern.compile("\\([a-zA-Z0-9]{2,}\\)");
    static Pattern groupLength = Pattern.compile("[a-zA-Z0-9]{2,}");

    Contact(String phoneNumber) {
        setDateCreated();
        setDateEdited();
        setPhoneNumber(phoneNumber);
    }

    String getDateCreated() {
        return dateCreated.toString().substring(0, 16);
    }

    private void setDateCreated() {
        this.dateCreated = LocalDateTime.now();
    }

    String getDateEdited() {
        return dateEdited.toString().substring(0, 16);
    }

    void setDateEdited() {
        this.dateEdited = LocalDateTime.now();
    }


    void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "";
        }
    }

    String getPhoneNumber() {
        return hasNumber() ? this.phoneNumber : "[no number]";
    }

    boolean hasNumber() {
        return !this.phoneNumber.isEmpty();
    }

    /**
     * <p>Validate a given phone number.</p>
     * <p>Takes a given string and parses it making sure it passes our requirements
     * to be a valid phone number.</p>
     *
     * @param phoneNumber string to be validated
     * @return true if valid
     */
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

    void editField(String field, String value) {

    }

    @Override
    public String toString() {
        return "";
    }
}
