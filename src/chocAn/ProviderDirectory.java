package chocAn;
import java.util.List;
import java.util.ArrayList;
/**
 * The ProviderDirectory class stores and manages the list of services
 * available to providers in the ChocAn system.
 *
 * It allows services to be added, validated by service code,
 * and queried for their associated fee.
 *
 * @author Candise
 */
public class ProviderDirectory {
    // Unique identifier for this provider directory
    private String directoryID;
    // Collection of services available in the directory
    private static List<Service> serviceList = new ArrayList<>(List.of(
        new Service("883948", 60, "Aerobics Excercise Session"),
        new Service("112233", 110, "Bootcamp"),
        new Service("123456", 20, "Check Up"),
        new Service("123123", 30, "Exposure Therapy"),
        new Service("009988", 60, "Group Therapy"),
        new Service("445566", 45, "Personal Training"),
        new Service("59840", 50, "Session with a Dietitian")
    ));

    /**
     * Constructs a ProviderDirectory with the specified directory ID
     * and initializes the service list.
     *
     * @param directoryID the unique identifier for this provider directory
     */
    public ProviderDirectory(String directoryID) {
        this.directoryID = directoryID;
        //serviceList = new ArrayList<Service>();
    }

    /**
     * Gets the directory ID.
     *
     * @return the unique identifier for this provider directory
     */
    public String getDirectoryID() {
        return directoryID;
    }


    /**
     * Adds a service to the provider directory.
     *
     * @param service the service to add
     */
    public void addService(Service service) {
        serviceList.add(service);
    }

    /**
     * Checks whether a given service code exists in the directory.
     *
     * @param code the service code to validate
     * @return true if the service code exists, false otherwise
     */
    public boolean validateServiceCode(String code) {
        for (Service service : serviceList) {
            if (service.getId().equals(code)) return true;
        }

        return false;
    }

    /**
     * Gets the fee associated with a given service code.
     *
     * @param code the service code to search for
     * @return the fee of the matching service, or 0.0 if no match is found
     */
    public double getServiceFee(String code) {
        for (Service service : serviceList) {
            if (service.getId().equals(code)) return service.getFee();
        }

        return 0.0d;
    }

    /**
     * Gets the fee associated with a given service code.
     *
     * @param code the service code to search for
     * @return the name of the matching service, or "invalid" if no match is found
     */
    public String getServiceName(String code) {
        for (Service service : serviceList) {
            if (service.getId().equals(code)) return service.getName();
        }

        return "invalid";
    }
}
