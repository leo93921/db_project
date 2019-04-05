
package it.unisalento.db.project.models.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Job {

    @SerializedName("description")
    private String mDescription;
    @SerializedName("discoverDate")
    private String mDiscoverDate;
    @SerializedName("importConfigId")
    private Long mImportConfigId;
    @SerializedName("jobReqId")
    private Long mJobReqId;
    @SerializedName("jobSource")
    private String mJobSource;
    @SerializedName("jobTitleId")
    private Long mJobTitleId;
    @SerializedName("listingId")
    private Long mListingId;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDiscoverDate() {
        return mDiscoverDate;
    }

    public void setDiscoverDate(String discoverDate) {
        mDiscoverDate = discoverDate;
    }

    public Long getImportConfigId() {
        return mImportConfigId;
    }

    public void setImportConfigId(Long importConfigId) {
        mImportConfigId = importConfigId;
    }

    public Long getJobReqId() {
        return mJobReqId;
    }

    public void setJobReqId(Long jobReqId) {
        mJobReqId = jobReqId;
    }

    public String getJobSource() {
        return mJobSource;
    }

    public void setJobSource(String jobSource) {
        mJobSource = jobSource;
    }

    public Long getJobTitleId() {
        return mJobTitleId;
    }

    public void setJobTitleId(Long jobTitleId) {
        mJobTitleId = jobTitleId;
    }

    public Long getListingId() {
        return mListingId;
    }

    public void setListingId(Long listingId) {
        mListingId = listingId;
    }

}
