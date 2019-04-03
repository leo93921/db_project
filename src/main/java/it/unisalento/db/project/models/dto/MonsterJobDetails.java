
package it.unisalento.db.project.models.dto;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
public class MonsterJobDetails {

    @Expose
    private CompanyInfo companyInfo;
    @Expose
    private String jobCategory;
    @Expose
    private String jobDescription;
    @Expose
    private String jobId;
    @Expose
    private String jobIdentification;
    @Expose
    private String locationTypeAheadUrl;
    @Expose
    private List<String> locations;
    @Expose
    private String resumeUpdateInfoUrl;
    @Expose
    private String saveUrl;
    @Expose
    private String sendFromPlaceholder;
    @Expose
    private String sendFromValue;
    @Expose
    private SpeedApplyData speedApplyData;
    @Expose
    private Summary summary;

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobIdentification() {
        return jobIdentification;
    }

    public void setJobIdentification(String jobIdentification) {
        this.jobIdentification = jobIdentification;
    }

    public String getLocationTypeAheadUrl() {
        return locationTypeAheadUrl;
    }

    public void setLocationTypeAheadUrl(String locationTypeAheadUrl) {
        this.locationTypeAheadUrl = locationTypeAheadUrl;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getResumeUpdateInfoUrl() {
        return resumeUpdateInfoUrl;
    }

    public void setResumeUpdateInfoUrl(String resumeUpdateInfoUrl) {
        this.resumeUpdateInfoUrl = resumeUpdateInfoUrl;
    }

    public String getSaveUrl() {
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

    public String getSendFromPlaceholder() {
        return sendFromPlaceholder;
    }

    public void setSendFromPlaceholder(String sendFromPlaceholder) {
        this.sendFromPlaceholder = sendFromPlaceholder;
    }

    public String getSendFromValue() {
        return sendFromValue;
    }

    public void setSendFromValue(String sendFromValue) {
        this.sendFromValue = sendFromValue;
    }

    public SpeedApplyData getSpeedApplyData() {
        return speedApplyData;
    }

    public void setSpeedApplyData(SpeedApplyData speedApplyData) {
        this.speedApplyData = speedApplyData;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

}
