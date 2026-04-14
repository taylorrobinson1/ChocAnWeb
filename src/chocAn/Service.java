package chocAn;
/**
 * The Service class represents a service provided in the ChocAn system.
 *
 * It contains information such as the service ID, name, and fee.
 * This class is used within the ProviderDirectory to manage available services.
 *
 * @author Candise
 */
public class Service {
    // Unique identifier for the service
    private String id;
    // Cost associated with the service
    private double fee;
    // Name/description of the service
    private String name;

    /**
     * Constructs a Service with the specified ID, fee, and name.
     *
     * @param id the service ID
     * @param fee the service cost
     * @param name the service name
     */
    public Service(String id, double fee, String name) {
        this.id = id; 
        this.fee = fee;
        this.name = name;
    }

    /**
     * Gets the service ID.
     *
     * @return the service ID
     */
    public String getId() {
        return id;
    }   

    /**
     * Sets the service ID.
     *
     * @param id the unique identifier for the service
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the service fee.
     *
     * @return the fee of the service
     */
    public double getFee() {
        return fee;
    }

    /**
     * Sets the service fee.
     *
     * @param fee the cost of the service
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * Gets the service name.
     *
     * @return the name of the service
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the service name.
     *
     * @param name the name/description of the service
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns a string representation of the service.
     *
     * @return formatted string containing ID, name, and fee
     */
    @Override
    public String toString() {
        return id + " - " + name + " - $" + fee;
    }
}
