package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import chocAn.EmailController;
import chocAn.MemberAccount;
import chocAn.MemberAccountController;
import chocAn.ProviderAccount;
import chocAn.ProviderAccountController;

public class ZaneTurksonOcranTests {

    @Test
    public void testSendMemberReportSuccess() {
        EmailController emailController = new EmailController("EC-01");
        MemberAccountController memberController = new MemberAccountController("MC-01");

        MemberAccount member = new MemberAccount("001", "Jane Doe","505 avenue","apharetta", "georgia","35005" );
        memberController.addMember(member);

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        emailController.sendMemberReport(memberController, "001");

        System.setOut(old);

        assertTrue(printed.toString().contains("Member report sent successfully."));
    }

    @Test
    public void testSendMemberReportInvalidID() {
        EmailController emailController = new EmailController("EC-02");
        MemberAccountController memberController = new MemberAccountController("MC-02");

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        emailController.sendMemberReport(memberController, null);

        System.setOut(old);

        assertTrue(printed.toString().contains("Member ID is missing"));
    }

    @Test
    public void testSendProviderReportSuccess() {
        EmailController emailController = new EmailController("EC-03");
        ProviderAccountController providerController = new ProviderAccountController("37");

        ProviderAccount provider = new ProviderAccount("037", "Dr. Smith","404 bottle lane","glasscow","whisconson","35545","good","fakeemail@gmail.com");
        providerController.addProvider(provider);

        ByteArrayOutputStream printed = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(printed));

        emailController.sendProviderReport(providerController, "037");

        System.setOut(old);

        assertTrue(printed.toString().contains("Provider report sent successfully."));
    }
}