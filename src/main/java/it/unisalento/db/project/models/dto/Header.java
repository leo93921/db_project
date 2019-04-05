
package it.unisalento.db.project.models.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Header {

    @SerializedName("adOrderId")
    private Long mAdOrderId;
    @SerializedName("advertiserType")
    private String mAdvertiserType;
    @SerializedName("employerName")
    private String mEmployerName;
    @SerializedName("expired")
    private Boolean mExpired;
    @SerializedName("jobTitle")
    private String mJobTitle;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("overviewUrl")
    private String mOverviewUrl;
    @SerializedName("posted")
    private String mPosted;

    public Long getAdOrderId() {
        return mAdOrderId;
    }

    public void setAdOrderId(Long adOrderId) {
        mAdOrderId = adOrderId;
    }

    public String getAdvertiserType() {
        return mAdvertiserType;
    }

    public void setAdvertiserType(String advertiserType) {
        mAdvertiserType = advertiserType;
    }

    public String getEmployerName() {
        return mEmployerName;
    }

    public void setEmployerName(String employerName) {
        mEmployerName = employerName;
    }

    public Boolean getExpired() {
        return mExpired;
    }

    public void setExpired(Boolean expired) {
        mExpired = expired;
    }

    public String getJobTitle() {
        return mJobTitle;
    }

    public void setJobTitle(String jobTitle) {
        mJobTitle = jobTitle;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getOverviewUrl() {
        return mOverviewUrl;
    }

    public void setOverviewUrl(String overviewUrl) {
        mOverviewUrl = overviewUrl;
    }

    public String getPosted() {
        return mPosted;
    }

    public void setPosted(String posted) {
        mPosted = posted;
    }

}
