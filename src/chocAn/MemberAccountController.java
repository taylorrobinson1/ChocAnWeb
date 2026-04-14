package chocAn;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * The MemberAccountController class manages member accounts
 * in the ChocAn system.
 *
 * It is responsible for storing, retrieving, updating, deleting,
 * and verifying member records. Member data is persisted in a JSON file.
 *
 * @author Candise
 */
public class MemberAccountController {
    // Unique identifier for this controller
    private String controllerID;
    // Collection of all member accounts
    private List<MemberAccount> members;

    /**
     * Constructs a MemberAccountController with the specified ID
     * and loads member data from storage.
     *
     * @param controllerID the unique identifier for this controller
     */
    public MemberAccountController(String controllerID) {
        this.controllerID = controllerID;
        members = new ArrayList<MemberAccount>();
        readMembers();
    }

    /**
     * Adds a new member to the system and saves the updated member list.
     *
     * @param member the member account to add
     */
    public void addMember(MemberAccount member) {
        members.add(member);
        saveMembers();
    }

    /**
     * Retrieves member information by member ID.
     *
     * @param id the ID of the member to search for
     * @return the matching MemberAccount if found, otherwise null
     */
    public MemberAccount getMemberInfo(String id) {
        if(id == null){
            return null;
        }
        for (MemberAccount member : members) {
            if (member.getMemberID().equals(id)) {
                return member;
            }
        }
        return null;
    }  

    /**
     * Deletes a member from the system by ID and saves the updated list.
     *
     * @param id the ID of the member to delete
     * @return true if the member was found and removed, false otherwise
     */
    public boolean deleteMember(String id) {
        for (MemberAccount member : members) {
            if (member.getMemberID().equals(id)) {
                members.remove(member);
                saveMembers();
                return true;
            }
        }

        return false;
    }  

    /**
     * Updates the status of a member and saves the updated member list.
     *
     * @param id the ID of the member whose status is being updated
     * @param newStatus the new membership status
     */
    public void updateMemberStatus(String id, String newStatus) {
        for (MemberAccount member : members) {
            if (member.getMemberID().equals(id)) {
                member.setStatus(newStatus);
            }
        }
        saveMembers();
    }

    /**
     * Verifies a member by ID and returns a status message
     * indicating whether the member is valid, suspended, or not found.
     *
     * @param id the ID of the member to verify
     * @return a string representing the verification result
     */
    public String verifyMember(String id) {
        if (id == null || id.trim().isEmpty()) {
            return "Invalid ID";
        }

        MemberAccount member = getMemberInfo(id);

        if (member == null) {
            return "Member Not Found";
        }

        String status = member.getStatus();

        if (status == null) {
            return "Invalid Status";
        }

        switch (status) {
            case "Valid":
                return "Validated";
            case "Suspended":
                return "Suspended";
            default:
                return "Invalid";
        }
    }

    /**
     * Saves all member accounts to the members.json file.
     */
    public void saveMembers() {
        try (Writer writer = new FileWriter("members.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(members, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads member accounts from the members.json file.
     * If the file does not exist or cannot be read, an empty member list is created.
     */
    public void readMembers() {
        try (Reader reader = new FileReader("members.json")) {
            Gson gson = new GsonBuilder().create();
            members = gson.fromJson(reader, new TypeToken<List<MemberAccount>>(){}.getType());
            if(members == null){
                members = new ArrayList<MemberAccount>();
            }
        } catch (IOException e) {
            members = new ArrayList<MemberAccount>();
        }
    }
}
