package chocAn;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * The ProviderAccountController class manages provider accounts
 * in the ChocAn system.
 *
 * It is responsible for storing, retrieving, updating, deleting,
 * saving, and loading provider records.
 *
 * @author Candise
 */
public class ProviderAccountController {
    // Unique identifier for this controller
    private String controllerID;
    // Collection of all provider accounts
    private static List<ProviderAccount> providers = new ArrayList<ProviderAccount>();

    /**
     * Constructs a ProviderAccountController with the specified ID
     * and initializes the provider list.
     *
     * @param controllerID the unique identifier for this controller
     */
    public ProviderAccountController(String controllerID) {
        this.controllerID = controllerID;
    }

    /**
     * Gets the list of providers currently stored in the system.
     *
     * @return the list of provider accounts
     */
    public List<ProviderAccount> getProviders() {
        return providers;
    }

    /**
     * Adds a provider to the system.
     *
     * @param provider the provider account to add
     */
    public void addProvider(ProviderAccount provider) {
        providers.add(provider);
    }

    /**
     * Retrieves provider information by provider ID.
     *
     * @param id the ID of the provider to search for
     * @return the matching ProviderAccount if found, otherwise null
     */
    public ProviderAccount getProviderInfo(String id) {
        if (id == null) return null;
        for (ProviderAccount provider : providers) {
            if (provider.getProviderID().equals(id)) {
                return provider;
            }
        }

        return null;
    }  

    /**
     * Deletes a provider from the system by ID.
     *
     * @param id the ID of the provider to delete
     * @return true if the provider was found and removed, false otherwise
     */
    public boolean deleteProvider (String id){
        if (id == null) return false;
        for(ProviderAccount provider : providers){
            if(provider.getProviderID().equals(id)){
                providers.remove(provider);
                return true;
            }
        }
        return false;

    }

    /**
     * Updates the status of a provider.
     *
     * @param id the ID of the provider whose status is being updated
     * @param newStatus the new provider status
     */
    public void updateProviderStatus(String id, String newStatus) {
        if (id == null) return;
        for (ProviderAccount provider : providers) {
            if (provider.getProviderID().equals(id)) {
                provider.setStatus(newStatus);

            }
        }
    }

    /**
     * Saves all provider accounts to the providers.json file.
     */
    public void saveProviders(){
        try(Writer writer = new FileWriter("providers.json")){
            Gson gson = new GsonBuilder().create();
            gson.toJson(providers,writer);

        }
        catch(IOException e){
            e.printStackTrace();
        } 
    }

    /**
     * Reads provider accounts from the providers.json file.
     * If the file cannot be read, an empty provider list is created.
     */
    public void readProviders(){
        try (Reader reader = new FileReader("providers.json")) {
            Gson gson = new GsonBuilder().create();
            providers = gson.fromJson(reader, new TypeToken<List<ProviderAccount>>(){}.getType());

            if (providers == null) {
                providers = new ArrayList<ProviderAccount>();
            }
        } 
        catch (IOException e) {
            providers = new ArrayList<ProviderAccount>();
        }
    }

}
