package chocAn;
import java.util.Scanner;
/**
 * The Main class serves as the entry point for the ChocAn system.
 *
 * It provides a simple command-line interface that allows users
 * to interact with the ProviderTerminal by selecting menu options.
 *
 * @author Candise
 */
public class Main {
     /**
     * The main method starts the program and handles user input
     * through a menu-driven interface.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Scanner used to read user input from the console
        Scanner scanner = new Scanner(System.in);
        int choice = 5;
        // Loop to continuously display menu until user exits
        while(true) {
            System.out.println("\n--- ChocAn System ---");
            System.out.println("1. Provider Login");
            System.out.println("2. Operator Login");
            System.out.println("3. Manager Login");
            System.out.println("4. Run Accounting Procedure");
            System.out.println("5. Exit System");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            switch(choice) {   
                case 1:
                    runProviderTerminal();
                    break;
                case 2:
                    // runOperatorPage();
                    break;
                case 3:
                    // runManagerPage();
                    break;
                case 4:
                    // runAccountingProcedure();
                    break;
                case 5:
                    // Close scanner to prevent resource leak
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void runProviderTerminal() {
        ProviderTerminal terminal = new ProviderTerminal();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display menu options
            terminal.displayMenu();
            // Read user choice
            int choice = scanner.nextInt();  
            switch(choice) {   
                // Option 1: Verify member
                case 1:
                    System.out.print("Enter Member ID: ");
                    String memberID = scanner.next();
                    terminal.verifyMember(memberID);
                    break;
                // Option 2: Record service (not implemented yet)
                case 2:
                    //System.out.println("Record Service coming soon...");
                    terminal.recordService();
                    break;
                // Option 3: Weekly accounting (not implemented yet)
                case 3:
                    System.out.println("Weekly Accounting coming soon...");
                    break;
                // Option 4: Exit program
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public static void runOperatorPage() {
             ProviderTerminal terminal = new ProviderTerminal();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Display menu options
            terminal.displayMenu();
            // Read user choice
            int choice = scanner.nextInt();  
            switch(choice) {   
                // Option 1: Add New Member
                case 1:
                    System.out.print("Enter Member ID: ");
                    String memberID = scanner.next();
                    terminal.verifyMember(memberID);
                    break;
                // Option 2: Update Member
                case 2:
                    //System.out.println("Record Service coming soon...");
                    terminal.recordService();
                    break;
                // Option 3: Delete Member
                case 3:
                    System.out.println("Weekly Accounting coming soon...");
                    break;
                // Option 4: Add new Provider
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            //Option 5: Update Provider
                

            //Option 6: Delete Provider
                
        // Option 7: Exit
        }
        
    
    }
}
}