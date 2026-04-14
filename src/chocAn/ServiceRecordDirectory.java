package chocAn;
import java.util.List;
import java.util.ArrayList;
import java.io.Writer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * The ServiceRecordDirectory class manages the collection of
 * service records in the ChocAn system.
 *
 * It is responsible for storing service records in memory,
 * saving them to a JSON file, loading them from storage,
 * and providing access to record information.
 *
 * @author Candise
 */
public class ServiceRecordDirectory {
    // Unique identifier for this service record directory
    private String directoryID;
    // Collection of all service records
    private List<ServiceRecord> recordList;

    /**
     * Constructs a ServiceRecordDirectory with the specified ID,
     * initializes the record list, and loads existing records
     * from storage if available.
     *
     * @param directoryID the unique identifier for this directory
     */
    public ServiceRecordDirectory(String directoryID) {
        this.directoryID = directoryID;
        recordList = new ArrayList<ServiceRecord>();
        readServiceRecords();
    }

    /**
     * Adds a service record to the directory and saves
     * the updated record list to storage.
     *
     * @param record the service record to add
     */
    public void addRecord(ServiceRecord record) {
        recordList.add(record);
        saveServiceRecords();
    }
    
    /**
     * Removes all service records from the directory.
     */
    public void clearRecords() {
        recordList.clear();
    }

    /**
     * Saves the current list of service records to the
     * serviceRecords.json file.
     */
    public void saveServiceRecords() {
        try (Writer writer = new FileWriter("serviceRecords.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(recordList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads service records from the serviceRecords.json file.
     * If the file does not exist, a message is printed and
     * no data is loaded.
     */
    public void readServiceRecords() {
        try (Reader reader = new FileReader("serviceRecords.json")) {
            Gson gson = new GsonBuilder().create();
            recordList = gson.fromJson(reader, new TypeToken<List<ServiceRecord>>(){}.getType());
        } catch (IOException e) {
            System.out.println("serviceRecords.json does not exist. No data to read from.");
        }
    }
    
    /**
     * Gets the list of all service records.
     *
     * @return the list of service records
     */
    public List<ServiceRecord> getRecordList() {
        return recordList;
    }

    /**
     * Gets the directory ID.
     *
     * @return the unique identifier for this directory
     */
    public String getDirectoryID() {
        return directoryID;
    }

    /**
     * Gets the total number of service records currently stored.
     *
     * @return the number of records in the directory
     */
    public int getTotalRecord() {
        return recordList.size();
    }
}
