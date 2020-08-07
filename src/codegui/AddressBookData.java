package codegui;

/**
 * This class only contains the structure of an object that can store specific data about a new contact.
 */
public class AddressBookData {

    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String contactAddress;


    /**
     * The content of this constructor is executed when the object of type AddressBookData is instantiated. Every data
     * is saved so it can be added later to an array list.
     *
     * @param contactName    contains the name of the new user.
     * @param contactPhone   contains the telephone number of the new user.
     * @param contactEmail   contains the e-mail of the new user.
     * @param contactAddress contains the address of the new user.
     */
    public AddressBookData(String contactName, String contactPhone, String contactEmail, String contactAddress) {
        setContactName(contactName);
        setContactPhone(contactPhone);
        setContactEmail(contactEmail);
        setContactAddress(contactAddress);
    }

    /**
     * Gets the value of the contact name variable.
     *
     * @return a string type that contains the name of the contact.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value in the contact name variable.
     *
     * @param contactName contains the contact's name value.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the value of the contact phone variable.
     *
     * @return a string type that contains the phone number of the contact.
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the value in the contact phone variable.
     *
     * @param contactPhone contains the contact's phone value.
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Gets the value of the contact email variable.
     *
     * @return a string type that contains the e-mail of the contact.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the value in the contact email variable.
     *
     * @param contactEmail contains the contact's email value.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Gets the value of the contact address variable.
     *
     * @return a string type that contains the address of the contact.
     */
    public String getContactAddress() {
        return contactAddress;
    }

    /**
     * Sets the value in the contact address variable.
     *
     * @param contactAddress contains the contact's address value.
     */
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
}
