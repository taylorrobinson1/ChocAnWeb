package chocAn;
/**
 * The ProviderAccount class represents a provider in the ChocAn system.
 *
 * It stores provider-related information such as ID, name, address,
 * and status. This class is used to manage provider data within the system.
 *
 * @author Candise
 */
public class ProviderAccount {
    // Unique identifier for the provider
    String providerID;
    // Name of the provider
    String name;
    // Street address of the provider
    String streetAddress;
    // City where the provider is located
    String city;
    // State where the provider is located
    String state;
    // Zip code of the provider's address
    String zipcode;
    // Status of the provider (e.g., Active, Inactive)
    String status;
    // E-mail address of the provider
    String emailAddress;

    /**
     * Constructs a ProviderAccount with the specified details.
     *
     * @param providerID the unique provider ID
     * @param name the provider's name
     * @param streetAddress the provider's street address
     * @param city the provider's city
     * @param state the provider's state
     * @param zipcode the provider's zip code
     * @param status the provider's status
     * @param emailAddress the provider's e-mail address
     */
    public ProviderAccount(String providerID, String name, String streetAddress, String city, String state, String zipcode,
            String status, String emailAddress) {
        this.providerID = providerID;
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.status = status;
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the provider ID.
     *
     * @return the provider ID
     */
    public String getProviderID() {
        return providerID;
    }

    /**
     * Sets the provider ID.
     *
     * @param providerID the unique identifier for the provider
     */
    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    /**
     * Gets the provider's name.
     *
     * @return the provider's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the provider's name.
     *
     * @param name the name of the provider
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the provider's street address.
     *
     * @return the address of the provider
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the provider's street address.
     *
     * @param address the street address of the provider
     */
    public void setStreetAddress(String address) {
        this.streetAddress = address;
    }

    /**
     * Gets the provider's city.
     *
     * @return the city of the provider
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the provider's city.
     *
     * @param city the city where the provider is located
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the provider's state.
     *
     * @return the state of the provider
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the provider's state.
     *
     * @param state the state where the provider is located
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the provider's zip code.
     *
     * @return the zip code of the provider
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets the provider's zip code.
     *
     * @param zipcode the zip code of the provider
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets the provider's status.
     *
     * @return the provider status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the provider's status.
     *
     * @param status the provider status (e.g., Active, Inactive)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the provider's e-mail address.
     *
     * @return the e-mail address of the provider
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the provider's e-mail address.
     *
     * @param emailAddress the street address of the provider
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
