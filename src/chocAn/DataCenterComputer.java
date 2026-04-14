package chocAn;

import java.util.Date;
import java.time.ZoneId;
import java.time.LocalDateTime;
/**
 * The DataCenterComputer class acts as the central system controller
 * for handling verification and service recording in the ChocAn system.
 *
 * It communicates with the UserVerificationController to validate users
 * and the BillingController to process and store service records.
 *
 * @author Candise
 */
public class DataCenterComputer {
    // Controller responsible for validating members and providers
    private UserVerificationController userVC;
    // Controller responsible for handling billing and service records
    private BillingController billingController;

    /**
     * Default constructor that initializes the verification
     * and billing controllers.
     */
    public DataCenterComputer() {
        userVC = new UserVerificationController("UVC-1");
        billingController = new BillingController();
    }

    /**
     * Verifies whether a member ID is valid.
     *
     * @param memberID the ID of the member to verify
     * @return true if the member is valid, false otherwise
     */
    // 1. Verify that the user is valid (by ProviderTerminal)
    public boolean verifyMember(String memberID) {
        return userVC.validateMember(memberID);
    }

    /**
     * Verifies whether a provider ID is valid.
     *
     * @param providerID the ID of the provider to verify
     * @return true if the provider is valid, false otherwise
     */
    // 2. Verify that the provider is valid
    public boolean verifyProvider(String providerID) {
        return userVC.validateProvider(providerID);
    }
     /**
     * Records a service provided by a provider to a member.
     * The method first validates both the member and provider.
     * If valid, it converts the date and sends the record
     * to the BillingController for storage.
     *
     * @param memberID the ID of the member receiving the service
     * @param providerID the ID of the provider performing the service
     * @param serviceCode the code identifying the service
     * @param date the date and time the service was provided
     * @param comments additional notes about the service
     * @return true if the service is successfully recorded, false otherwise
     */
    // 3.Record the service provided (real implementation now)
    public boolean recordService(String memberID, String providerID, String serviceCode, LocalDateTime date,
            String comments) {
        // Validate member before recording service
        if (!userVC.validateMember(memberID)) {
            return false;
        }
        // Validate provider before recording service
        if (!userVC.validateProvider(providerID)) {
            return false;
        }
        // Convert LocalDateTime to Date for compatibility
        Date convertedDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        // Record the service using the billing controller
        billingController.billService(memberID, providerID, serviceCode, convertedDate, comments);
        return true;
    }

}
