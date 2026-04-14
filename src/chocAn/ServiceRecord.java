package chocAn;
import java.time.Instant;
import java.util.Date;
/**
 * The ServiceRecord class represents a record of a service provided
 * in the ChocAn system.
 *
 * It stores the time the record was created, the date the service
 * was provided, the provider number, the member number,
 * the service code, and any additional comments.
 *
 * @author Candise
 */
public class ServiceRecord {
    // The time the service record was created
    private Date currentTime;
    // The date the service was provided
    private Date serviceDate;
    // The provider number associated with the service
    private String providerNum;
    // The member number associated with the service
    private String memberNum;
    // The code identifying the service performed
    private String serviceCode;
    // Additional comments related to the service
    private String comment;

    /**
     * Constructs a ServiceRecord with the specified service details.
     * The current time is automatically set when the record is created.
     *
     * @param serviceDate the date the service was provided
     * @param providerNum the provider number
     * @param memberNum the member number
     * @param serviceCode the service code
     * @param comment additional comments about the service
     */
    public ServiceRecord(Date serviceDate, String providerNum, 
        String memberNum, String serviceCode, String comment) {
        this.currentTime = Date.from(Instant.now());
        this.serviceDate = serviceDate;
        this.providerNum = providerNum;
        this.memberNum = memberNum;
        this.serviceCode = serviceCode;
        this.comment = comment;
    }

    /**
     * Compares this service record to another object for equality.
     * Two service records are equal if all fields match.
     *
     * @param o the object to compare with this record
     * @return true if the objects are equal, false otherwise
     */
    //For testing
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof ServiceRecord)) return false;

        ServiceRecord comp = (ServiceRecord) o;
        if (!this.currentTime.equals(comp.currentTime)) return false;
        if (!this.serviceDate.equals(comp.serviceDate)) return false;
        if (!this.providerNum.equals(comp.providerNum)) return false;
        if (!this.memberNum.equals(comp.memberNum)) return false;
        if (!this.serviceCode.equals(comp.serviceCode)) return false;
        if (!this.comment.equals(comp.comment)) return false;
        return true;
    }

    /**
     * Gets the time the service record was created.
     *
     * @return the current record creation time
     */
    public Date getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets the time the service record was created.
     *
     * @param currentTime the record creation time
     */
    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Gets the date the service was provided.
     *
     * @return the service date
     */
    public Date getServiceDate() {
        return serviceDate;
    }

    /**
     * Sets the date the service was provided.
     *
     * @param serviceDate the date of the service
     */
    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    /**
     * Gets the provider number.
     *
     * @return the provider number
     */
    public String getProviderNum() {
        return providerNum;
    }

    /**
     * Sets the provider number.
     *
     * @param providerNum the provider number
     */
    public void setProviderNum(String providerNum) {
        this.providerNum = providerNum;
    }

    /**
     * Gets the member number.
     *
     * @return the member number
     */
    public String getMemberNum() {
        return memberNum;
    }

    /**
     * Sets the member number.
     *
     * @param memberNum the member number
     */
    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    /**
     * Gets the service code.
     *
     * @return the service code
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the service code.
     *
     * @param serviceCode the code identifying the service
     */
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * Gets the comment associated with the service record.
     *
     * @return the service comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment associated with the service record.
     *
     * @param comment additional notes about the service
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}
