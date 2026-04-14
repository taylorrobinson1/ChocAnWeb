package chocAn;
/**
 * The UserVerificationController class is responsible for validating
 * members and providers within the ChocAn system.
 *
 * It checks whether a member or provider exists and ensures their
 * account status is not suspended.
 *
 * @author Candise
 */
public class UserVerificationController {
    // Unique identifier for this controller
    private String controllerID;
    // Controller used to access member account data
    private MemberAccountController memberAccountController;
    // Controller used to access provider account data
    private ProviderAccountController providerAccountController;
    /**
     * Constructs a UserVerificationController with the specified ID
     * and initializes account controllers for members and providers.
     *
     * @param controllerID the unique identifier for this controller
     */
    public UserVerificationController(String controllerID) {
        this.controllerID = controllerID;
        memberAccountController = new MemberAccountController("1");
        providerAccountController = new ProviderAccountController("2");
    }
    /**
     * Validates whether a member is eligible.
     * A member is valid if they exist and are not suspended.
     *
     * @param memberID the ID of the member to validate
     * @return true if the member is valid, false otherwise
     */
    public boolean validateMember(String memberID) {
        // Retrieve member information
        MemberAccount m = memberAccountController.getMemberInfo(memberID);
        // Check if member exists
        if (m == null) return false;
        // Check if member is suspended
        if (m.getStatus() == "suspended") return false;
        return true;
    }
    /**
     * Validates whether a provider is eligible.
     * A provider is valid if they exist and are not suspended.
     *
     * @param providerID the ID of the provider to validate
     * @return true if the provider is valid, false otherwise
     */
    boolean validateProvider(String providerID) {
        // Retrieve provider information
        ProviderAccount p = providerAccountController.getProviderInfo(providerID);
        // Check if provider exists
        if (p == null) return false;
        // Check if provider is suspended
        if (p.getStatus() == "suspended") return false;
        return true;
    }
}
