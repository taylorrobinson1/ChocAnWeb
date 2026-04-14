package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class CandiseBattinTests {

    @Test
    public void testValidTransfer() {
        EFTRecord record = new EFTRecord(100.0, "Bank A", "Bank B", "04/13/2026");

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        record.createTransfer();

        System.setOut(old);

        assertTrue(printed.toString().contains("Transfer created successfully."));
    }

    @Test
    public void testBadAmount() {
        EFTRecord record = new EFTRecord(0.0, "Bank A", "Bank B", "04/13/2026");

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        record.createTransfer();

        System.setOut(old);

        assertTrue(printed.toString().contains("Invalid amount"));
    }

    @Test
    public void testSendWithoutCreate() {
        EFTRecord record = new EFTRecord(100.0, "Bank A", "Bank B", "04/13/2026");

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        record.sendToBank();

        System.setOut(old);

        assertTrue(printed.toString().contains("not been validated"));
    }
}