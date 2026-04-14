package chocAn;
/**
 * The EmailController class is responsible for simulating the sending
 * of reports via email in the ChocAn system.
 *
 * It can send reports for both members and providers by retrieving
 * their information from the appropriate controllers.
 *
 * @author Candise
 */
public class EmailController {
    // Unique identifier for the email controller
    private String controllerID;
    // Current status of the email process (e.g., Idle, Working, Sent, Failed)
    private String emailStatus;

    /**
     * Constructs an EmailController with a specified ID
     * and initializes the email status to "Idle".
     *
     * @param controllerID the unique identifier for this controller
     */
    public EmailController(String controllerID) {
        this.controllerID = controllerID;
        this.emailStatus = "Idle";
    }

    /**
     * Sends a report for a specific member.
     * Retrieves member information using the MemberAccountController
     * and displays the report details.
     *
     * @param memberController the controller used to access member data
     * @param memberID the ID of the member whose report is being sent
     */
    public void sendMemberReport(MemberAccountController memberController, String memberID) {
        // Validate that the member ID is provided
        if (memberID == null || memberID.isEmpty()) {
            System.out.println("Error: Member ID is missing.");
            emailStatus = "Failed";
            return;
        }
        // Retrieve member information
        MemberAccount member = memberController.getMemberInfo(memberID);
        // Check if member exists
        if (member == null) {
            System.out.println("Error: No member found with ID " + memberID);
            emailStatus = "Failed";
            return;
        }
        // Simulate sending member report
        System.out.println("Sending member report...");
        System.out.println("  Member ID:   " + member.getMemberID());
        System.out.println("  Member Name: " + member.getName());
        emailStatus = "Sent";
        System.out.println("Member report sent successfully.");
    }

    /**
     * Sends a report for a specific provider.
     * Retrieves provider information using the ProviderAccountController
     * and displays the report details.
     *
     * @param providerController the controller used to access provider data
     * @param providerID the ID of the provider whose report is being sent
     */
    public void sendProviderReport(ProviderAccountController providerController, String providerID) {
        // Validate that the provider ID is provided
        if (providerID == null || providerID.isEmpty()) {
            System.out.println("Error: Provider ID is missing.");
            emailStatus = "Failed";
            return;
        }
        // Retrieve provider information
        ProviderAccount provider = providerController.getProviderInfo(providerID);
        // Check if provider exists
        if (provider == null) {
            System.out.println("Error: No provider found with ID " + providerID);
            emailStatus = "Failed";
            return;
        }
        // Simulate sending provider report
        System.out.println("Sending provider report...");
        System.out.println("  Provider ID:   " + provider.getProviderID());
        System.out.println("  Provider Name: " + provider.getName());
        emailStatus = "Sent";
        System.out.println("Provider report sent successfully.");
    }

    public void sendProviderDirectory(String providerID) {
        emailStatus = "Working";
        // Use providerID to fetch provider e-mail address
        ProviderAccountController p = new ProviderAccountController("37");
        String providerEmail = p.getProviderInfo(providerID).getEmailAddress();
        // Send mock email to fetched address
        System.out.println("Sending provider directory...");
        System.out.println("  Sent to " + providerEmail + " at " /*+ time in MM-DD-YYYY HH:MM:SS*/);
        System.out.println("  Attached is the requested provider directory.");
        System.out.println("  Directory.txt"); // True embed beyond scope of project.
        System.out.println("Provider Directory sent successfully.");

        emailStatus = "Sent";
    }
}
