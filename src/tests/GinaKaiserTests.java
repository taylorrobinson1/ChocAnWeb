package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import chocAn.RecordWriterController;
import chocAn.ServiceRecord;

import java.util.Date;

public class GinaKaiserTests {

    @Test
    public void testConstructorSetsRecordWriterID() {
        RecordWriterController controller = new RecordWriterController("RW123");
        assertEquals("RW123", controller.getRecordWriterID());
    }

    @Test
    public void testSetRecordWriterID() {
        RecordWriterController controller = new RecordWriterController("RW123");
        controller.setRecordWriterID("RW999");
        assertEquals("RW999", controller.getRecordWriterID());
    }

    @Test
    public void testSetAndGetRecordStatus() {
        RecordWriterController controller = new RecordWriterController("RW123");
        controller.setRecordStatus("Saved");
        assertEquals("Saved", controller.getRecordStatus());
    }

    @Test
    public void testSaveServiceRecordReturnsFalseWhenCommentTooLong() {
        RecordWriterController controller = new RecordWriterController("RW123");

        String longComment = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        // 101 characters

        ServiceRecord record = new ServiceRecord(
            new Date(),
            "P123456789",
            "M123456789",
            "S001",
            longComment
        );

        boolean result = controller.saveServiceRecord(record);

        assertFalse(result);
    }

    @Test
    public void testSaveServiceRecordReturnsTrueWhenCommentValid() {
        RecordWriterController controller = new RecordWriterController("RW123");

        ServiceRecord record = new ServiceRecord(
            new Date(),
            "P123456789",
            "M123456789",
            "S001",
            "Valid comment"
        );

        boolean result = controller.saveServiceRecord(record);

        assertTrue(result);
    }
}