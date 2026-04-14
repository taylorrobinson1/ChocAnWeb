package chocAn;
import java.util.Scanner;
/**
 * The ProviderTerminal class represents the user interface for providers
 * interacting with the ChocAn system.
 *
 * It displays menu options and allows providers to perform actions such
 * as verifying member eligibility.
 *
 * @author Candise
 */
public class ProviderTerminal {
    // The ID of the currently logged in provider.
    private String providerID;
    

    public void logIn() {
        // Implement provider login function.
    }

    /**
     * Displays the main menu options for the provider.
     */
    public void displayMenu() {
        System.out.println("\n--- ChocAn System ---");
        System.out.println("1. Verify Member");
        System.out.println("2. Record Service");
        System.out.println("3. Weekly Accounting");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Verifies whether a member ID is valid by using the
     * UserVerificationController.
     *
     * @param memberID the ID of the member to verify
     */
    public boolean verifyMember(String memberID) {
        // Create a verification controller instance
        UserVerificationController controller = new UserVerificationController("1");
        // Validate the member ID
        boolean result = controller.validateMember(memberID);
        // Display result to the user
        if (result) {
            System.out.println("Member is VALID");
        } else {
            System.out.println("Member is INVALID");
        }

        return result;
    }

    public void recordService() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter member number: ");
        String memberID = scanner.nextLine();

        // Exit function if memberID is invalid
        if(!verifyMember(memberID)) {
            System.out.println("Service record failed, please try again.");
            scanner.close();
            return;
        }

        System.out.print("Please enter the date service was provided (MM-DD-YYYY): ");
        String serviceDate = scanner.nextLine();

        ProviderDirectory pd = new ProviderDirectory("1");
        String correctCode = "N"; // User input for if the service code was the intended one.
        String serviceCode = "";

        // User enters service codes until they choose the correct one.
        while(!correctCode.toUpperCase().equals("Y")) {
            System.out.print("Please enter six-digit service code: ");
            serviceCode = scanner.nextLine();
            // Exit function if serviceCode is invalid
            if(!pd.validateServiceCode(serviceCode)) {
                System.out.println("Code was: " + serviceCode); //testing line
                System.out.println("Invalid service code entered, please try again.");
                continue;
            }
            else {
                System.out.println("Provided service: " + pd.getServiceName(serviceCode));
                System.out.print("Is this correct? (Y/N): ");
                correctCode = scanner.nextLine();
            }
        }

        System.out.println("Enter any additional comments (empty input to skip):");
        String comments = scanner.nextLine();

        RecordWriterController rwc = new RecordWriterController("1");
        rwc.createServiceRecord(serviceDate, providerID, memberID, serviceCode, comments);

        scanner.close();
    }

    public void requestDirectory(String providerID) {
        EmailController e = new EmailController("17");
        e.sendProviderDirectory(providerID);
    }
}   
