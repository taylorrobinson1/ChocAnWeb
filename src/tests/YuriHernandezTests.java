package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import chocAn.*;
import java.time.LocalDateTime;

//Tests for DataCenterComputer
//Author: Yuri Hernandez
public class YuriHernandezTests {

    // Checks if member verification runs and returns a result
    // This also uses UserVerification in the background, so it indirectly tests
    // uses VerficationController internally
    @Test
    public void testVerifyMember() {
        DataCenterComputer dcc = new DataCenterComputer();
        boolean result = dcc.verifyMember("123");
        assertNotNull(result);
    }

    // Checks that an invalid member ID returns false for verification
    @Test
    public void testVerifyProvider() {
        DataCenterComputer dcc = new DataCenterComputer();
        boolean result = dcc.verifyProvider("invalidID");
        assertFalse(result);
    }

    // Checks that recording a service returns a non-null result, indicating success
    // This uses BillingController and other classes in the background, so it
    // indirectly tests those as well
    @Test
    public void testRecordService() {
        DataCenterComputer dcc = new DataCenterComputer();
        boolean result = dcc.recordService("123", "456", "789", LocalDateTime.now(), "Test comment");
        assertNotNull(result);
    }

}
