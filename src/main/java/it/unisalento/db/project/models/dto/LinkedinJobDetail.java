
package it.unisalento.db.project.models.dto;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class LinkedinJobDetail {

    @SerializedName("company")
    private String mCompany;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("Employment type")
    private String mEmploymentType;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @SerializedName("Industries")
    private String mIndustries;
    @SerializedName("Job function")
    private String mJobFunction;
    @SerializedName("link")
    private String mLink;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("name")
    private String mName;
    @SerializedName("posted")
    private String mPosted;
    @SerializedName("Seniority level")
    private String mSeniorityLevel;

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getEmploymentType() {
        return mEmploymentType;
    }

    public void setEmploymentType(String employmentType) {
        mEmploymentType = employmentType;
    }

    public String getIndustries() {
        return mIndustries;
    }

    public void setIndustries(String industries) {
        mIndustries = industries;
    }

    public String getJobFunction() {
        return mJobFunction;
    }

    public void setJobFunction(String jobFunction) {
        mJobFunction = jobFunction;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPosted() {
        return mPosted;
    }

    public void setPosted(String posted) {
        mPosted = posted;
    }

    public String getSeniorityLevel() {
        return mSeniorityLevel;
    }

    public void setSeniorityLevel(String seniorityLevel) {
        mSeniorityLevel = seniorityLevel;
    }

}
