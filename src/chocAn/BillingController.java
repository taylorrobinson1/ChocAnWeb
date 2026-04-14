package chocAn;
import java.time.Instant;
import java.util.Date;
/**
 * The BillingController class manages billing operations in the ChocAn system.
 * It is responsible for verifying service codes and creating/storing service records.
 *
 * @author Candise
 */
public class BillingController {
    // Unique identifier for the billing record
    private String billingId;
    // Code representing the service being billed
    private String serviceCode;
    // Current status of the billing process
    private String billingStatus;
    // Directory used to validate service codes
    private ProviderDirectory providerDirectory;
    // Controller responsible for saving service records
    private RecordWriterController recordWriterController;

    /**
     * Default constructor that initializes the provider directory
     * and record writer controller using unique identifiers.
     */
    public BillingController() {
        providerDirectory = new ProviderDirectory(String.valueOf(Instant.now().hashCode()));
        recordWriterController = new RecordWriterController(String.valueOf(Instant.now().hashCode()));
    }

    /**
     * Gets the billing ID.
     *
     * @return the billing ID
     */
    public String getBillingId() {
        return billingId;
    }

    
    /**
     * Sets the billing ID.
     *
     * @param billingId the unique identifier for billing
     */
    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }
    
    /**
     * Gets the service code.
     *
     * @return the service code
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the service code.
     *
     * @param serviceCode the code representing the service
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

     /**
     * Gets the billing status.
     *
     * @return the billing status
     */
    public String getBillingStatus() {
        return billingStatus;
    }

    /**
     * Sets the billing status.
     *
     * @param billingStatus the current billing status
     */
    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    /**
     * Verifies if a given service code is valid using the provider directory.
     *
     * @param code the service code to verify
     * @return true if the code is valid, false otherwise
     */
    public boolean verifyServiceCode(String code) {
        return providerDirectory.validateServiceCode(code);
    }

    /**
     * Creates a service record and saves it using the record writer controller.
     *
     * @param memberID the ID of the member receiving the service
     * @param providerID the ID of the provider performing the service
     * @param serviceCode the code identifying the service
     * @param dateProvided the date the service was performed
     * @param comments additional notes about the service
     */
    public void billService(String memberID, String providerID, String serviceCode, Date dateProvided, String comments) {
        ServiceRecord serviceRecord = new ServiceRecord(dateProvided, providerID, memberID, serviceCode, comments);
        
        recordWriterController.saveServiceRecord(serviceRecord);
    }
}
