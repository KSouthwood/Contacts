package contacts;

public class Organization extends Contact {
    private String organizationName;
    private String address;

    Organization(String name, String address, String phone) {
        super(phone);
        setOrganizationName(name);
        setAddress(address);
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    void editField(String field, String value) {
        switch (field) {
            case "name":
                setOrganizationName(value);
                break;
            case "address":
                setAddress(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
        }

        setDateEdited();
    }
    @Override
    public String toString() {
        return "Organization name: " + getOrganizationName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Number: " + getPhoneNumber() + "\n" +
                "Time created: " + getDateCreated() + "\n" +
                "Time last edit: " + getDateEdited();
    }
}
