package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import chocAn.*;

public class EliasStevensonTests {
    @BeforeEach
    void setUp() throws Exception {
    }

    // Ensure that 
    @Test
    public void createProviderReportTest() {
        String providerName = "John Doe";
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        RecordWriterController.createProviderReport(providerName);

        Path filePath = Paths.get(providerName+"_"+date.format(dateFormat)+".txt");

        assertTrue(Files.exists(filePath));
    }

    @Test
    public void sendProviderDirectoryTest() {
        EmailController emailController = new EmailController("11");
        ProviderAccountController providerController = new ProviderAccountController("22");

        ProviderAccount provider = new ProviderAccount("1", "John Doe","123","city", "state","78626", "idle", "mail@email.com");
        providerController.addProvider(provider);

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        emailController.sendProviderDirectory("1");

        System.setOut(old);

        assertTrue(printed.toString().contains("Provider Directory sent successfully."));
    }

    // Test function made by someone else
    @Test
    public void createTransferTest() {
        EFTRecord eftRecord = new EFTRecord(10, "test receiver info", "test sender info", "04-14-2026");

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        eftRecord.createTransfer();

        System.setOut(old);

        assertTrue(printed.toString().contains("Transfer created successfully."));
    }
}
