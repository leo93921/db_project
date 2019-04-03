
package it.unisalento.db.project.models.dto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CompanyInfo {

    @Expose
    private Long channelId;
    @Expose
    private String companyHeader;
    @Expose
    private String jobLocation;
    @Expose
    private String name;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getCompanyHeader() {
        return companyHeader;
    }

    public void setCompanyHeader(String companyHeader) {
        this.companyHeader = companyHeader;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
