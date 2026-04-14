package chocAn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordWriterController {
    // Unique identifier for the record writer
    private String recordWriterID;
    // Status of the record writing process
    private String recordStatus;
    // Directory that stores all service records
    private ServiceRecordDirectory serviceRecordDirectory;

    /**
     * Constructs a RecordWriterController with the specified ID.
     *
     * @param recordWriterID the unique identifier for this controller
     */
    public RecordWriterController(String recordWriterID) {
        this.recordWriterID = recordWriterID;
        this.serviceRecordDirectory = new ServiceRecordDirectory("SRD001");
    }

    public void createServiceRecord(String serviceDate, String providerID, String memberID, String serviceCode, String comments) {
        // create text file to store service record
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");

        System.out.println(System.getProperty("user.dir"));
        String fileDirectory = "/data/service_records/";
        String fileName = providerID + "_" + date.format(dateFormat) + ".txt";

        Path filePath = Paths.get(System.getProperty("user.dir"), fileDirectory, fileName);
        try {
            Files.createFile(filePath);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // fill text file with data as requested
        dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try {
            Files.writeString(filePath,
                date.format(dateFormat) + "\n" +
                serviceDate + "\n" +
                providerID + "\n" +
                memberID + "\n" +
                serviceCode + "\n" +
                comments + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Saves a service record after validating it.
     * A record is considered invalid if the comment exceeds 100 characters.
     *
     * @param record the service record to save
     * @return true if the record is successfully saved, false otherwise
     */
    public boolean saveServiceRecord(ServiceRecord record) {
        if (record.getComment().length() > 100) return false;

        serviceRecordDirectory.addRecord(record);
        return true;
    }

    /**
     * Gets the record writer ID.
     *
     * @return the record writer ID
     */
    public String getRecordWriterID() {
        return recordWriterID;
    }

    /**
     * Sets the record writer ID.
     *
     * @param recordWriterID the unique identifier for this controller
     */
    public void setRecordWriterID(String recordWriterID) {
        this.recordWriterID = recordWriterID;
    }

    /**
     * Gets the current record status.
     *
     * @return the record status
     */
    public String getRecordStatus() {
        return recordStatus;
    }

    /**
     * Sets the record status.
     *
     * @param recordStatus the status of the record process
     */
    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }
}