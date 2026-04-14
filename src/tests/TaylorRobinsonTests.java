/* Compile: javac -cp "src:.:lib/*:junit-4.13.2.jar:hamcrest-core-1.3.jar" src/chocAn/*.java src/tests/TaylorRobinsonTests.java
Run: java -cp "src:.:lib/*:junit-4.13.2.jar:hamcrest-core-1.3.jar" org.junit.runner.JUnitCore tests.TaylorRobinsonTests
Correct: OK (3 tests) */

package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import chocAn.MemberAccount;
import chocAn.ProviderDirectory;
import chocAn.Service;

public class TaylorRobinsonTests {

    @Test
    public void testGetNameSuccess() {
        MemberAccount member = new MemberAccount(
            "123456789",
            "Alice Johnson",
            "123 Main St",
            "City",
            "ST",
            "12345"
        );

        assertEquals("Alice Johnson", member.getName());
    }

    @Test
    public void testStatusChange() {
        MemberAccount member = new MemberAccount(
            "987654321",
            "Bob Smith",
            "456 Oak St",
            "Town",
            "AL",
            "35401"
        );

        assertEquals("Valid", member.getStatus());

        member.setStatus("Suspended");
        assertEquals("Suspended", member.getStatus());
    }

    @Test
    public void testProviderDirectory() {
        ProviderDirectory directory = new ProviderDirectory("TEST-DIR");

        Service s = new Service("598470", 49.99, "Test Service");
        directory.addService(s);

        assertTrue(directory.validateServiceCode("598470"));
        assertEquals(49.99, directory.getServiceFee("598470"), 0.001);

        assertFalse(directory.validateServiceCode("111111"));
        assertEquals(0.0, directory.getServiceFee("111111"), 0.001);
    }
}