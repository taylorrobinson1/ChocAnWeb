package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import chocAn.BillingController;
import chocAn.ServiceRecordDirectory;
import chocAn.UserVerificationController;
import chocAn.MemberAccount;
import chocAn.MemberAccountController;
import chocAn.ServiceRecord;

public class GiovanniDAmbrosioTests {

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    public void PersistenceTest() {
        ServiceRecordDirectory serviceRecordDirectory = new ServiceRecordDirectory("1");

        ServiceRecord s = new ServiceRecord(
            Date.from(Instant.now()),
            "1",
            "2",
            "3",
            "a"
        );

        serviceRecordDirectory.addRecord(s);
        serviceRecordDirectory.clearRecords();
        serviceRecordDirectory.readServiceRecords();

        int lastIndex = serviceRecordDirectory.getRecordList().size() - 1;
        ServiceRecord loaded = serviceRecordDirectory.getRecordList().get(lastIndex);

        assertEquals(s.getServiceCode(), loaded.getServiceCode());
        assertEquals(s.getProviderNum(), loaded.getProviderNum());
        assertEquals(s.getComment(), loaded.getComment());
    }

    // Checks that the member verification is working
    @Test
    public void MemberVerificationTest() {
        MemberAccountController mac = new MemberAccountController("1");
        MemberAccount member = new MemberAccount("3", "Gio", "100 Drive", "null", "CA", "94512");
        UserVerificationController userVerificationController = new UserVerificationController("2");

        mac.addMember(member);
        assertTrue(userVerificationController.validateMember("3"));
    }

    // Tests BillingController code ( Not written by me )
    @Test
    public void BillingControllerTest() {
        BillingController billingController = new BillingController();
        billingController.setBillingId("BID-1");

        assertTrue(billingController.getBillingId().equals("BID-1"));
    }
}