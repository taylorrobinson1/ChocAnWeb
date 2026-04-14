package chocAn;
/**
 * The MemberAccount class represents a member in the ChocAn system.
 *
 * It stores personal and account-related information such as
 * member ID, name, address, and membership status.
 *
 * @author Candise
 */
public class MemberAccount {
    // Unique identifier for the member
    private String memberID;
    // Name of the member
    private String name;
    // Street address of the member
    private String address;
    // City where the member resides
    private String city;
    // State where the member resides
    private String state;
    // Zip code of the member's address
    private String zipcode;
    // Membership status (e.g., Valid or Suspended)
    private String status;

    /**
     * Constructs a MemberAccount with the specified details.
     * The default status is set to "Valid".
     *
     * @param memberID the unique member ID
     * @param name the member's name
     * @param address the member's address
     * @param city the member's city
     * @param state the member's state
     * @param zipcode the member's zip code
    */
    public MemberAccount(String memberID, String name, String address, String city, String state, String zipcode) {
        this.memberID = memberID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.status = "Valid";
    }

    /**
     * Gets the member ID.
     *
     * @return the member ID
     */
    public String getMemberID() {
        return memberID;
    }

    /**
     * Sets the member ID.
     *
     * @param memberID the unique identifier for the member
     */
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    /**
     * Gets the member's name.
     *
     * @return the member's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the member's name.
     *
     * @param name the name of the member
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the member's address.
     *
     * @return the address of the member
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the member's address.
     *
     * @param address the street address of the member
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the member's city.
     *
     * @return the city of the member
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the member's city.
     *
     * @param city the city where the member resides
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the member's state.
     *
     * @return the state of the member
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the member's state.
     *
     * @param state the state where the member resides
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the member's zip code.
     *
     * @return the zip code of the member
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets the member's zip code.
     *
     * @param zipcode the zip code of the member
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets the member's status.
     *
     * @return the membership status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the member's status.
     *
     * @param status the membership status (e.g., Valid, Suspended)
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
