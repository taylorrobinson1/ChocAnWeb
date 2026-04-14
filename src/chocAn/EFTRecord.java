package chocAn;
/**
 * The EFTRecord class represents an electronic funds transfer (EFT)
 * between a sender and a receiver in the ChocAn system.
 *
 * It is responsible for validating transfer details and simulating
 * sending the transfer to a bank.
 *
 * @author Candise
 */
public class EFTRecord {
    // The amount of money being transferred
    private double transferAmount;
    // Information about the receiver of the transfer
    private String receiverInfo;
    // Information about the sender of the transfer
    private String senderInfo;
    // Date of the transfer in MM-DD-YYYY format
    private String transferDate; //Format date as MM-DD-YYYY
    // Indicates whether the transfer has been validated
    private boolean isValid;
    
    /**
     * Constructs an EFTRecord with the specified transfer details.
     *
     * @param transAmt the amount to transfer
     * @param receiverInf the receiver's information
     * @param senderInf the sender's information
     * @param transDate the date of transfer in MM-DD-YYYY format
     */
    public EFTRecord(double transAmt, String receiverInf, String senderInf, String transDate) {
        transferAmount = transAmt;
        receiverInfo = receiverInf;
        senderInfo = senderInf;
        transferDate = transDate;
    }

    /**
     * Validates the transfer details and marks the transfer as valid if all
     * conditions are met. Prints error messages if validation fails.
     */
    public void createTransfer() {
            // Check that the transfer amount is greater than zero
            if(transferAmount <= 0.0){
              System.out.println("Invalid amount");
            return;
        }
        // Check that sender information is provided
        if (senderInfo == null || senderInfo.isEmpty()) {
            System.out.println("Sender information is missing.");
            return;
        }
        // Check that receiver information is provided
        if (receiverInfo == null || receiverInfo.isEmpty()) {
            System.out.println("Error: Receiver information is missing.");
            return;
        }
        // Validate date format (MM-DD-YYYY)
        if (transferDate == null || !transferDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.out.println("Error: Transfer date must be in MM-DD-YYYY format.");
            return;
        }
        // Mark transfer as valid if all checks pass
        isValid=true;
        System.out.println("Transfer created successfully.");
    }
    
    /**
     * Simulates sending the EFT transfer to a bank.
     * The transfer must be validated before it can be sent.
     */
    public void sendToBank() {
            // Ensure transfer has been validated before sending
           if (!isValid) {
            System.out.println("Error: Transfer has not been validated. Call createTransfer() first.");
            return;
        }
        // Display transfer details
        System.out.println("Sending EFT transfer to bank...");
        System.out.println("  Sender:   " + senderInfo);
        System.out.println("  Receiver: " + receiverInfo);
        System.out.println("  Amount:   $" + String.format("%.2f", transferAmount));
        System.out.println("  Date:     " + transferDate);
        System.out.println("Transfer sent successfully.");
    }
    }
